package ua.com.company.dao.derby.impl;

import ua.com.company.DBConstants;
import ua.com.company.dao.PublicationDAO;
import ua.com.company.entity.Image;
import ua.com.company.entity.Publication;
import ua.com.company.entity.PublicationComment;
import ua.com.company.entity.Sorting;
import ua.com.company.exception.DBException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class DerbyPublicationDAOImpl implements PublicationDAO {
    @Override
    public void create(Connection con, Publication publication) throws DBException {
        try (PreparedStatement stmt = con.prepareStatement(DBConstants.CREATE_PUBLICATION)) {
            int index = 0;
            stmt.setString(++index, publication.getTitle());
            stmt.setString(++index, publication.getDescription());
            stmt.setDouble(++index, publication.getPrice());
            stmt.setInt(++index, publication.getCover().getId());
            stmt.execute();
        } catch (SQLException e) {
            throw new DBException(con + publication.toString(), e);
        }
    }


    public void updateCoverForPublication(Connection con, int pubId, Image image) throws DBException {
        try (PreparedStatement stmt = con.prepareStatement(DBConstants.UPDATE_IMAGE_TO_PUBLICATION)) {
            int index = 0;
            stmt.setString(++index, image.getPath());
            stmt.setInt(++index, pubId);
            stmt.execute();
        } catch (SQLException e) {
            throw new DBException(con + "pubId= " + pubId + image.toString(), e);
        }
    }

    @Override
    public int countAllByTopicId(Connection con, int topicId) throws DBException {
        int count = -1;
        try (PreparedStatement stmt = con.prepareStatement(DBConstants.COUNT_PUBLICATION_BY_TOPIC_ID)) {
            stmt.setInt(1, topicId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                count = rs.getInt("count");
            }
        } catch (SQLException e) {
            throw new DBException(con + "topicId= " + topicId, e);
        }
        return count;
    }

    @Override
    public int countAllByUserId(Connection con, int userId) throws DBException {
        int count = -1;
        try (PreparedStatement stmt = con.prepareStatement(DBConstants.COUNT_PUBLICATION_BY_USER_ID)) {
            stmt.setInt(1, userId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                count = rs.getInt("count");
            }
        } catch (SQLException e) {
            throw new DBException(con + "userId= " + userId, e);
        }
        return count;
    }

    @Override
    public int countAllByTitle(Connection con, String searchReq) throws DBException {
        int count = -1;
        try (PreparedStatement stmt = con.prepareStatement(DBConstants.COUNT_PUBLICATION_BY_TITLE)) {
            stmt.setString(1, "%" + escapeForLike(searchReq) + "%");
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                count = rs.getInt("count");
            }
        } catch (SQLException e) {
            throw new DBException(con + "searchReq= " + searchReq, e);
        }
        return count;
    }


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
            throw new DBException(con + "publication= " + publication, e);
        }
    }

    public void deleteFromPublicationHasTopicByPublicationId(Connection con, int pubId) throws DBException {
        try (PreparedStatement stmt = con.prepareStatement(DBConstants.DELETE_PUBLICATION_HAS_TOPIC_BY_PUBLICATION_ID)) {
            int index = 0;
            stmt.setInt(++index, pubId);
            stmt.execute();
        } catch (SQLException e) {
            throw new DBException(con + "pubId= " + pubId, e);
        }
    }

    public void deleteOrphanTopic(Connection con) throws DBException {
        try (Statement stmt = con.createStatement()) {
            stmt.execute(DBConstants.DELETE_ORPHAN_TOPIC);
        } catch (SQLException e) {
            throw new DBException(con.toString(), e);
        }
    }


    @Override
    public void delete(Connection con, int id) throws DBException {
        try (PreparedStatement stmt = con.prepareStatement(DBConstants.DELETE_PUBLICATION)) {
            stmt.setInt(1, id);
            stmt.execute();
        } catch (SQLException e) {
            throw new DBException(con + "id= " + id, e);
        }
    }

    @Override
    public Optional<Publication> findById(Connection con, int id) throws DBException {
        Optional<Publication> publication = Optional.empty();
        try (PreparedStatement stmt = con.prepareStatement(DBConstants.FIND_PUBLICATION_BY_ID)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                publication = Optional.of(mapPublication(rs));
            }
        } catch (SQLException e) {
            throw new DBException(con + "id= " + id, e);
        }
        return publication;

    }

    @Override
    public List<Publication> findAll(Connection con) throws DBException {
        List<Publication> publications = new ArrayList<>();
        try (Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(DBConstants.FIND_ALL_PUBLICATIONS)) {
            while (rs.next()) {
                publications.add(mapPublication(rs));
            }
        } catch (SQLException e) {
            throw new DBException(con.toString(), e);
        }
        return publications;
    }

    @Override
    public List<Publication> findAllByTitle(Connection con, Sorting obj, String pattern) throws DBException {//TODO GENERIC FINDALL
        List<Publication> publications = new ArrayList<>();
        String search = "'" + "%" + escapeForLike(pattern) + "%" + "'";
        String query = "SELECT p.id,p.description,p.title,p.price,p.create_date,i.name,i.path " +
                "FROM publication p INNER JOIN image i on p.image_id = i.id WHERE p.title LIKE " + search + " ORDER BY " + obj.getSortingField() +
                " " + (obj.getSortingType().equals("DESC") ? "DESC" : "") +
                " LIMIT " + obj.getStarRecord() + "," + obj.getPageSize() + "";
        return getPublications(con, publications, query);
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
        return getPublications(con, publications, query);
    }


    public void addTopicForPublication(Connection con, int pubId, int topicId) throws DBException {
        try (PreparedStatement stmt = con.prepareStatement(DBConstants.ADD_TOPIC_TO_PUBLICATION)) {
            int index = 0;
            stmt.setInt(++index, pubId);
            stmt.setInt(++index, topicId);
            stmt.execute();
        } catch (SQLException e) {
            throw new DBException(con + " pubId=" + pubId + " topicId=" + topicId, e);
        }
    }

    @Override
    public Publication findByTitle(Connection con, String title) throws DBException {
        Publication publication = new Publication();
        try (PreparedStatement stmt = con.prepareStatement(DBConstants.FIND_PUBLICATION_BY_TITLE)) {
            stmt.setString(1, title);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    publication = mapPublication(rs);
                }
            }
        } catch (SQLException e) {
            throw new DBException(con + " title=" + title, e);
        }
        return publication;
    }


    @Override
    public List<Publication> findAllByUserId(Connection con, Sorting obj, int userId) throws DBException {
        List<Publication> publications = new ArrayList<>();
        String query = "SELECT p.id,p.description,p.title,p.price,p.create_date,i.name,i.path " +
                "FROM publication p LEFT JOIN person_has_publication php on p.id = php.publication_id" +
                " INNER JOIN person pers on php.person_id = pers.id " +
                "INNER JOIN image i on p.image_id = i.id WHERE pers.id =" + userId + " ORDER BY " + obj.getSortingField() +
                " " + (obj.getSortingType().equals("DESC") ? "DESC" : "") +
                " LIMIT " + obj.getStarRecord() + "," + obj.getPageSize() + "";
        return getPublications(con, publications, query);
    }

    private List<Publication> getPublications(Connection con, List<Publication> publications, String query) throws DBException {
        try (Statement stmt = con.createStatement()) {
            try (ResultSet rs = stmt.executeQuery(query)) {
                while (rs.next()) {
                    publications.add(mapPublication(rs));
                }
            }
        } catch (SQLException e) {
            throw new DBException(con + query, e);
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
        publication.setDescription(rs.getString(DBConstants.F_PUBLICATION_DESCRIPTION));
        publication.setPrice(rs.getDouble(DBConstants.F_PUBLICATION_PRICE));
        publication.setCover(mapImage(rs));
        return publication;
    }

    private Image mapImage(ResultSet rs) throws SQLException {
        Image image = new Image();
        image.setId(rs.getInt(DBConstants.F_IMAGE_ID));
        image.setName(rs.getString(DBConstants.F_IMAGE_NAME));
        image.setPath(rs.getString(DBConstants.F_IMAGE_PATH));
        return image;
    }


}

