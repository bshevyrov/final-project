package ua.com.company.service;

import ua.com.company.entity.Person;
import ua.com.company.exception.UserNotFoundException;

public interface PersonService extends BaseService<Person>{
    Person findByEmail(String email) throws UserNotFoundException;

    Person findByUsername(String username) throws UserNotFoundException;
}
