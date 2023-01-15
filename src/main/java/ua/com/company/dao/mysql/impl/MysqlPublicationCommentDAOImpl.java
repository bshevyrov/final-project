package ua.com.company.dao.mysql.impl;

import ua.com.company.dao.PublicationCommentDAO;
import ua.com.company.entity.Publication;
import ua.com.company.entity.PublicationComment;
import ua.com.company.entity.Sorting;
import ua.com.company.exception.DBException;
import ua.com.company.utils.DBConstants;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class MysqlPublicationCommentDAOImpl implements PublicationCommentDAO {
    /**
     * @param con                connection to DataBase
     * @param publicationComment entity to put in Database
     * @return id of created entity in DataBase
     * @throws DBException if catch SQLException
     */
    @Override
    public int create(Connection con, PublicationComment publicationComment) throws DBException {
        int id = 0;
        try (PreparedStatement ps = con.prepareStatement(DBConstants.CREATE_PUBLICATION_COMMENT, PreparedStatement.RETURN_GENERATED_KEYS)) {
            int index = 0;
            ps.setInt(++index, publicationComment.getPublicationId());
            ps.setInt(++index, publicationComment.getPersonId());
            ps.setString(++index, publicationComment.getText());
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                id = rs.getInt(1);
            }
        } catch (SQLException e) {
            throw new DBException("Connection: " + con + " PublicationComment= " + publicationComment, e);
        }
        return id;
    }

    /**
     * @param con                connection to DataBase
     * @param publicationComment entity to update in Database
     * @throws DBException if catch SQLException
     * @deprecated
     */
    @Override
    public void update(Connection con, PublicationComment publicationComment) throws DBException {
        throw new UnsupportedOperationException();
    }

    /**
     * @param con connection to DataBase
     * @param id  id of entity that need to delete
     * @throws DBException if catch SQLException
     * @deprecated
     */
    @Override
    public void delete(Connection con, int id) throws DBException {
        throw new UnsupportedOperationException();
    }

    /**
     * @param con connection to DataBase
     * @param id  of entity that want to get
     * @return found entity or Optional.empty() of entity
     * @throws DBException if catch SQLException
     * @deprecated
     */
    @Override
    public Optional<PublicationComment> findById(Connection con, int id) throws DBException {
        throw new UnsupportedOperationException();
    }

    /**
     * @param con connection to DataBase
     * @return List of all entity
     * @throws DBException if catch SQLException
     * @deprecated
     */
    @Override
    public List<PublicationComment> findAll(Connection con) throws DBException {
        throw new UnsupportedOperationException();
    }

    /**
     * @param con           connection to DataBase
     * @param sorting       {@link Sorting} object that contain sorting attributes
     * @param publicationId id of {@link Publication}
     * @return List of all comments of specific {@link Publication}
     * @throws DBException if catch SQLException
     */
    @Override
    public List<PublicationComment> findAllByPublicationId(Connection con, Sorting sorting, int publicationId) throws DBException {
        List<PublicationComment> commentList = new ArrayList<>();
        String query = "SELECT p.username, i.path, pc.* " +
                "FROM publication_comment pc LEFT JOIN person p on p.id = pc.person_id " +
                "LEFT JOIN image i on p.image_id = i.id " +
                " WHERE publication_id = " + publicationId + " ORDER BY " + sorting.getSortingField() +
                " " + (sorting.getSortingType().equals("DESC") ? "DESC" : "") +
                " LIMIT " + sorting.getStarRecord() + "," + sorting.getPageSize() + "";
        try (Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                commentList.add(mapPublicationComment(rs));
            }
        } catch (SQLException e) {
            throw new DBException("Connection: " + con + " and query= " + query, e);
        }
        return commentList;
    }

    /**
     * @param con           connection to DataBase
     * @param publicationId id of {@link Publication}
     * @return count of commentary
     * @throws DBException if catch SQLException
     */
    @Override
    public int countAllByPublicationId(Connection con, int publicationId) throws DBException {
        int count = -1;
        try (PreparedStatement stmt = con.prepareStatement(DBConstants.COUNT_PUBLICATION_COMMENT_BY_PUBLICATION_ID)) {
            stmt.setInt(1, publicationId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                count = rs.getInt("count");
            }
        } catch (SQLException e) {
            throw new DBException("Connection: " + con + "publicationId= " + publicationId, e);
        }
        return count;
    }

    /**
     * @param rs
     * @return entity with values from result set
     * @throws SQLException
     */
    private PublicationComment mapPublicationComment(ResultSet rs) throws SQLException {
        PublicationComment publicationComment = new PublicationComment();
        publicationComment.setUsername(rs.getString(DBConstants.F_PERSON_USERNAME));
        publicationComment.setUpdateDate(rs.getTimestamp(DBConstants.F_PUBLICATION_COMMENT_UPDATE_DATE));
        publicationComment.setCreateDate(rs.getTimestamp(DBConstants.F_PUBLICATION_COMMENT_CREATE_DATE));
        publicationComment.setAvatarPath(rs.getString(DBConstants.F_IMAGE_PATH));
        publicationComment.setText(rs.getString(DBConstants.F_PUBLICATION_COMMENT_TEXT));
        return publicationComment;
    }
}
