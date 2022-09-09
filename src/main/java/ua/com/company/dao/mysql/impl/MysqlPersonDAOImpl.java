package ua.com.company.dao.mysql.impl;

import ua.com.company.DBConstants;
import ua.com.company.config.impl.DBDataSourceImpl;
import ua.com.company.dao.PersonDAO;
import ua.com.company.entity.Person;
import ua.com.company.entity.Publication;
import ua.com.company.exception.DBException;
import ua.com.company.type.RoleType;
import ua.com.company.type.StatusType;
import ua.com.company.utils.PasswordUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class MysqlPersonDAOImpl implements PersonDAO {

    @Override
    public void create(Person person) {
        try (Connection con = DBDataSourceImpl.getInstance().getDataSource().getConnection();
             PreparedStatement stmt = con.prepareStatement(DBConstants.CREATE_PERSON)) {
            String encryptedPass = PasswordUtil.encryptPassword(person.getPassword());
            int index = 0;
            stmt.setString(++index, person.getEmail());
            stmt.setString(++index, encryptedPass);
//            stmt.setString(++index, person.getRole().toString());
            stmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Person person) throws DBException {
        if (isExist(person.getId())) {
            try (Connection con = DBDataSourceImpl.getInstance().getDataSource().getConnection();
                 PreparedStatement stmt = con.prepareStatement(DBConstants.UPDATE_PERSON)) {
               // String encryptedPass = PasswordUtil.encryptPassword(person.getPassword());
                int index = 0;
                stmt.setString(++index, person.getEmail());
               // stmt.setString(++index, encryptedPass);
                person.getRole().toString();
                stmt.setString(++index, person.getRole().toString());
                stmt.setString(++index, person.getStatus().toString());
                stmt.setInt(++index, person.getId());

             /*   stmt.setString(++index, person.getFirstName());
                stmt.setString(++index, person.getLastName());
                stmt.setDouble(++index, person.getFunds());*/


                stmt.execute();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            throw new DBException("Cant find person with id " + person.getId());
        }
    }

    @Override
    public void delete(int id) {
        try (Connection con = DBDataSourceImpl.getInstance().getDataSource().getConnection();
             PreparedStatement stmt = con.prepareStatement(DBConstants.DELETE_PERSON)) {
            stmt.setInt(1, id);
            stmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Optional<Person> findPersonByEmail(String email) {
        Person Person = null;
        try (Connection con = DBDataSourceImpl.getInstance().getDataSource().getConnection();
             PreparedStatement stmt = con.prepareStatement(DBConstants.FIND_PERSON_BY_EMAIL)) {
            stmt.setString(1,email);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Person = mapPerson(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.ofNullable(Person);
    }

    @Override
    public void addPublicationForPerson(Person person, Publication publication) {
     try(   Connection con = DBDataSourceImpl.getInstance().getDataSource().getConnection();
        PreparedStatement stmt = con.prepareStatement(DBConstants.ADD_PUBLICATION_TO_PERSON)){
         int index=0;
         stmt.setInt(++index,person.getId());
         stmt.setInt(++index,publication.getId());
         stmt.execute();
     } catch (SQLException e) {
         e.printStackTrace();
     }

    }

    @Override
    public Optional<Person> findById(int id) {
        Person person = null;
        try (Connection con = DBDataSourceImpl.getInstance().getDataSource().getConnection();
             PreparedStatement stmt = con.prepareStatement(DBConstants.FIND_PERSON_BY_ID)) {
           stmt.setInt(1,id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                person = mapPerson(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.ofNullable(person);
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

    public boolean isExist(String email) {
        int count = 0;
        try (Connection con = DBDataSourceImpl.getInstance().getDataSource().getConnection();
             PreparedStatement stmt = con.prepareStatement(DBConstants.COUNT_PERSON_BY_EMAIL)) {
            stmt.setString(1, email);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                count = rs.getInt("count");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return count == 1;
    }

    public boolean isExist(int id) {
        int count = 0;
        try (Connection con = DBDataSourceImpl.getInstance().getDataSource().getConnection();
             PreparedStatement stmt = con.prepareStatement(DBConstants.COUNT_PERSON_BY_ID)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                count = rs.getInt("count");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return count == 1;
    }

    private Person mapPerson(ResultSet rs) throws SQLException {
        Person person = new Person();
        person.setId(rs.getInt(DBConstants.F_PERSON_ID));
        person.setEmail(rs.getString(DBConstants.F_PERSON_EMAIL));
        person.setRole(RoleType.valueOf(rs.getString(DBConstants.F_PERSON_ROLE)));
        person.setStatus(StatusType.valueOf(rs.getString(DBConstants.F_PERSON_STATUS)));
        return person;
    }
}
