package ua.com.company.dao.mysql.impl;

import ua.com.company.dao.PublicationDAO;
import ua.com.company.entity.*;
import ua.com.company.exception.DBException;
import ua.com.company.utils.DBConstants;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class MysqlPublicationDAOImpl implements PublicationDAO {
    /**
     * @param con         connection to DataBase
     * @param publication entity to put in Database
     * @return id of created entity in DataBase
     * @throws DBException if catch SQLException
     */
    @Override
    public int create(Connection con, Publication publication) throws DBException {
        int id = 0;
        try (PreparedStatement ps = con.prepareStatement(DBConstants.CREATE_PUBLICATION, PreparedStatement.RETURN_GENERATED_KEYS)) {
            int index = 0;
            ps.setString(++index, publication.getTitle());
            ps.setString(++index, publication.getDescription());
            ps.setDouble(++index, publication.getPrice());
            ps.setInt(++index, publication.getCover().getId());
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                id = rs.getInt(1);
            }
        } catch (SQLException e) {
            throw new DBException("Connection: " + con + " and " + publication, e);
        }
        return id;
    }

    /**
     * @param con         connection to DataBase
     * @param publication entity to update in Database
     * @throws DBException if catch SQLException
     */
    @Override
    public void update(Connection con, Publication publication) throws DBException {
        try (PreparedStatement ps = con.prepareStatement(DBConstants.UPDATE_PUBLICATION)) {
            int index = 0;
            ps.setString(++index, publication.getTitle());
            ps.setString(++index, publication.getDescription());
            ps.setDouble(++index, publication.getPrice());
            ps.setInt(++index, publication.getCover().getId());
            ps.setInt(++index, publication.getId());
            ps.execute();
        } catch (SQLException e) {
            throw new DBException("Connection: " + con + "publication= " + publication, e);
        }
    }

    /**
     * @param con   connection to DataBase
     * @param pubId id of {@link Publication}
     * @throws DBException if catch SQLException
     */
    public void deleteFromPublicationHasTopicByPublicationId(Connection con, int pubId) throws DBException {
        try (PreparedStatement stmt = con.prepareStatement(DBConstants.DELETE_PUBLICATION_HAS_TOPIC_BY_PUBLICATION_ID)) {
            int index = 0;
            stmt.setInt(++index, pubId);
            stmt.execute();
        } catch (SQLException e) {
            throw new DBException("Connection: " + con + "pubId= " + pubId, e);
        }
    }

    /**
     * @param con connection to DataBase
     * @throws DBException if catch SQLException
     */
    public void deleteOrphanTopic(Connection con) throws DBException {
        try (Statement stmt = con.createStatement()) {
            stmt.execute(DBConstants.DELETE_ORPHAN_TOPIC);
        } catch (SQLException e) {
            throw new DBException("Connection: " + con, e);
        }
    }

    /**
     * @param con connection to DataBase
     * @param id  of entity that need to delete
     * @throws DBException if catch SQLException
     */
    @Override
    public void delete(Connection con, int id) throws DBException {
        try (PreparedStatement stmt = con.prepareStatement(DBConstants.DELETE_PUBLICATION)) {
            stmt.setInt(1, id);
            stmt.execute();
        } catch (SQLException e) {
            throw new DBException("Connection: " + con + "id= " + id, e);
        }
    }

    /**
     * @param con connection to DataBase
     * @param id  of entity that want to get
     * @return found entity or Optional.empty() of entity
     * @throws DBException if catch SQLException
     */
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

    /**
     * @param con connection to DataBase
     * @return List of all entity
     * @throws DBException if catch SQLException
     */
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

    /**
     * @param con       connection to DataBase
     * @param sorting   {@link Sorting} object that contain sorting attributes
     * @param searchReq part of {@link Publication} title to search in Database
     * @return List of all entity
     * @throws DBException if catch SQLException
     */
    @Override
    public List<Publication> findAllByTitle(Connection con, Sorting sorting, String searchReq) throws DBException {
        List<Publication> publications = new ArrayList<>();
        String search = "'" + "%" + escapeForLike(searchReq) + "%" + "'";
        String query = "SELECT * " +
                "FROM publication p WHERE p.title LIKE " + search + " ORDER BY " + sorting.getSortingField() +
                " " + (sorting.getSortingType().equals("DESC") ? "DESC" : "") +
                " LIMIT " + sorting.getStarRecord() + "," + sorting.getPageSize() + "";
        return getPublications(con, publications, query);
    }

    /**
     * @param con     connection to DataBase
     * @param sorting {@link Sorting} object that contain sorting attributes
     * @param id      id of{@link Topic}
     * @return List of all entity
     * @throws DBException if catch SQLException
     */
    @Override
    public List<Publication> findAllByTopicId(Connection con, Sorting sorting, int id) throws DBException {
        List<Publication> publications = new ArrayList<>();

        String query = "SELECT * " +
                "FROM publication p LEFT JOIN publication_has_topic pht on p.id = pht.publication_id" +
                " WHERE pht.topic_id =" + id + " ORDER BY " + sorting.getSortingField() +
                " " + (sorting.getSortingType().equals("DESC") ? "DESC" : "") +
                " LIMIT " + sorting.getStarRecord() + "," + sorting.getPageSize() + "";

        return getPublications(con, publications, query);
    }

    /**
     * @param con     connection to DataBase
     * @param pubId   id of {@link Publication}
     * @param topicId id of {@link Topic}
     * @throws DBException if catch SQLException
     */
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

    /**
     * @param con   connection to DataBase
     * @param title title of {@link Publication}
     * @return found entity or Optional.empty() of entity
     * @throws DBException if catch SQLException
     */
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

    /**
     * @param con     connection to DataBase
     * @param sorting {@link Sorting} object that contain sorting attributes
     * @param userId  id of {@link Person}
     * @return List of all entity
     * @throws DBException if catch SQLException
     */
    @Override
    public List<Publication> findAllByPersonId(Connection con, Sorting sorting, int userId) throws DBException {
        List<Publication> publications = new ArrayList<>();
        String query = "SELECT * " +
                "FROM publication p LEFT JOIN person_has_publication php on p.id = php.publication_id" +
                " INNER JOIN person pers on php.person_id = pers.id " +
                " WHERE pers.id =" + userId + " ORDER BY " + sorting.getSortingField() +
                " " + (sorting.getSortingType().equals("DESC") ? "DESC" : "") +
                " LIMIT " + sorting.getStarRecord() + "," + sorting.getPageSize() + "";
        return getPublications(con, publications, query);
    }

    /**
     * @param con          connection to DataBase
     * @param publications List where to add publication from result set
     * @param query        to execute
     * @return List of all entity
     * @throws DBException if catch SQLException
     */
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

    /**
     * Find  special character and add escape symbols
     *
     * @param searchReq part of search request
     * @return correct string for SQL request
     */
    static String escapeForLike(String searchReq) {
        return searchReq.replace("!", "!!")
                .replace("%", "!%")
                .replace("_", "!_")
                .replace("[", "![");
    }

    /**
     * @param con     connection to DataBase
     * @param topicId id of {@link Topic}
     * @return count of all publication by {@link Topic}
     * @throws DBException if catch SQLException
     */
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

    /**
     * @param con    connection to DataBase
     * @param userId id of {@link Person}
     * @return count of {@link Person} subscriptions
     * @throws DBException if catch SQLException
     */
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

    /**
     * @param con       connection to DataBase
     * @param searchReq part of {@link Publication} title to search in Database
     * @return count of found publications
     * @throws DBException if catch SQLException
     */
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

    /**
     * @param con      connection to DataBase
     * @param personId id of {@link Person}
     * @return list of {@link Publication}
     * @throws DBException if catch SQLException
     */
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

    /**
     * @param con     connection to DataBase
     * @param pubId   id of {@link Publication}
     * @param coverId id of {@link Image}
     * @throws DBException if catch SQLException
     */
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

    /**
     * @param con     connection to DataBase
     * @param sorting {@link Sorting} object that contain sorting attributes
     * @return List of entities
     * @throws DBException if catch SQLException
     */
    @Override
    public List<Publication> findAllSorted(Connection con, Sorting sorting) throws DBException {
        List<Publication> publications = new ArrayList<>();
        String query = "SELECT * " +
                "FROM publication p ORDER BY " + sorting.getSortingField() +
                " " + (sorting.getSortingType().equals("DESC") ? "DESC" : "") +
                " LIMIT " + sorting.getStarRecord() + "," + sorting.getPageSize() + "";
        return getPublications(con, publications, query);
    }

    /**
     * @param con connection to DataBase
     * @return count of all publications
     * @throws DBException if catch SQLException
     */
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

    /**
     * @param rs result set
     * @return entity with values from result set
     * @throws SQLException when something goes wrong
     */
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

