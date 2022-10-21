package ua.com.company.dao.mysql.impl;

import ua.com.company.DBConstants;
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
    public int create(Person person) throws DBException {
        int id = -1;
        try (Connection con = getConnection();
             PreparedStatement stmt = con.prepareStatement(DBConstants.CREATE_PERSON, Statement.RETURN_GENERATED_KEYS)) {
            String encryptedPass = PasswordUtil.encryptPassword(person.getPassword());
            int index = 0;
            stmt.setString(++index, person.getUsername());
            stmt.setString(++index, person.getEmail());
            stmt.setString(++index, encryptedPass);
//            stmt.setString(++index, person.getRole().toString());
            int count = stmt.executeUpdate();
            if (count > 0) {
                try (ResultSet rs = stmt.getGeneratedKeys()) {
                    if (rs.next()) {
                        id = rs.getInt(1);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DBException(e);
        }
        return id;
    }

    @Override
    public void update(Person person) throws DBException {
        if (isExist(person.getId())) {
            try (Connection con = getConnection();
                 PreparedStatement stmt = con.prepareStatement(DBConstants.UPDATE_PERSON)) {
                // String encryptedPass = PasswordUtil.encryptPassword(person.getPassword());
                int index = 0;
                stmt.setString(++index, person.getEmail());
                // stmt.setString(++index, encryptedPass);
                stmt.setString(++index, person.getRole().toString());
                stmt.setString(++index, person.getStatus().toString());
                stmt.setInt(++index, person.getId());

             /*   stmt.setString(++index, person.getFirstName());
                stmt.setString(++index, person.getLastName());
                stmt.setDouble(++index, person.getFunds());*/


                stmt.execute();
            } catch (SQLException e) {
                e.printStackTrace();
                throw new DBException(e);
            }
        } else {
            throw new DBException("Cant find person with id " + person.getId());
        }
    }

    @Override
    public void delete(int id) throws DBException {
        try (Connection con = getConnection();
             PreparedStatement stmt = con.prepareStatement(DBConstants.DELETE_PERSON)) {
            stmt.setInt(1, id);
            stmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DBException(e);
        }
    }

    @Override
    public Optional<Person> findPersonByEmail(String email) throws DBException {
        Person person = null;
        try (Connection con = getConnection();
             PreparedStatement stmt = con.prepareStatement(DBConstants.FIND_PERSON_BY_EMAIL);) {
            stmt.setString(1, email);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                person = mapPerson(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DBException(e);
        }
        return Optional.ofNullable(person);
    }

    @Override
    public void addPublicationForPerson(Person person, Publication publication) throws DBException {
        try (Connection con = getConnection();
             PreparedStatement stmt = con.prepareStatement(DBConstants.ADD_PUBLICATION_TO_PERSON)) {
            int index = 0;
            stmt.setInt(++index, person.getId());
            stmt.setInt(++index, publication.getId());
            stmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DBException(e);
        }
    }

    @Override
    public Optional<Person> findPersonByUsername(String username) throws DBException {
        Person Person = null;
        try (Connection con = getConnection();
             PreparedStatement stmt = con.prepareStatement(DBConstants.FIND_PERSON_BY_USERNAME)) {
            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Person = mapPerson(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DBException(e);
        }
        return Optional.ofNullable(Person);
    }

    @Override
    public Optional<Person> findById(int id) throws DBException {
        Person person = null;
        try (Connection con = getConnection();
             PreparedStatement stmt = con.prepareStatement(DBConstants.FIND_PERSON_BY_ID)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                person = mapPerson(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DBException(e);
        }
        return Optional.ofNullable(person);
    }

    @Override
    public List<Person> findAll() throws DBException {
        List<Person> persons = new ArrayList<>();
        try (Connection con = getConnection();
             Statement stmt = con.createStatement()) {
            ResultSet rs = stmt.executeQuery(DBConstants.FIND_ALL_PERSONS);
            while (rs.next()) {
                persons.add(mapPerson(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DBException(e);
        }
        return persons;
    }

    public boolean isExistByEmail(String email) throws DBException {
        int count = 0;
        try (Connection con = getConnection();
             PreparedStatement stmt = con.prepareStatement(DBConstants.COUNT_PERSON_BY_EMAIL)) {
            stmt.setString(1, email);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                count = rs.getInt("count");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DBException(e);
        }
        return count == 1;
    }

    @Override
    public boolean isExistByUsername(String username) throws DBException {
        int count = 0;
        try (Connection con = getConnection();
             PreparedStatement stmt = con.prepareStatement(DBConstants.COUNT_PERSON_BY_USERNAME)) {
            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                count = rs.getInt("count");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DBException(e);
        }
        return count == 1;
    }

    @Override
    public boolean changeStatusById(int id) throws DBException {
        boolean completed;
        try (Connection con = getConnection();
             PreparedStatement stmt = con.prepareStatement(DBConstants.CHANGE_USER_STATUS_BY_ID)) {
            int newStatus = checkStatusById(con, id) == 1 ? 2 : 1;
            int index = 0;
            stmt.setInt(++index, newStatus);
            stmt.setInt(++index, id);
            completed = stmt.execute();
        } catch (SQLException e) {
            completed = false;
            e.printStackTrace();
            throw new DBException(e);
        }
        return completed;
    }

    private int checkStatusById(Connection con, int id) throws SQLException {
        ResultSet rs = null;
        try (PreparedStatement stmt = con.prepareStatement(DBConstants.CHECK_USER_STATUS_BY_ID)) {
            stmt.setInt(1, id);
            rs = stmt.executeQuery();
            int statusCode = -1;
            if (rs.next()) {
                statusCode = rs.getInt(DBConstants.F_PERSON_STATUS_ID);
            }
            return statusCode;
        } finally {
            close(rs);
        }

    }
   /* @Override
    public Optional<Person> findSimplePersonByEmail(String email) throws DBException {
        Person person = null;
        try (Connection con = getConnection();
             PreparedStatement stmt = con.prepareStatement(DBConstants.FIND_SIMPLE_PERSON_BY_EMAIL);) {
            stmt.setString(1, email);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                person = mapPerson(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DBException(e);
        }
        return Optional.ofNullable(person);    }*/

    public boolean isExist(int id) throws DBException {
        int count = 0;
        try (Connection con = getConnection();
             PreparedStatement stmt = con.prepareStatement(DBConstants.COUNT_PERSON_BY_ID)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                count = rs.getInt("count");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DBException(e);
        }
        return count == 1;
    }

    private Person mapPerson(ResultSet rs) throws SQLException {
        Person person = new Person();
        // List<Publication> publications = new ArrayList<>();
        person.setId(rs.getInt(DBConstants.F_PERSON_ID));
        person.setEmail(rs.getString(DBConstants.F_PERSON_EMAIL));
        person.setRole(RoleType.valueOf(rs.getString(DBConstants.F_PERSON_ROLE)));
        person.setStatus(StatusType.valueOf(rs.getString(DBConstants.F_PERSON_STATUS)));
        person.setUsername(rs.getString(DBConstants.F_PERSON_USERNAME));
        person.setPassword(rs.getString(DBConstants.F_PERSON_PASSWORD));
        person.setFunds(rs.getDouble(DBConstants.F_PERSON_FUNDS));
        if (rs.getString(DBConstants.F_PERSON_HAS_PUBLICATION_PUBLICATION) != null) {
            person.setPublicationsId(getPublicationsId(rs));
        }
        return person;
    }

    private int[] getPublicationsId(ResultSet rs) throws SQLException {
        String rsPublicationsId = rs.getString(DBConstants.F_PERSON_HAS_PUBLICATION_PUBLICATION);
        String[] tempPublicationsId = rsPublicationsId.split(",");
        int[] publicationsId = new int[tempPublicationsId.length];
        int index = 0;
        for (String s : tempPublicationsId) {
            publicationsId[index++] = Integer.parseInt(s);
        }
        return publicationsId;
    }
}
