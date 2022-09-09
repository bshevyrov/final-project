package ua.com.company.dao.mysql.impl;

import ua.com.company.DBConstants;
import ua.com.company.config.impl.DBDataSourceImpl;
import ua.com.company.dao.PublicationDAO;
import ua.com.company.entity.Image;
import ua.com.company.entity.Publication;
import ua.com.company.entity.Topic;
import ua.com.company.exception.DBException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class MysqlPublicationDAOImpl implements PublicationDAO {

    @Override
    public void create(Publication publication, Image... images) {
        Connection con = null;
        try {
            con = DBDataSourceImpl.getInstance().getDataSource().getConnection();
            con.setAutoCommit(false);
            con.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
            addPublication(con, publication);
            for (Image image : images) {
                addImageForPublication(con, publication.getId(), image);
            }
            con.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            rollback(con);
        } finally {
            close(con);
        }
    }

    private void addPublication(Connection con, Publication publication) throws SQLException {
        try (PreparedStatement stmt = con.prepareStatement(DBConstants.CREATE_PUBLICATION,
                Statement.RETURN_GENERATED_KEYS)) {
            int index = 0;
            stmt.setString(++index, publication.getTitle());
            stmt.setString(++index, publication.getSample());
            stmt.setDouble(++index, publication.getPrice());
            int count = stmt.executeUpdate();
            if (count > 0) {
                try (ResultSet rs = stmt.getGeneratedKeys()) {
                    if (rs.next()) {
                        publication.setId(rs.getInt(1));
                    }
                }
            }
        }
    }
    private void addImageForPublication(Connection con,int pubId, Image image) throws SQLException {
        PreparedStatement stmt=null;
        try{
            stmt = con.prepareStatement(DBConstants.ADD_IMAGE_TO_PUBLICATION);
            int index=0;
            stmt.setInt(++index,pubId);
            stmt.setString(++index,image.getName());
            stmt.setString(++index,image.getPath());
            stmt.execute();
        } finally {
            close(stmt);
        }

    }

    private void rollback(Connection con) {
        try {
            con.rollback();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void create(Publication publication) {

    }

    @Override
    public void update(Publication publication) {
        try (Connection con = DBDataSourceImpl.getInstance().getDataSource().getConnection();
             PreparedStatement stmt = con.prepareStatement(DBConstants.UPDATE_PUBLICATION)) {
            int index = 0;
            stmt.setString(++index, publication.getTitle());
            stmt.setString(++index, publication.getSample());
            stmt.setDouble(++index, publication.getPrice());
            stmt.setInt(++index, publication.getId());
            stmt.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(int id) {
        try (Connection con = DBDataSourceImpl.getInstance().getDataSource().getConnection();
             PreparedStatement stmt = con.prepareStatement(DBConstants.DELETE_PUBLICATION)) {
            stmt.setInt(1, id);
            stmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Optional<Publication> findById(int id) throws DBException {

        Publication publication = null;
        try (Connection con = DBDataSourceImpl.getInstance().getDataSource().getConnection();
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
    public List<Publication> findAll() throws DBException {
        List<Publication> publications = new ArrayList<>();
        try (Connection con = DBDataSourceImpl.getInstance().getDataSource().getConnection();
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
    public List<Publication> findAllByTitle(String pattern) throws DBException {

        List<Publication> publications = new ArrayList<>();
        try (Connection con = DBDataSourceImpl.getInstance().getDataSource().getConnection();
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
    public void addTopicForPublication(Publication publication, Topic... topics) {

    }

   /* @Override
    public void addImage(Connection con, Publication publication, Image... images) {
       try {

           PreparedStatement stmt = con.prepareStatement(DBConstants.ADD_IMAGE_TO_PUBLICATION);
      int index=0;
      stmt.setInt(++index,publication.getId());
      stmt.setString(++index,images.);

       } catch (SQLException e) {
           e.printStackTrace();
       }


    }*/

//    public void addNewTopic(Topic topic, PublicationTopic... publicationTopic) throws DBException {
//        Connection con = null;
//        PreparedStatement pStmt = null;
//        try {
//            con = DBDataSourceImpl.getInstance().getDataSource().getConnection();
//            pStmt = con.prepareStatement("INSERT INTO topic (title) VALUES (?);", Statement.RETURN_GENERATED_KEYS);
//            pStmt.setString(1, topic.getTitle());
////2:23
//
//            try (ResultSet rs = pStmt.executeQuery()) {
//                rs.next();
//            }
//
//        } catch (SQLException e) {
//            //log
//            e.printStackTrace();
//            throw new DBException("GOOD INFORMATION ERORR", e);
//        } finally {
//            close(pStmt);
//            close(con);
//        }
//    }


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
        publication.setCreateDate(rs.getTimestamp(DBConstants.F_PUBLICATION_CREATE_DATE));
        publication.setUpdateDate(rs.getTimestamp(DBConstants.F_PUBLICATION_UPDATE_DATE));
        publication.setSample(rs.getString(DBConstants.F_PUBLICATION_SAMPLE));
        publication.setPrice(rs.getDouble(DBConstants.F_PUBLICATION_PRICE));
        //  publication.setTopic();
        return publication;
    }
}

