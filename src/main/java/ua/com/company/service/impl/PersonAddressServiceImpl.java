package ua.com.company.service.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.com.company.dao.DAOFactory;
import ua.com.company.dao.PersonAddressDAO;
import ua.com.company.entity.PersonAddress;
import ua.com.company.exception.DBException;
import ua.com.company.service.PersonAddressService;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class PersonAddressServiceImpl implements PersonAddressService {
    private final PersonAddressDAO personAddressDAO = DAOFactory.getInstance().getPersonAddressDAO();
    private static PersonAddressServiceImpl instance;
    private final Logger log = LogManager.getLogger(PersonAddressServiceImpl.class);

    public static synchronized PersonAddressServiceImpl getInstance() {
        if (instance == null) {
            instance = new PersonAddressServiceImpl();
        }
        return instance;
    }

    private PersonAddressServiceImpl() {
    }

    @Override
    public void create(PersonAddress personAddress) {
        try (Connection con = getConnection()) {
            personAddressDAO.create(con, personAddress);
        } catch (SQLException | DBException e) {
            log.error("Can`t create personAddress " + personAddress, e);
        }

    }

    @Override
    public void update(PersonAddress personAddress) {
        try (Connection con = getConnection()) {
            personAddressDAO.update(con, personAddress);
        } catch (SQLException | DBException e) {
            log.error("Can`t update personAddress " + personAddress, e);
        }
    }

    @Override
    public void delete(int id) {

    }

    @Override
    public PersonAddress findById(int id) {
        return null;
    }

    @Override
    public List<PersonAddress> findAll() {
        return null;
    }

    @Override
    public void updateByPersonId(int personId) {

    }
}
