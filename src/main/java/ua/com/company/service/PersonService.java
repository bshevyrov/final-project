package ua.com.company.service;

import ua.com.company.entity.Person;

public interface PersonService extends BaseService<Person> {
    Person findByEmail(String email);

    Person findByUsername(String username);

//    Person findSimpleByEmail(String email);

    boolean isExistByEmail(String email);

    boolean isExistByUsername(String username);

    boolean changeStatusById(int parseInt);

    void subscribe(int pubId,int personId);
}
