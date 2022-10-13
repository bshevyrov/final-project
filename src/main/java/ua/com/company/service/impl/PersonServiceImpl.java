package ua.com.company.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.com.company.dao.PersonDAO;
import ua.com.company.entity.Person;
import ua.com.company.exception.DBException;
import ua.com.company.exception.UserNotFoundException;
import ua.com.company.service.PersonService;

import java.util.List;

public class PersonServiceImpl implements PersonService {
    private final PersonDAO personDao;
    private final Logger log = LoggerFactory.getLogger(PersonServiceImpl.class);


    public PersonServiceImpl(PersonDAO personDAO) {
        this.personDao = personDAO;
    }

    @Override
    public int create(Person person) {
        int id = -1;
        try {
            id = personDao.create(person);
        } catch (DBException e) {
            log.error(String.valueOf(e));
            e.printStackTrace();
        }
        return id;
    }

    @Override
    public void update(Person person) {
        try {
            personDao.update(person);
        } catch (DBException e) {
            log.error(String.valueOf(e));
            e.printStackTrace();
        }
    }

    @Override
    public void delete(int id) {
        try {
            personDao.delete(id);
        } catch (DBException e) {
            log.error(String.valueOf(e));
            e.printStackTrace();
        }
    }

    @Override
    public Person findById(int id) {
        Person person = null;
        try {
            person = personDao.findById(id)
                    .orElseThrow(() -> new UserNotFoundException("" + id));
        } catch (DBException e) {
            log.error(String.valueOf(e));
            e.printStackTrace();
        } catch (UserNotFoundException e) {
            log.warn(String.valueOf(e));
            e.printStackTrace();
        }
        return person;
    }

    @Override
    public List<Person> findAll() {
        List<Person> personList = null;
        try {
            personList = personDao.findAll();
        } catch (DBException e) {
            log.error(String.valueOf(e));
            e.printStackTrace();
        }
        return personList;
    }

    @Override
    public Person findByEmail(String email) {
        Person person = null;
        try {
            person = personDao.findPersonByEmail(email)
                    .orElseThrow(() -> new UserNotFoundException(email));
        } catch (DBException e) {
            log.error(String.valueOf(e));
            e.printStackTrace();
        } catch (UserNotFoundException e) {
            log.warn(String.valueOf(e));
            e.printStackTrace();
        }
        return person;
    }
    @Override
    public Person findSimpleByEmail(String email) {
        Person person = null;
        try {
            person = personDao.findSimplePersonByEmail(email)
                    .orElseThrow(() -> new UserNotFoundException(email));
        } catch (DBException e) {
            log.error(String.valueOf(e));
            e.printStackTrace();
        } catch (UserNotFoundException e) {
            log.warn(String.valueOf(e));
            e.printStackTrace();
        }
        return person;
    }

    @Override
    public Person findByUsername(String username) {
        Person person = null;
        try {
            person = personDao.findPersonByUsername(username)
                    .orElseThrow(() -> new UserNotFoundException(username));
        } catch (DBException e) {
            log.error(String.valueOf(e));
            e.printStackTrace();
        } catch (UserNotFoundException e) {
            log.warn(String.valueOf(e));
            e.printStackTrace();
        }
        return person;
    }

    @Override
    public boolean isExistByEmail(String email) {
        boolean existByUEmail = false;

        try {
            existByUEmail = personDao.isExistByEmail(email);
        } catch (DBException e) {
            log.error(String.valueOf(e));
            e.printStackTrace();
        }
        return existByUEmail;
    }


    @Override
    public boolean isExistByUsername(String username) {
        boolean existByUsername = false;
        try {
            existByUsername = personDao.isExistByUsername(username);
        } catch (DBException e) {
            log.error(String.valueOf(e));
            e.printStackTrace();
        }
        return existByUsername;
    }
}
