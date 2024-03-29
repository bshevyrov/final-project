package ua.com.company.dao.derby.impl;

import ua.com.company.utils.DBConstants;
import ua.com.company.dao.PublicationDAO;
import ua.com.company.entity.Image;
import ua.com.company.entity.Publication;
import ua.com.company.entity.Sorting;
import ua.com.company.exception.DBException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class DerbyPublicationDAOImpl implements PublicationDAO {

    @Override
    public int create(Connection con, Publication publication) throws DBException {
        try (PreparedStatement stmt = con.prepareStatement(DBConstants.CREATE_PUBLICATION)) {
            int index = 0;
            stmt.setString(++index, publication.getTitle());
            stmt.setString(++index, publication.getDescription());
            stmt.setDouble(++index, publication.getPrice());
            stmt.setInt(++index, publication.getCover().getId());
            stmt.execute();
        } catch (SQLException e) {
            throw new DBException("Connection: " + con + " and " + publication, e);
        }
        return 0;
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
            throw new DBException("Connection: " + con + "topicId= " + topicId, e);
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
            throw new DBException("Connection: " + con + "userId= " + userId, e);
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
            throw new DBException("Connection: " + con + "searchReq= " + searchReq, e);
        }
        return count;
    }

    @Override
    public List<Publication> findAllByPersonId(Connection con, int personId) throws DBException {
        List<Publication> publications = new ArrayList<>();
        try (PreparedStatement stmt = con.prepareStatement(DBConstants.FIND_ALL_PUBLICATIONS_BY_PERSON_ID)) {
            stmt.setInt(1, personId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                publications.add(mapPublication(rs));
            }
        } catch (SQLException e) {
            throw new DBException("Connection: " + con + " personId= " + personId, e);
        }
        return publications;
    }


    public void update(Connection con, Publication publication) throws DBException {
        try (PreparedStatement ps = con.prepareStatement(DBConstants.UPDATE_PUBLICATION)) {
            int index = 0;
            ps.setString(++index, publication.getTitle());
            ps.setString(++index, publication.getDescription());
            ps.setDouble(++index, publication.getPrice());
            ps.setInt(++index,publication.getCover().getId());
            ps.setInt(++index, publication.getId());
            ps.execute();
        } catch (SQLException e) {
            throw new DBException("Connection: " + con + "publication= " + publication, e);
        }
    }
    public void deleteFromPublicationHasTopicByPublicationId(Connection con, int pubId) throws DBException {
        try (PreparedStatement stmt = con.prepareStatement(DBConstants.DELETE_PUBLICATION_HAS_TOPIC_BY_PUBLICATION_ID)) {
            int index = 0;
            stmt.setInt(++index, pubId);
            stmt.execute();
        } catch (SQLException e) {
            throw new DBException("Connection: " + con + "pubId= " + pubId, e);
        }
    }

    public void deleteOrphanTopic(Connection con) throws DBException {
        try (Statement stmt = con.createStatement()) {
            stmt.execute(DBConstants.DELETE_ORPHAN_TOPIC);
        } catch (SQLException e) {
            throw new DBException("Connection: " + con, e);
        }
    }


    @Override
    public void delete(Connection con, int id) throws DBException {
        try (PreparedStatement stmt = con.prepareStatement(DBConstants.DELETE_PUBLICATION)) {
            stmt.setInt(1, id);
            stmt.execute();
        } catch (SQLException e) {
            throw new DBException("Connection: " + con + "id= " + id, e);
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
            throw new DBException("Connection: " + con + "id= " + id, e);
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
            throw new DBException("Connection: " + con, e);
        }
        return publications;
    }

    @Override
    public List<Publication> findAllByTitle(Connection con, Sorting obj, String pattern) throws DBException {
        List<Publication> publications = new ArrayList<>();
        String search = "'" + "%" + escapeForLike(pattern) + "%" + "'";
        String query = "SELECT * " +
                "FROM publication p WHERE p.title LIKE " + search + " ORDER BY " + obj.getSortingField() +
                " " + (obj.getSortingType().equals("DESC") ? "DESC" : "") +
                " LIMIT " + obj.getStarRecord() + "," + obj.getPageSize() + "";
        return getPublications(con, publications, query);
    }

    @Override
    public List<Publication> findAllByTopicId(Connection con, Sorting obj, int id) throws DBException {
        List<Publication> publications = new ArrayList<>();

        String query = "SELECT * " +
                "FROM publication p LEFT JOIN publication_has_topic pht on p.id = pht.publication_id" +
                " INNER JOIN topic t on pht.topic_id = t.id " +
                " WHERE pht.topic_id =" + id + " ORDER BY " + obj.getSortingField() +
                " " + (obj.getSortingType().equals("DESC") ? "DESC" : "") +
                " LIMIT " + obj.getStarRecord() + "," + obj.getPageSize() + "";

        return getPublications(con, publications, query);
    }


    public void addTopicToPublication(Connection con, int pubId, int topicId) throws DBException {
        try (PreparedStatement stmt = con.prepareStatement(DBConstants.ADD_TOPIC_TO_PUBLICATION)) {
            int index = 0;
            stmt.setInt(++index, pubId);
            stmt.setInt(++index, topicId);
            stmt.execute();
        } catch (SQLException e) {
            throw new DBException("Connection: " + con + " and pubId=" + pubId + " and topicId=" + topicId, e);
        }
    }

    @Override
    public Optional<Publication> findByTitle(Connection con, String title) throws DBException {
        Optional<Publication> publication = Optional.empty();
        try (PreparedStatement stmt = con.prepareStatement(DBConstants.FIND_PUBLICATION_BY_TITLE)) {
            stmt.setString(1, title);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    publication = Optional.of(mapPublication(rs));
                }
            }
        } catch (SQLException e) {
            throw new DBException("Connection: " + con + " and title=" + title, e);
        }
        return publication;
    }


    @Override
    public List<Publication> findAllByPersonId(Connection con, Sorting obj, int userId) throws DBException {
        List<Publication> publications = new ArrayList<>();
        String query = "SELECT * " +
                "FROM publication p LEFT JOIN person_has_publication php on p.id = php.publication_id" +
                " INNER JOIN person pers on php.person_id = pers.id " +
                " WHERE pers.id =" + userId + " ORDER BY " + obj.getSortingField() +
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
            throw new DBException("Connection: " + con + " and query= " + query, e);
        }
        return publications;
    }

    static String escapeForLike(String param) {
        return param.replace("!", "!!")
                .replace("%", "!%")
                .replace("_", "!_")
                .replace("[", "![");
    }

    @Override
    public void updateCover(Connection con, int pubId, int coverId) throws DBException {
        try (PreparedStatement ps = con.prepareStatement(DBConstants.UPDATE_PUBLICATION_IMAGE)) {
            int index = 0;
            ps.setInt(++index, coverId);
            ps.setInt(++index, pubId);
            ps.execute();
        } catch (SQLException e) {
            throw new DBException("Connection: " + con + " pubId= " + pubId + " coverId= " + coverId, e);
        }
    }

    @Override
    public List<Publication> findAllSorted(Connection con, Sorting sorting) throws DBException {
        List<Publication> publications = new ArrayList<>();
        String query = "SELECT * " +
                "FROM publication p WHERE ORDER BY " + sorting.getSortingField() +
                " " + (sorting.getSortingType().equals("DESC") ? "DESC" : "") +
                " LIMIT " + sorting.getStarRecord() + "," + sorting.getPageSize() + "";
        return getPublications(con, publications, query);
    }

    @Override
    public int countAll(Connection con) throws DBException {
        int count = -1;
        try (Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(DBConstants.COUNT_ALL_PUBLICATIONS)) {
            if (rs.next()) {
                count = rs.getInt("count");
            }
        } catch (SQLException e) {
            throw new DBException("Connection: " + con, e);
        }
        return count;
    }

    private Publication mapPublication(ResultSet rs) throws SQLException {
        Publication publication = new Publication();
        publication.setId(rs.getInt(DBConstants.F_PUBLICATION_ID));
        publication.setTitle(rs.getString(DBConstants.F_PUBLICATION_TITLE));
        publication.setDescription(rs.getString(DBConstants.F_PUBLICATION_DESCRIPTION));
        publication.setPrice(rs.getDouble(DBConstants.F_PUBLICATION_PRICE));
        Image image = new Image();
        image.setId(rs.getInt(DBConstants.F_PUBLICATION_IMAGE_ID));
        publication.setCover(image);
        return publication;
    }
}

