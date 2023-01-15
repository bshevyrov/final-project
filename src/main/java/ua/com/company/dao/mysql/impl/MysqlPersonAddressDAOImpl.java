package ua.com.company.dao.mysql.impl;

import ua.com.company.dao.PersonAddressDAO;
import ua.com.company.entity.PersonAddress;
import ua.com.company.exception.DBException;
import ua.com.company.utils.DBConstants;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class MysqlPersonAddressDAOImpl implements PersonAddressDAO {

    @Override
    public int create(Connection con, PersonAddress personAddress) throws DBException {
        int id = 0;
        try (PreparedStatement ps = con.prepareStatement(DBConstants.CREATE_PERSON_ADDRESS,PreparedStatement.RETURN_GENERATED_KEYS)) {
            int index = 0;
            ps.setString(++index, personAddress.getFirstName());
            ps.setString(++index, personAddress.getLastName());
            ps.setString(++index, personAddress.getAddress());
            ps.setString(++index, personAddress.getCity());
            ps.setString(++index, personAddress.getCountry());
            ps.setString(++index, personAddress.getPhone());
            ps.setInt(++index, personAddress.getPostalCode());
            ps.setInt(++index, personAddress.getPersonId());
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                id = rs.getInt(1);
            }
        } catch (SQLException e) {
            throw new DBException("Connection: " + con + " and " + personAddress, e);
        }
        return id;
    }

    @Override
    public void update(Connection con, PersonAddress personAddress) throws DBException {
        try (PreparedStatement stmt = con.prepareStatement(DBConstants.UPDATE_PERSON_ADDRESS)) {
            int index = 0;
            stmt.setString(++index, personAddress.getFirstName());
            stmt.setString(++index, personAddress.getLastName());
            stmt.setString(++index, personAddress.getAddress());
            stmt.setString(++index, personAddress.getCity());
            stmt.setString(++index, personAddress.getCountry());
            stmt.setString(++index, personAddress.getPhone());
            stmt.setInt(++index, personAddress.getPostalCode());
            stmt.setInt(++index, personAddress.getId());
            stmt.execute();
        } catch (SQLException e) {
            throw new DBException("Connection: " + con + " and " + personAddress, e);
        }
    }

    @Override
    public void delete(Connection con, int id) throws DBException {
        throw new UnsupportedOperationException();

    }

    @Override
    public Optional<PersonAddress> findById(Connection con, int id) throws DBException {
        Optional<PersonAddress> personAddress = Optional.empty();
        try (PreparedStatement stmt = con.prepareStatement(DBConstants.FIND_PERSON_ADDRESS_BY_ID);) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                personAddress = Optional.of(mapPersonAddress(rs));
            }
        } catch (SQLException e) {
            throw new DBException("Connection: " + con + " and id= " + id, e);
        }
        return personAddress;
    }

    @Override
    public List<PersonAddress> findAll(Connection con) throws DBException {
        throw new UnsupportedOperationException();
    }

    @Override
    public Optional<PersonAddress> findByPersonId(Connection con, int userId) throws DBException {
        Optional<PersonAddress> personAddress = Optional.empty();
        try (PreparedStatement stmt = con.prepareStatement(DBConstants.FIND_PERSON_ADDRESS_BY_PERSON_ID);) {
            stmt.setInt(1, userId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                personAddress = Optional.of(mapPersonAddress(rs));
            }
        } catch (SQLException e) {
            throw new DBException("Connection: " + con + "userId= " + userId, e);
        }
        return personAddress;
    }

    private PersonAddress mapPersonAddress(ResultSet rs) throws SQLException {
        PersonAddress personAddress = new PersonAddress();
        personAddress.setId(rs.getInt(DBConstants.F_PERSON_ADDRESS_ID));
        personAddress.setFirstName(rs.getString(DBConstants.F_PERSON_ADDRESS_FIRST_NAME));
        personAddress.setLastName(rs.getString(DBConstants.F_PERSON_ADDRESS_LAST_NAME));
        personAddress.setAddress(rs.getString(DBConstants.F_PERSON_ADDRESS_ADDRESS));
        personAddress.setCity(rs.getString(DBConstants.F_PERSON_ADDRESS_CITY));
        personAddress.setCountry(rs.getString(DBConstants.F_PERSON_ADDRESS_COUNTRY));
        personAddress.setPhone(rs.getString(DBConstants.F_PERSON_ADDRESS_PHONE));
        personAddress.setPostalCode(rs.getInt(DBConstants.F_PERSON_ADDRESS_POSTAL_CODE));
        personAddress.setCreateDate(rs.getTimestamp(DBConstants.F_PERSON_ADDRESS_CREATE_DATE));
        personAddress.setUpdateDate(rs.getTimestamp(DBConstants.F_PERSON_ADDRESS_UPDATE_DATE));
        personAddress.setPersonId(rs.getInt(DBConstants.F_PERSON_ADDRESS_PERSON_ID));
        return personAddress;
    }
}

