package ua.com.company.service;

import ua.com.company.entity.Person;

public interface PersonService extends BaseService<Person> {
    Person findByEmail(String email);

    boolean isExistByEmail(String email);

    boolean isExistByUsername(String username);

    void changeStatusById(int parseInt);

    void subscribe(int pubId, int personId);
    void updateAvatar(int personId, int avatarId);
}
