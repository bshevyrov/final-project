package ua.com.company.dao.mysql.impl;

import ua.com.company.dao.PublicationCommentDAO;
import ua.com.company.entity.PublicationComment;
import ua.com.company.entity.Sorting;
import ua.com.company.exception.DBException;
import ua.com.company.utils.DBConstants;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class MysqlPublicationCommentDAOImpl implements PublicationCommentDAO {
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

    @Override
    public void update(Connection con, PublicationComment entity) throws DBException {
        throw new UnsupportedOperationException();
    }

    @Override
    public void delete(Connection con, int id) throws DBException {
        throw new UnsupportedOperationException();
    }

    @Override
    public Optional<PublicationComment> findById(Connection con, int id) throws DBException {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<PublicationComment> findAll(Connection con) throws DBException {
        throw new UnsupportedOperationException();
    }

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
