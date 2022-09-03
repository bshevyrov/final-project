package ua.com.company.dao;

import ua.com.company.entity.Person;

import java.util.Optional;

public interface PersonDao extends BaseDao<Person> {
    Optional<Person>findPersonByEmail(String email);
}
