package ua.com.company.service.impl;

import ua.com.company.dao.PersonDao;
import ua.com.company.dao.impl.PersonDaoImpl;
import ua.com.company.entity.Person;
import ua.com.company.service.PersonService;

import java.util.List;

public class PersonServiceImpl implements PersonService {
    PersonDao personDao = new PersonDaoImpl();

    @Override
    public void create(Person person) {
        personDao.create(person);
    }

    @Override
    public void update(Person person) {
        personDao.update(person);
    }

    @Override
    public void delete(int id) {
        personDao.delete(id);
    }

    @Override
    public Person findById(int id) {

        return personDao.findById(id);
    }

    @Override
    public List<Person> findAll() {
        return personDao.findAll();
    }
}
