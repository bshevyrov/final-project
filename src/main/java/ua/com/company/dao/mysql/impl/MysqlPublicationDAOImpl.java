package ua.com.company.dao.mysql.impl;

import ua.com.company.DBConstants;
import ua.com.company.dao.PublicationDAO;
import ua.com.company.entity.Image;
import ua.com.company.entity.Publication;
import ua.com.company.entity.Sorting;
import ua.com.company.entity.Topic;
import ua.com.company.exception.DBException;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class MysqlPublicationDAOImpl implements PublicationDAO {


    @Override
    public int create(Connection con, Publication publication) throws SQLException {
        int id = -1;
        try (PreparedStatement stmt = con.prepareStatement(DBConstants.CREATE_PUBLICATION,
                Statement.RETURN_GENERATED_KEYS)) {
            int index = 0;
            stmt.setString(++index, publication.getTitle());
            stmt.setString(++index, publication.getDescription());
            stmt.setDouble(++index, publication.getPrice());
            stmt.setInt(++index, publication.getCover().getId());
            int count = stmt.executeUpdate();
            if (count > 0) {
                try (ResultSet rs = stmt.getGeneratedKeys()) {
                    if (rs.next()) {
                        id = rs.getInt(1);
                    }
                }
            }
        }
        return id;
    }


    public void updateCoverForPublication(Connection con, int pubId, Image image) throws SQLException {
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement(DBConstants.UPDATE_IMAGE_TO_PUBLICATION);
            int index = 0;
            stmt.setString(++index, image.getPath());
            stmt.setInt(++index, pubId);
            stmt.execute();
        } finally {
            close(stmt);
        }

    }

    @Override
    public int countAllByTopicId(Connection con, int topicId) throws DBException {
        int count = -1;
        try (
                PreparedStatement stmt = con.prepareStatement(DBConstants.COUNT_PUBLICATION_BY_TOPIC_ID)) {
            stmt.setInt(1, topicId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                count = rs.getInt("count");
            }
        } catch (SQLException e) {
            //log
            e.printStackTrace();
            throw new DBException("GOOD INFORMATION ERORR", e);
        }
        return count;

    }

    @Override
    public int countAllByUserId(Connection con, int userId) throws DBException {
        int count = -1;
        try (
                PreparedStatement stmt = con.prepareStatement(DBConstants.COUNT_PUBLICATION_BY_USER_ID)) {
            stmt.setInt(1, userId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                count = rs.getInt("count");
            }
        } catch (SQLException e) {
            //log
            e.printStackTrace();
            throw new DBException("GOOD INFORMATION ERORR", e);
        }
        return count;    }


    @Override
    public void update(Connection con, Publication publication) throws DBException {
        try (PreparedStatement stmt = con.prepareStatement(DBConstants.UPDATE_PUBLICATION)) {
            int index = 0;
            stmt.setString(++index, publication.getTitle());
            stmt.setString(++index, publication.getDescription());
            stmt.setDouble(++index, publication.getPrice());
            stmt.setInt(++index, publication.getId());

            stmt.execute();

        } catch (SQLException e) {
            e.printStackTrace();
            throw new DBException(e);
        }
    }

    public void deleteFromPublicationHasTopicByPublicationId(Connection con, int pubId) throws SQLException {
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement(DBConstants.DELETE_PUBLICATION_HAS_TOPIC_BY_PUBLICATION_ID);
            int index = 0;
            stmt.setInt(++index, pubId);
            stmt.execute();
        } finally {
            close(stmt);
        }
    }

    public void deleteOrphanTopic(Connection con) throws SQLException {
        try (Statement stmt = con.createStatement()) {
            stmt.execute(DBConstants.DELETE_ORPHAN_TOPIC);

        }
    }


    @Override
    public void delete(Connection con, int id) throws DBException {
        try (
                PreparedStatement stmt = con.prepareStatement(DBConstants.DELETE_PUBLICATION)) {
            stmt.setInt(1, id);
            stmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DBException(e);
        }
    }

    @Override
    public Optional<Publication> findById(Connection con, int id) throws DBException {

        Publication publication = null;
        try (
                PreparedStatement stmt = con.prepareStatement(DBConstants.FIND_PUBLICATION_BY_ID)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                publication = mapPublication(rs);
            }
        } catch (SQLException e) {
            //log
            e.printStackTrace();
            throw new DBException("GOOD INFORMATION ERORR", e);
        }
        return Optional.ofNullable(publication);

    }

    @Override
    public List<Publication> findAll(Connection con) throws DBException {
        List<Publication> publications = new ArrayList<>();
        try (
                Statement stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery(DBConstants.FIND_ALL_PUBLICATIONS)) {
            while (rs.next()) {
                publications.add(mapPublication(rs));
            }
        } catch (SQLException e) {
            //log
            e.printStackTrace();
            throw new DBException("GOOD INFORMATION ERORR", e);
        }
        return publications;
    }

    @Override
    public List<Publication> findAllByTitle(Connection con, String pattern) throws DBException {

        List<Publication> publications = new ArrayList<>();
        try (
                PreparedStatement stmt = con.prepareStatement(DBConstants.FIND_ALL_PUBLICATIONS_BY_TITLE)) {
//            int k =1;

            stmt.setString(1, "%" + escapeForLike(pattern) + "%");
//            stmt.setString(1, pattern);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    publications.add(mapPublication(rs));
                }
            }
        } catch (SQLException e) {
            //log
            e.printStackTrace();
            throw new DBException("GOOD INFORMATION ERORR", e);
        }
        return publications;
    }

    @Override
    public List<Publication> findAllByTopicId(Connection con, Sorting obj, int id) throws DBException {
        List<Publication> publications = new ArrayList<>();
        String query = "SELECT p.id,p.description,p.title,p.price,p.create_date,i.name,i.path,t.title " +
                "FROM publication p LEFT JOIN publication_has_topic pht on p.id = pht.publication_id" +
                " INNER JOIN topic t on pht.topic_id = t.id " +
                "INNER JOIN image i on p.image_id = i.id WHERE pht.topic_id =" + id + " ORDER BY " + obj.getSortingField() +
                " " + (obj.getSortingType().equals("DESC") ? "DESC" : "") +
                " LIMIT " + obj.getStarRecord() + "," + obj.getPageSize() + "";
        try (
                Statement stmt = con.createStatement()) {
            try (ResultSet rs = stmt.executeQuery(query)) {
                while (rs.next()) {
                    publications.add(mapPublication(rs));
                }
            }
        } catch (SQLException e) {
            //log
            e.printStackTrace();
            throw new DBException("GOOD INFORMATION ERORR", e);
        }
        return publications;
    }
