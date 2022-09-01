package ua.com.company.dao.impl;

import ua.com.company.DBConstants;
import ua.com.company.config.impl.DBDataSourceImpl;
import ua.com.company.dao.AdminDao;
import ua.com.company.entity.user.Admin;
import ua.com.company.exception.DBException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


public class AdminDaoImpl implements AdminDao {


    @Override
    public void create(Admin admin) {
        try (Connection con = DBDataSourceImpl.getInstance().getDataSource().getConnection();
             PreparedStatement stmt = con.prepareStatement(DBConstants.CREATE_ADMIN)) {
            int index = 0;
            stmt.setString(++index, admin.getEmail());
            stmt.setTimestamp(++index, admin.getCreateDate());
            stmt.setTimestamp(++index, admin.getUpdateDate());
            stmt.setString(++index, admin.getPassword());
            stmt.setString(++index, admin.getRoleType().toString());
            stmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Admin admin) {
        try (Connection con = DBDataSourceImpl.getInstance().getDataSource().getConnection();
             PreparedStatement stmt = con.prepareStatement(DBConstants.UPDATE_ADMIN)) {
            int index = 0;
            stmt.setInt(++index, admin.getId());
            stmt.setString(++index, admin.getEmail());
            stmt.setTimestamp(++index, new Timestamp(System.currentTimeMillis()));
            stmt.setString(++index, admin.getPassword());
            stmt.setString(++index, admin.getRoleType().toString());
            stmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Admin admin) {
        try (Connection con = DBDataSourceImpl.getInstance().getDataSource().getConnection();
             PreparedStatement stmt = con.prepareStatement(DBConstants.DELETE_ADMIN)) {
            stmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Optional<Admin> findById(int id) {
        Admin admin = null;
        try (Connection con = DBDataSourceImpl.getInstance().getDataSource().getConnection();
             PreparedStatement stmt = con.prepareStatement(DBConstants.FINd_ADMIN_BY_ID)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                admin = mapAdmin(rs);
            }
        } catch (SQLException e) {
            //Logger log ERROR
            e.printStackTrace();
            throw new DBException("Error while findAll Admin" + this.getClass().getSimpleName(), e);
            //130 time
        }
        return Optional.ofNullable(admin);
    }

    @Override
    public List<Admin> findAll() throws DBException {
        List<Admin> admins = new ArrayList<>();
        try (Connection con = DBDataSourceImpl.getInstance().getDataSource().getConnection();
             Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(DBConstants.FIND_ALL_ADMINS);) {

            while (rs.next()) {
                admins.add(mapAdmin(rs));
            }
        } catch (SQLException e) {
            //Logger log ERROR
            e.printStackTrace();
            throw new DBException("Error while findAll Admin" + this.getClass().getSimpleName(), e);
            //130 time
        }
        return admins;
    }

    private Admin mapAdmin(ResultSet rs) throws SQLException {
        Admin admin = new Admin();
        admin.setId(rs.getInt(DBConstants.F_ADMIN_ID));
        admin.setEmail(rs.getString(DBConstants.F_ADMIN_EMAIL));
        admin.setCreateDate(rs.getTimestamp(DBConstants.F_ADMIN_CREATE_DATE));
        admin.setUpdateDate(rs.getTimestamp(DBConstants.F_ADMIN_UPDATE_DATE));
        return admin;
    }
}
