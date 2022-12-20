package ua.com.company.dao;

import ua.com.company.entity.PersonAddress;
import ua.com.company.exception.DBException;

import java.sql.Connection;
import java.util.Optional;

public interface PersonAddressDAO extends BaseDAO<PersonAddress> {
    Optional<PersonAddress> findByPersonId(Connection con, int userId) throws DBException;
}
