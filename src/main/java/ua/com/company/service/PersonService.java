package ua.com.company.service;

import ua.com.company.entity.Person;

import java.util.Optional;

public interface PersonService extends BaseService<Person>{
    Optional<Person> findByEmail(String email);

    Optional<Person> findByUsername(String username);
}
