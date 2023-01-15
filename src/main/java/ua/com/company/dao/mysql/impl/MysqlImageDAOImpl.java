package ua.com.company.dao.mysql.impl;

import ua.com.company.dao.ImageDAO;
import ua.com.company.entity.Image;
import ua.com.company.entity.Publication;
import ua.com.company.exception.DBException;
import ua.com.company.utils.DBConstants;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class MysqlImageDAOImpl implements ImageDAO {
    /**
     * @param con connection to DataBase
     * @param image entity to put in Database
     * @return id of created entity in DataBase
     * @throws DBException if catch SQLException
     */
    @Override
    public int create(Connection con, Image image) throws DBException {
        int id = 0;
        try (PreparedStatement ps = con.prepareStatement(DBConstants.CREATE_IMAGE, PreparedStatement.RETURN_GENERATED_KEYS)) {
            int index = 0;
            ps.setString(++index, image.getName());
            ps.setString(++index, image.getPath());
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                id = rs.getInt(1);
            }
        } catch (SQLException e) {
            throw new DBException("Connection: " + con + " and " + image, e);
        }
        return id;
    }

    /**
     * @param con connection to DataBase
     * @param image entity to put in Database
     * @throws DBException if catch {@link SQLException}
     */
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

    /**
     * @param con connection to DataBase
     * @param id id of entity that need to delete
     * @throws DBException if catch {@link SQLException}
     */
    @Override
    public void delete(Connection con, int id) throws DBException {
        try (PreparedStatement stmt = con.prepareStatement(DBConstants.DELETE_IMAGE)) {
            stmt.setInt(1, id);
            stmt.execute();
        } catch (SQLException e) {
            throw new DBException("Connection: " + con + " and id= " + id, e);
        }
    }

    /**
     * @param con connection to DataBase
     * @param id of entity that want to get
     * @return found entity or Optional.empty() of entity
     * @throws DBException if catch {@link SQLException}
     */
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

    /**
     * @param con connection to DataBase
     * @return List of all entity
     * @throws DBException if catch {@link SQLException}
     */
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

    /**
     * @param con connection to DataBase
     * @param path path to image
     * @return found entity or Optional.empty() of entity
     * @throws DBException if catch {@link SQLException}
     */
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

    /**
     * @param con connection to DataBase
     * @param id of {@link Publication} that store image id
     * @return found entity or Optional.empty() of entity
     * @throws DBException if catch {@link SQLException}
     */
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

    /**
     * 
     * @param rs result set
     * @return entity with values from result set
     * @throws DBException if catch {@link SQLException}
     */
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