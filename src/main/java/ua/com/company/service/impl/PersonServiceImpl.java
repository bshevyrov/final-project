package ua.com.company.service.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.com.company.dao.DAOFactory;
import ua.com.company.dao.PersonDAO;
import ua.com.company.entity.Person;
import ua.com.company.entity.Publication;
import ua.com.company.exception.DBException;
import ua.com.company.exception.UserNotFoundException;
import ua.com.company.service.PersonService;
import ua.com.company.service.PublicationService;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class PersonServiceImpl implements PersonService {
    private final Logger log = LogManager.getLogger(PersonServiceImpl.class);
    private final PersonDAO personDAO = DAOFactory.getInstance().getPersonDAO();
    private final PublicationService publicationService = PublicationServiceImpl.getInstance();
    private static PersonService instance;

    public static synchronized PersonService getInstance() {
        if (instance == null) {
            try {
                instance = new PersonServiceImpl();
            } catch (Exception e) {
                //TODO LOG
                e.printStackTrace();
            }
        }
        return instance;
    }

    private PersonServiceImpl(){
    }

    @Override
    public void create(Person person) {
        try (Connection con = getConnection()) {
            personDAO.create(con, person);
        } catch (DBException | SQLException e) {
            log.error("Can`t create person " + person, e);
            e.printStackTrace();
        }
    }

    @Override
    public void update(Person person) {
        try (Connection con = getConnection()) {
            personDAO.update(con, person);
        } catch (DBException | SQLException e) {
            log.error("Can`t update person " + person, e);
            e.printStackTrace();
        }
    }

    @Override
    public void delete(int id) {
        try (Connection con = getConnection()) {
            personDAO.delete(con, id);
        } catch (DBException | SQLException e) {
            log.error("Delete Error " + id, e);
            e.printStackTrace();
        }
    }

    @Override
    public List<Person> findAll() {
        List<Person> personList = null;
        try (Connection con = getConnection()) {
            personList = personDAO.findAll(con);
        } catch (DBException | SQLException e) {
            log.error("findAll ex ", e);
            e.printStackTrace();
        }
        return personList;
    }

    @Override
    public Person findById(int id) {
        Person person = null;
        try (Connection con = getConnection()) {
            person = personDAO.findById(con, id)
                    .orElseThrow(() -> new UserNotFoundException("" + id));
        } catch (DBException | SQLException e) {
            log.error("Person not found " + id, e);
            e.printStackTrace();
        } catch (UserNotFoundException e) {
            log.warn("Person not found " + id, e);
            e.printStackTrace();
        }
        return person;
    }

    @Override
    public Person findByEmail(String email) {
        Person person = null;
        try (Connection con = getConnection()) {
            person = personDAO.findPersonByEmail(con, email)
                    .orElseThrow(() -> new UserNotFoundException(email));
        } catch (DBException | SQLException e) {
            log.error("Person not found " + email, e);
            e.printStackTrace();
        } catch (UserNotFoundException e) {
            log.warn("Person not found " + email, e);
            e.printStackTrace();
        }
        return person;
    }

    @Override
    public Person findByUsername(String username) {
        Person person = null;
        try (Connection con = getConnection()) {
            person = personDAO.findPersonByUsername(con, username)
                    .orElseThrow(() -> new UserNotFoundException(username));
        } catch (DBException | SQLException e) {
            log.error("Person not found " + username, e);
            e.printStackTrace();
        } catch (UserNotFoundException e) {
            log.warn("Person not found " + username, e);
            e.printStackTrace();
        }
        return person;
    }

    @Override
    public boolean isExistByEmail(String email) {
        boolean existByUEmail = false;

        try (Connection con = getConnection()) {
            existByUEmail = personDAO.isExistByEmail(con, email);
        } catch (DBException | SQLException e) {
            log.error("Is exist by email ex " + email, e);
            e.printStackTrace();
        }
        return existByUEmail;
    }


    @Override
    public boolean isExistByUsername(String username) {
        boolean existByUsername = false;
        try (Connection con = getConnection()) {
            existByUsername = personDAO.isExistByUsername(con, username);
        } catch (DBException | SQLException e) {
            log.error("Is exist by username ex " + username, e);
            e.printStackTrace();
        }
        return existByUsername;
    }

    @Override
    public boolean changeStatusById(int id) {
        boolean completed = false;
        try (Connection con = getConnection()) {
            completed = personDAO.changeStatusById(con, id);
        } catch (DBException | SQLException e) {
            log.error("Change status by id ex " + id, e);
            e.printStackTrace();
        }
        return completed;
    }

    @Override
    public void subscribe(int pubId, int personId) {
        //todo
        Person person = findById(personId);
        Publication publication = publicationService.findById(pubId);

        try (Connection con = getConnection()) {
            if (person.getFunds() - publication.getPrice() > 0) {
                for (int i : person.getPublicationsId()) {
                    if (pubId == i) {
                        throw new DBException("Subscriptions already exist");
                    }
                }
            }
            personDAO.decreaseFunds(con, personId, person.getFunds() - publication.getPrice());
            personDAO.subscribe(con, pubId, personId);
        } catch (DBException | SQLException e) {
            log.error("Subscribe ex, publication id = " + pubId + " person id = " + personId, e);
            e.printStackTrace();
        }
    }
}
