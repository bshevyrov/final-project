package ua.com.company.dao.derby.impl;

import ua.com.company.utils.DBConstants;
import ua.com.company.dao.ImageDAO;
import ua.com.company.entity.Image;
import ua.com.company.exception.DBException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class DerbyImageDAOImpl implements ImageDAO {
    @Override
    public void create(Connection con, Image image) throws DBException {
        try (PreparedStatement stmt = con.prepareStatement(DBConstants.CREATE_IMAGE)) {
            int index = 0;
            stmt.setString(++index, image.getName());
            stmt.setString(++index, image.getPath());
            stmt.execute();
        } catch (SQLException e) {
            throw new DBException("Connection: " + con + " and " + image, e);
        }
    }

    @Override
    public void update(Connection con, Image image) throws DBException {
        try (PreparedStatement stmt = con.prepareStatement(DBConstants.UPDATE_IMAGE)) {
            int index = 0;
            stmt.setString(++index, image.getName());
            stmt.setString(++index, image.getPath());
            stmt.setInt(++index, image.getId());
            stmt.execute();
        } catch (SQLException e) {
            throw new DBException("Connection: " + con + " and " + image, e);
        }
    }

    @Override
    public void delete(Connection con, int id) throws DBException {
        try (PreparedStatement stmt = con.prepareStatement(DBConstants.DELETE_IMAGE)) {
            stmt.setInt(1, id);
            stmt.execute();
        } catch (SQLException e) {
            throw new DBException("Connection: " + con + " and id= " + id, e);
        }
    }

    @Override
    public Optional<Image> findById(Connection con, int id) throws DBException {
        Optional<Image> image = Optional.empty();
        try (PreparedStatement stmt = con.prepareStatement(DBConstants.FIND_IMAGE_BY_ID)) {
            stmt.setInt(1, id);
            stmt.executeQuery();
            ResultSet rs = stmt.getResultSet();
            if (rs.next()) {
                image = Optional.of(mapImage(rs));
            }
        } catch (SQLException e) {
            throw new DBException("Connection: " + con + " and id= " + id, e);
        }
        return image;
    }

    @Override
    public List<Image> findAll(Connection con) throws DBException {
        List<Image> imageList = new ArrayList<>();
        try (Statement stmt = con.createStatement()) {
            stmt.executeQuery(DBConstants.FIND_ALL_IMAGES);
            ResultSet rs = stmt.getResultSet();
            while (rs.next()) {
                imageList.add(mapImage(rs));
            }
        } catch (SQLException e) {
            throw new DBException("Connection: " + con, e);
        }
        return imageList;
    }

    @Override
    public Optional<Image> findByPublicationId(Connection con, int id) throws DBException {
        Optional<Image> image = Optional.empty();
        try (PreparedStatement stmt = con.prepareStatement(DBConstants.FIND_IMAGE_BY_PUBLICATION_ID)) {
            stmt.setInt(1, id);
            stmt.executeQuery();
            ResultSet rs = stmt.getResultSet();
            if (rs.next()) {
                image = Optional.of(mapImage(rs));
            }
        } catch (SQLException e) {
            throw new DBException("Connection: " + con + " and id= " + id, e);
        }
        return image;
    }

    @Override
    public Optional<Image> findByPath(Connection con, String path) throws DBException {
        Optional<Image> image = Optional.empty();
        try (PreparedStatement stmt = con.prepareStatement(DBConstants.FIND_IMAGE_BY_PATH)) {
            stmt.setString(1, path);
            stmt.executeQuery();
            ResultSet rs = stmt.getResultSet();
            if (rs.next()) {
                image = Optional.of(mapImage(rs));
            }
        } catch (SQLException e) {
            throw new DBException("Connection: " + con + " and path= " + path, e);
        }
        return image;
    }

    private Image mapImage(ResultSet rs) throws SQLException {
        Image image = new Image();
        image.setId(rs.getInt(DBConstants.F_IMAGE_ID));
        image.setName(rs.getString(DBConstants.F_IMAGE_NAME));
        image.setPath(rs.getString(DBConstants.F_IMAGE_PATH));
        image.setCreateDate(rs.getTimestamp(DBConstants.F_IMAGE_CREATE_DATE));
        image.setUpdateDate(rs.getTimestamp(DBConstants.F_IMAGE_UPDATE_DATE));
        return image;
    }
}