package ua.com.company.dao.impl;

import ua.com.company.DBConstants;
import ua.com.company.config.impl.DBDataSourceImpl;
import ua.com.company.dao.PersonDao;
import ua.com.company.entity.Person;
import ua.com.company.utils.PasswordUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PersonDaoImpl implements PersonDao {

    @Override
    public void create(Person person) {
        try (Connection con = DBDataSourceImpl.getInstance().getDataSource().getConnection();
             PreparedStatement stmt = con.prepareStatement(DBConstants.CREATE_PERSON)) {
            String encryptedPass = PasswordUtil.encryptPassword(person.getPassword());
            int index = 0;
            stmt.setString(++index, person.getEmail());
            stmt.setString(++index, encryptedPass);
            stmt.setString(++index, person.getRole().toString());
            stmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Person person) {
        try (Connection con = DBDataSourceImpl.getInstance().getDataSource().getConnection();
             PreparedStatement stmt = con.prepareStatement(DBConstants.UPDATE_PERSON)) {
            String encryptedPass = PasswordUtil.encryptPassword(person.getPassword());
            int index = 0;
            stmt.setInt(++index, person.getId());
            stmt.setString(++index, person.getEmail());
            stmt.setString(++index, encryptedPass);

            stmt.setString(++index, person.getFirstName());
            stmt.setString(++index, person.getLastName());
            stmt.setDouble(++index, person.getFunds());
            stmt.setString(++index, person.getRole().toString());
            stmt.setString(++index, person.getStatus().toString());


            stmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Person Person) {
        try (Connection con = DBDataSourceImpl.getInstance().getDataSource().getConnection();
             PreparedStatement stmt = con.prepareStatement(DBConstants.DELETE_PERSON)) {
            stmt.setInt(1, Person.getId());
            stmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Optional<Person> findById(int id) {
        Person Person = null;
        try (Connection con = DBDataSourceImpl.getInstance().getDataSource().getConnection();
             Statement stmt = con.createStatement()) {
            ResultSet rs = stmt.executeQuery(DBConstants.FIND_PERSON_BY_ID);
            while (rs.next()) {
                Person = mapPerson(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.ofNullable(Person);
    }

    @Override
    public List<Person> findAll() {
        List<Person> Persons = new ArrayList<>();
        try (Connection con = DBDataSourceImpl.getInstance().getDataSource().getConnection();
             Statement stmt = con.createStatement()) {
            ResultSet rs = stmt.executeQuery(DBConstants.FIND_ALL_PERSONS);
            while (rs.next()) {
                Persons.add(mapPerson(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Persons;
    }

    private Person mapPerson(ResultSet rs) throws SQLException {
        Person Person = new Person();
        Person.setId(rs.getInt(DBConstants.F_PERSON_ID));
        Person.setEmail(rs.getString(DBConstants.F_PERSON_EMAIL));
        Person.setCreateDate(rs.getTimestamp(DBConstants.F_PERSON_CREATE_DATE));
        Person.setUpdateDate(rs.getTimestamp(DBConstants.F_PERSON_UPDATE_DATE));
        Person.setFirstName(rs.getString(DBConstants.F_PERSON_FIRST_NAME));
        Person.setLastName(rs.getString(DBConstants.F_PERSON_LAST_NAME));
        Person.setFunds(rs.getDouble(DBConstants.F_PERSON_FUNDS));

        return Person;
    }

    @Override
    public Optional<Person> findPersonByEmail(String email) {
        return Optional.empty();
    }
}
