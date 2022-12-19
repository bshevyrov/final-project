package ua.com.company.dao.mysql.impl;

import ua.com.company.DBConstants;
import ua.com.company.dao.PublicationCommentDAO;
import ua.com.company.entity.PublicationComment;
import ua.com.company.entity.Sorting;
import ua.com.company.exception.DBException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class MysqlPublicationCommentDAOImpl implements PublicationCommentDAO {
    @Override
    public void create(Connection con, PublicationComment publicationComment) throws DBException {
        try (PreparedStatement stmt = con.prepareStatement(DBConstants.CREATE_PUBLICATION_COMMENT)) {
            int index = 0;
            stmt.setInt(++index, publicationComment.getPublicationId());
            stmt.setInt(++index, publicationComment.getPersonId());
            stmt.setString(++index, publicationComment.getText());
            stmt.execute();
        } catch (SQLException e) {
            throw new DBException(con + " PublicationComment= " +publicationComment, e);
        }
    }

    @Override
    public void update(Connection con, PublicationComment entity) throws DBException {

    }

    @Override
    public void delete(Connection con, int id) throws DBException {

    }

    @Override
    public Optional<PublicationComment> findById(Connection con, int id) throws DBException {
        return Optional.empty();
    }

    @Override
    public List<PublicationComment> findAll(Connection con) throws DBException {
        return null;
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
        try (Statement stmt = con.createStatement()) {
            try (ResultSet rs = stmt.executeQuery(query)) {
                while (rs.next()) {
                    commentList.add(mapPublicationComment(rs));
                }
            }
        } catch (SQLException e) {
            throw new DBException(con + query, e);
        }
        return commentList;
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
