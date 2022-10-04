package ua.com.company.service.impl;

import ua.com.company.dao.PersonDAO;
import ua.com.company.entity.Person;
import ua.com.company.exception.DBException;
import ua.com.company.service.PersonService;

import java.util.List;
import java.util.Optional;

public class PersonServiceImpl implements PersonService {
     private final PersonDAO personDao ;
     public PersonServiceImpl(PersonDAO personDAO){
         this.personDao = personDAO;
     }

    @Override
    public void create(Person person) {
        personDao.create(person);
    }

    @Override
    public void update(Person person) {
        try {
            personDao.update(person);
        } catch (DBException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(int id) {
        personDao.delete(id);
    }

    @Override
    public Person findById(int id) {

        try {
            return personDao.findById(id).get();
        } catch (DBException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Person> findAll() {
        try {
            return personDao.findAll();
        } catch (DBException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Optional<Person> findByEmail(String email) {

         Optional<Person> person = personDao.findPersonByEmail(email);
         return Optional.of(person.get());
    }

    @Override
    public Optional<Person> findByUsername(String username) {
    return personDao.findPersonByUsername(username);
    }
}
