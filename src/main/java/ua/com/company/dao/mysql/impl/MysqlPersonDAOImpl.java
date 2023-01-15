package ua.com.company.dao.mysql.impl;

import ua.com.company.utils.DBConstants;
import ua.com.company.dao.PersonDAO;
import ua.com.company.entity.Image;
import ua.com.company.entity.Person;
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
    public int create(Connection con, Person person) throws DBException {
        int id = 0;
        try (PreparedStatement ps = con.prepareStatement(DBConstants.CREATE_PERSON, PreparedStatement.RETURN_GENERATED_KEYS)) {
            String encryptedPass = PasswordUtil.encryptPassword(person.getPassword());
            int index = 0;
            ps.setString(++index, person.getUsername());
            ps.setString(++index, person.getEmail());
            ps.setString(++index, encryptedPass);
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                id = rs.getInt(1);
            }
        } catch (SQLException e) {
            throw new DBException("Connection: " + con + " and " + person, e);
        }
        return id;
    }

    @Override
    public void update(Connection con, Person person) throws DBException {
        if (isExist(con, person.getId())) {
            try (PreparedStatement ps = con.prepareStatement(DBConstants.UPDATE_PERSON)) {
                int index = 0;
                ps.setString(++index, person.getEmail());
                ps.setString(++index, person.getRole().toString());
                ps.setString(++index, person.getStatus().toString());
                ps.setInt(++index, person.getAvatar().getId());
                ps.setInt(++index, person.getId());
                ps.execute();
            } catch (SQLException e) {
                throw new DBException("Connection: " + con + " and " + person, e);
            }
        } else {
            throw new DBException("Cant find person with id " + person.getId());
        }
    }

    @Override
    public void delete(Connection con, int id) throws DBException {
        try (PreparedStatement stmt = con.prepareStatement(DBConstants.DELETE_PERSON)) {
            stmt.setInt(1, id);
            stmt.execute();
        } catch (SQLException e) {
            throw new DBException("Connection: " + con + " and id= " + id, e);
        }
    }

    @Override
    public Optional<Person> findPersonByEmail(Connection con, String email) throws DBException {
        Optional<Person> person = Optional.empty();
        try (PreparedStatement stmt = con.prepareStatement(DBConstants.FIND_PERSON_BY_EMAIL);) {
            stmt.setString(1, email);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                person = Optional.of(mapPerson(rs));
            }
        } catch (SQLException e) {
            throw new DBException("Connection: " + con + " and email= " + email, e);
        }
        return person;
    }

    @Override
    public Optional<Person> findById(Connection con, int id) throws DBException {
        Optional<Person> person = Optional.empty();
        try (PreparedStatement stmt = con.prepareStatement(DBConstants.FIND_PERSON_BY_ID)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                person = Optional.of(mapPerson(rs));
            }
        } catch (SQLException e) {
            throw new DBException("Connection: " + con + " and id= " + id, e);
        }
        return person;
    }


    @Override
    public List<Person> findAll(Connection con) throws DBException {
        List<Person> persons = new ArrayList<>();
        try (Statement stmt = con.createStatement()) {
            ResultSet rs = stmt.executeQuery(DBConstants.FIND_ALL_PERSONS);
            while (rs.next()) {
                persons.add(mapPerson(rs));
            }
        } catch (SQLException e) {
            throw new DBException("Connection: " + con, e);
        }
        return persons;
    }

    public boolean isExistByEmail(Connection con, String email) throws DBException {
        int count = 0;
        try (PreparedStatement stmt = con.prepareStatement(DBConstants.COUNT_PERSON_BY_EMAIL)) {
            stmt.setString(1, email);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                count = rs.getInt("count");
            }
        } catch (SQLException e) {
            throw new DBException("Connection: " + con + " and email= " + email, e);
        }
        return count == 1;
    }

    @Override
    public boolean isExistByUsername(Connection con, String username) throws DBException {
        int count = 0;
        try (PreparedStatement stmt = con.prepareStatement(DBConstants.COUNT_PERSON_BY_USERNAME)) {
            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                count = rs.getInt("count");
            }
        } catch (SQLException e) {
            throw new DBException("Connection: " + con + " and username= " + username, e);
        }
        return count == 1;
    }

    @Override
    public void changeStatusById(Connection con, int id) throws DBException {
        try (PreparedStatement stmt = con.prepareStatement(DBConstants.CHANGE_USER_STATUS_BY_ID)) {
            int newStatus = checkStatusById(con, id) == 1 ? 2 : 1;
            int index = 0;
            stmt.setInt(++index, newStatus);
            stmt.setInt(++index, id);
            stmt.execute();
        } catch (SQLException e) {
            throw new DBException("Connection: " + con + "and id= " + id, e);
        }
    }

    @Override
    public void decreaseFunds(Connection con, int personId, double newFunds) throws DBException {
        try (PreparedStatement stmt = con.prepareStatement(DBConstants.DECREASE_FUNDS)) {
            int index = 0;
            stmt.setDouble(++index, newFunds);
            stmt.setInt(++index, personId);
            stmt.execute();
        } catch (SQLException e) {
            throw new DBException(con + "personId= " + personId + " newFunds= " + newFunds, e);
        }
    }

    @Override
    public void subscribe(Connection con, int pubId, int personId) throws DBException {
        try (PreparedStatement stmt = con.prepareStatement(DBConstants.CREATE_PERSON_HAS_PUBLICATION)) {
            int index = 0;
            stmt.setInt(++index, personId);
            stmt.setInt(++index, pubId);
            stmt.execute();
        } catch (SQLException e) {
            throw new DBException("Connection: " + con + " and personId= " + personId + " and pubId= " + pubId, e);
        }
    }

    @Override
    public void updateAvatar(Connection con, int personId, int avatarId) throws DBException {
        try (PreparedStatement ps = con.prepareStatement(DBConstants.UPDATE_PERSON_AVATAR)) {
            int index = 0;
            ps.setInt(++index, avatarId);
            ps.setInt(++index, personId);
            ps.execute();
        } catch (SQLException e) {
            throw new DBException("Connection: " + con + " and personId= " + personId + " and avatarId= " + avatarId, e);
        }
    }

    private int checkStatusById(Connection con, int id) throws DBException {
        try (PreparedStatement stmt = con.prepareStatement(DBConstants.CHECK_USER_STATUS_BY_ID)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            int statusCode = -1;
            if (rs.next()) {
                statusCode = rs.getInt(DBConstants.F_PERSON_STATUS_ID);
            }
            return statusCode;
        } catch (SQLException e) {
            throw new DBException("Connection: " + con + " and id= " + id, e);
        }
    }

    public boolean isExist(Connection con, int id) throws DBException {
        int count = 0;
        try (PreparedStatement stmt = con.prepareStatement(DBConstants.COUNT_PERSON_BY_ID)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                count = rs.getInt("count");
            }
        } catch (SQLException e) {
            throw new DBException("Connection: " + con + " and id= " + id, e);
        }
        return count == 1;
    }

    private Person mapPerson(ResultSet rs) throws SQLException {
        Person person = new Person();
        person.setId(rs.getInt(DBConstants.F_PERSON_ID));
        person.setEmail(rs.getString(DBConstants.F_PERSON_EMAIL));
        person.setRole(RoleType.valueOf(rs.getString(DBConstants.F_PERSON_ROLE)));
        person.setStatus(StatusType.valueOf(rs.getString(DBConstants.F_PERSON_STATUS)));
        person.setUsername(rs.getString(DBConstants.F_PERSON_USERNAME));
        person.setPassword(rs.getString(DBConstants.F_PERSON_PASSWORD));
        person.setFunds(rs.getDouble(DBConstants.F_PERSON_FUNDS));

        Image image = new Image();
        image.setId(rs.getInt(DBConstants.F_PERSON_IMAGE_ID));
        person.setAvatar(image);
        return person;
    }
}