//    }


    public void addTopicForPublication(Connection con, int pubId, int topicId) throws SQLException {
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement(DBConstants.ADD_TOPIC_TO_PUBLICATION);
            int index = 0;
            stmt.setInt(++index, pubId);
            stmt.setInt(++index, topicId);
            stmt.execute();
        } finally {
            close(stmt);
        }
    }

    @Override
    public Publication findByTitle(Connection con, String title) throws DBException {

        Publication publication = new Publication();
        try (
                PreparedStatement stmt = con.prepareStatement(DBConstants.FIND_PUBLICATION_BY_TITLE)) {

            stmt.setString(1, title);
//            stmt.setString(1, pattern);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    publication = mapPublication(rs);
                }
            }
        } catch (SQLException e) {
            //log
            e.printStackTrace();
            try {
                throw new DBException("GOOD INFORMATION ERORR", e);
            } catch (DBException ex) {
                ex.printStackTrace();
            }
        }
        return publication;
    }

    @Override
    public List<Publication> findAllByUserId(Connection con,Sorting obj, int userId) throws DBException {
        List<Publication> publications = new ArrayList<>();
        String query = "SELECT p.id,p.description,p.title,p.price,p.create_date,i.name,i.path " +
                "FROM publication p LEFT JOIN person_has_publication php on p.id = php.publication_id" +
                " INNER JOIN person pers on php.person_id = pers.id " +
                "INNER JOIN image i on p.image_id = i.id WHERE pers.id =" + userId + " ORDER BY " + obj.getSortingField() +
                " " + (obj.getSortingType().equals("DESC") ? "DESC" : "") +
                " LIMIT " + obj.getStarRecord() + "," + obj.getPageSize() + "";
        try (
                Statement stmt = con.createStatement()) {
            try (ResultSet rs = stmt.executeQuery(query)) {
                while (rs.next()) {
                    publications.add(mapPublication(rs));
                }
            }
        } catch (SQLException e) {
            //log
            e.printStackTrace();
            throw new DBException("GOOD INFORMATION ERORR", e);
        }
        return publications;
    }


    static String escapeForLike(String param) {
        return param.replace("!", "!!")
                .replace("%", "!%")
                .replace("_", "!_")
                .replace("[", "![");
    }

    private Publication mapPublication(ResultSet rs) throws SQLException {
        Publication publication = new Publication();
        publication.setId(rs.getInt(DBConstants.F_PUBLICATION_ID));
        publication.setTitle(rs.getString(DBConstants.F_PUBLICATION_TITLE));
//        publication.setCreateDate(rs.getTimestamp(DBConstants.F_PUBLICATION_CREATE_DATE));
//        publication.setUpdateDate(rs.getTimestamp(DBConstants.F_PUBLICATION_UPDATE_DATE));
        publication.setDescription(rs.getString(DBConstants.F_PUBLICATION_DESCRIPTION));
        publication.setPrice(rs.getDouble(DBConstants.F_PUBLICATION_PRICE));
        publication.setCover(mapImage(rs));
//        publication.setTopics(getTopics(rs));
//        publication.setTopics((List<Topic>) mapTopic(rs));
        return publication;
    }

    private Image mapImage(ResultSet rs) throws SQLException {
        Image image = new Image();
        image.setId(rs.getInt(DBConstants.F_IMAGE_ID));
        image.setName(rs.getString(DBConstants.F_IMAGE_NAME));
        image.setPath(rs.getString(DBConstants.F_IMAGE_PATH));
//        image.setCreateDate(rs.getTimestamp(DBConstants.F_IMAGE_CREATE_DATE));
//        image.setUpdateDate(rs.getTimestamp(DBConstants.F_IMAGE_UPDATE_DATE));
        return image;
    }


}

