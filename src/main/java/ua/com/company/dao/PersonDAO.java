package ua.com.company.dao;

import ua.com.company.entity.Person;
import ua.com.company.entity.Publication;
import ua.com.company.exception.DBException;

import java.sql.Connection;
import java.util.Optional;

public interface PersonDAO extends BaseDAO<Person> {
    Optional<Person>findPersonByEmail(Connection con, String email) throws DBException;
    void addPublicationForPerson(Connection con,Person person, Publication publication) throws DBException;

    Optional<Person> findPersonByUsername(Connection con,String username) throws DBException;

    boolean isExistByEmail(Connection con,String email) throws DBException;

    boolean isExistByUsername(Connection con,String username) throws DBException;

    boolean changeStatusById(Connection con,int id) throws DBException;

    void decreaseFunds(Connection con,int personId,double newFunds) throws DBException;

    void subscribe(Connection con, int pubId, int personId) throws DBException;
// Optional<Person>findSimplePersonByEmail(String email) throws DBException;
}
