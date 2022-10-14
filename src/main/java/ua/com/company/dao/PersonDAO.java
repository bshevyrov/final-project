package ua.com.company.dao;

import ua.com.company.entity.Person;
import ua.com.company.entity.Publication;
import ua.com.company.exception.DBException;

import java.util.Optional;

public interface PersonDAO extends BaseDAO<Person> {
    Optional<Person>findPersonByEmail(String email) throws DBException;
    void addPublicationForPerson(Person person, Publication publication) throws DBException;

    Optional<Person> findPersonByUsername(String username) throws DBException;

    boolean isExistByEmail(String email) throws DBException;

    boolean isExistByUsername(String username) throws DBException;

    boolean changeStatusById(int id) throws DBException;
// Optional<Person>findSimplePersonByEmail(String email) throws DBException;
}
