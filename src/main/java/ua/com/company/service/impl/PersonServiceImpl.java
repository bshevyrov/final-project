package ua.com.company.service.impl;

import ua.com.company.dao.PersonDAO;
import ua.com.company.dao.mysql.impl.MysqlPersonDAOImpl;
import ua.com.company.entity.Person;
import ua.com.company.exception.DBException;
import ua.com.company.service.PersonService;

import java.util.List;

public class PersonServiceImpl implements PersonService {
    PersonDAO personDao = new MysqlPersonDAOImpl();

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
}
