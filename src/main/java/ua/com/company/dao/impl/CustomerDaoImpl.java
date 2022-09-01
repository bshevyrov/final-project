package ua.com.company.dao.impl;

import ua.com.company.DBConstants;
import ua.com.company.config.impl.DBDataSourceImpl;
import ua.com.company.dao.CustomerDao;
import ua.com.company.entity.user.Customer;
import ua.com.company.utils.PasswordUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CustomerDaoImpl implements CustomerDao {

    @Override
    public void create(Customer customer) {
        try (Connection con = DBDataSourceImpl.getInstance().getDataSource().getConnection();
             PreparedStatement stmt = con.prepareStatement(DBConstants.CREATE_CUSTOMER)) {
            String encryptedPass = PasswordUtil.encryptPassword(customer.getPassword());
            int index = 0;
            stmt.setString(++index, customer.getEmail());
            stmt.setString(++index, customer.getFirstName());
            stmt.setString(++index, customer.getLastName());
            stmt.setTimestamp(++index, customer.getCreateDate());
            stmt.setTimestamp(++index, customer.getUpdateDate());
            stmt.setDouble(++index, customer.getFunds());
            stmt.setString(++index, encryptedPass);
            stmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Customer customer) {
        try (Connection con = DBDataSourceImpl.getInstance().getDataSource().getConnection();
             PreparedStatement stmt = con.prepareStatement(DBConstants.UPDATE_CUSTOMER)) {
            String encryptedPass = PasswordUtil.encryptPassword(customer.getPassword());
            int index = 0;
            stmt.setInt(++index, customer.getId());
            stmt.setString(++index, customer.getEmail());
            stmt.setString(++index, customer.getFirstName());
            stmt.setString(++index, customer.getLastName());
            stmt.setTimestamp(++index, customer.getCreateDate());
            stmt.setTimestamp(++index, customer.getUpdateDate());
            stmt.setDouble(++index, customer.getFunds());
            stmt.setString(++index, encryptedPass);
            stmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Customer customer) {
        try (Connection con = DBDataSourceImpl.getInstance().getDataSource().getConnection();
             PreparedStatement stmt = con.prepareStatement(DBConstants.DELETE_CUSTOMER)) {
            stmt.setInt(1, customer.getId());
            stmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Optional<Customer> findById(int id) {
        Customer customer = null;
        try (Connection con = DBDataSourceImpl.getInstance().getDataSource().getConnection();
             Statement stmt = con.createStatement()) {
            ResultSet rs = stmt.executeQuery(DBConstants.FIND_ALL_CUSTOMERS);
            while (rs.next()) {
                customer=mapCustomer(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.ofNullable(customer);
    }

    @Override
    public List<Customer> findAll() {
        List<Customer> customers = new ArrayList<>();
        try (Connection con = DBDataSourceImpl.getInstance().getDataSource().getConnection();
             Statement stmt = con.createStatement()) {
            ResultSet rs = stmt.executeQuery(DBConstants.FIND_ALL_CUSTOMERS);
            while (rs.next()) {
                customers.add(mapCustomer(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return customers;
    }

    private Customer mapCustomer(ResultSet rs) throws SQLException {
        Customer customer = new Customer();
        customer.setId(rs.getInt(DBConstants.F_CUSTOMER_ID));
        customer.setEmail(rs.getString(DBConstants.F_CUSTOMER_EMAIL));
        customer.setCreateDate(rs.getTimestamp(DBConstants.F_CUSTOMER_CREATE_DATE));
        customer.setUpdateDate(rs.getTimestamp(DBConstants.F_CUSTOMER_UPDATE_DATE));
        customer.setFirstName(rs.getString(DBConstants.F_CUSTOMER_FIRST_NAME));
        customer.setLastName(rs.getString(DBConstants.F_CUSTOMER_LAST_NAME));
        customer.setFunds(rs.getDouble(DBConstants.F_CUSTOMER_FUNDS));

        return customer;
    }
}
