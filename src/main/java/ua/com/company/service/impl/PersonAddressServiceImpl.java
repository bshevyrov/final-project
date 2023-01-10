package ua.com.company.service.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.com.company.dao.PersonAddressDAO;
import ua.com.company.entity.PersonAddress;
import ua.com.company.exception.DBException;
import ua.com.company.exception.PersonAddressNotFoundException;
import ua.com.company.service.PersonAddressService;
import ua.com.company.utils.DBConnection;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class PersonAddressServiceImpl implements PersonAddressService {
    private final PersonAddressDAO personAddressDAO;
    private final Logger log = LogManager.getLogger(PersonAddressServiceImpl.class);

    public PersonAddressServiceImpl(PersonAddressDAO personAddressDAO) {
        this.personAddressDAO = personAddressDAO;
    }


    @Override
    public void create(PersonAddress personAddress) {
        try (Connection con = DBConnection.getConnection()) {
            personAddressDAO.create(con, personAddress);
        } catch (SQLException | DBException e) {
            log.error("Can`t create personAddress ", e);
        }

    }

    @Override
    public void update(PersonAddress personAddress) {
        try (Connection con = DBConnection.getConnection()) {
            personAddressDAO.update(con, personAddress);
        } catch (SQLException | DBException e) {
            log.error("Can`t update personAddress ", e);
        }
    }

    @Override
    public void delete(int id) {
        try (Connection con = DBConnection.getConnection()) {
            personAddressDAO.delete(con, id);
        } catch (SQLException | DBException e) {
            log.error("Delete error ", e);
        }
    }

    @Override
    public PersonAddress findById(int id) {
        PersonAddress personAddress = null;
        try (Connection con = DBConnection.getConnection()) {
            personAddress = personAddressDAO.findById(con, id).orElseThrow(() -> new PersonAddressNotFoundException("" + id));
        } catch (SQLException | DBException e) {
            log.error("Find by id error ", e);
        } catch (PersonAddressNotFoundException e) {
            log.warn("PersonAddress not found " + id, e);
        }
        return personAddress;
    }

    @Override
    public List<PersonAddress> findAll() {
        List<PersonAddress> personAddresses = null;
        try (Connection con = DBConnection.getConnection()) {
            personAddressDAO.findAll(con);
        } catch (SQLException | DBException e) {
            log.error("Find all error ", e);
        }
        return personAddresses;
    }
}

