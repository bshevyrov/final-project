package ua.com.company.dao.impl;

import ua.com.company.DBConstants;
import ua.com.company.config.impl.DBDataSourceImpl;
import ua.com.company.dao.PublicationDao;
import ua.com.company.entity.Publication;
import ua.com.company.entity.PublicationTopic;
import ua.com.company.entity.Topic;
import ua.com.company.exception.DBException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PublicationDaoImpl implements PublicationDao {

    @Override
    public void create(Publication publication) {
        try (Connection con = DBDataSourceImpl.getInstance().getDataSource().getConnection();
             PreparedStatement stmt = con.prepareStatement(DBConstants.CREATE_PUBLICATION)) {
            int index = 0;
            stmt.setString(++index, publication.getTitle());
            stmt.setTimestamp(++index, publication.getCreateDate());
            stmt.setTimestamp(++index, publication.getUpdateDate());
            stmt.setString(++index, publication.getSample());
            stmt.setDouble(++index, publication.getPrice());
            stmt.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Publication publication) {
        try (Connection con = DBDataSourceImpl.getInstance().getDataSource().getConnection();
             PreparedStatement stmt = con.prepareStatement(DBConstants.UPDATE_PUBLICATION)) {
            int index = 0;
            stmt.setInt(++index, publication.getId());
            stmt.setString(++index, publication.getTitle());
            stmt.setTimestamp(++index, publication.getCreateDate());
            stmt.setTimestamp(++index, publication.getUpdateDate());
            stmt.setString(++index, publication.getSample());
            stmt.setDouble(++index, publication.getPrice());
            stmt.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Publication publication) {
        try (Connection con = DBDataSourceImpl.getInstance().getDataSource().getConnection();
             PreparedStatement stmt = con.prepareStatement(DBConstants.DELETE_PUBLICATION)) {
            stmt.setInt(1, publication.getId());
            stmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Optional<Publication> findById(int id) {

        Publication publication = null;
        try (Connection con = DBDataSourceImpl.getInstance().getDataSource().getConnection();
             Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(DBConstants.FIND_PUBLICATION_BY_ID)) {
            while (rs.next()) {
               publication= mapPublication(rs);
            }
        } catch (SQLException e) {
            //log
            e.printStackTrace();
            throw new DBException("GOOD INFORMATION ERORR", e);
        }
        return Optional.ofNullable(publication);


        return Optional.empty();
    }

    @Override
    public List<Publication> findAll() {
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
    public List<Publication> findAllByTitle(String pattern) {

        List<Publication> publications = new ArrayList<>();
        try (Connection con = DBDataSourceImpl.getInstance().getDataSource().getConnection();
             PreparedStatement pStmt = con.prepareStatement(DBConstants.FIND_ALL_PUBLICATIONS_BY_TITLE)) {
//            int k =1;

            pStmt.setString(1, "%" + escapeForLike(pattern) + "%");
//            pStmt.setString(1, pattern);
            try (ResultSet rs = pStmt.executeQuery()) {
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

    public void addNewTopic(Topic topic, PublicationTopic... publicationTopic) throws DBException {
        Connection con = null;
        PreparedStatement pStmt = null;
        try {
            con = DBDataSourceImpl.getInstance().getDataSource().getConnection();
            pStmt = con.prepareStatement("INSERT INTO topic (title) VALUES (?);", Statement.RETURN_GENERATED_KEYS);
            pStmt.setString(1, topic.getTitle());
//2:23

            try (ResultSet rs = pStmt.executeQuery()) {
                rs.next();
            }

        } catch (SQLException e) {
            //log
            e.printStackTrace();
            throw new DBException("GOOD INFORMATION ERORR", e);
        } finally {
            close(pStmt);
            close(con);
        }
    }

    private void close(AutoCloseable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
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
        publication.setCreateDate(rs.getDate(DBConstants.F_PUBLICATION_CREATE_DATE));
        publication.setUpdateDate(rs.getDate(DBConstants.F_PUBLICATION_UPDATE_DATE));
        publication.setSample(rs.getString(DBConstants.F_PUBLICATION_SAMPLE));
        publication.setPrice(rs.getDouble(DBConstants.F_PUBLICATION_PRICE));
        //  publication.setTopic();
        return publication;
    }
}

