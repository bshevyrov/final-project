package ua.com.company.dao;

import ua.com.company.entity.Person;
import ua.com.company.entity.Publication;

import java.util.Optional;

public interface PersonDAO extends BaseDAO<Person> {
    Optional<Person>findPersonByEmail(String email);
    void addPublicationForPerson(Person person, Publication publication);

    Optional<Person> findPersonByUsername(String username);
}
