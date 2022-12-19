package ua.com.company.service.impl;

import jakarta.servlet.http.HttpSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.com.company.dao.*;
import ua.com.company.entity.Image;
import ua.com.company.entity.Person;
import ua.com.company.entity.PersonAddress;
import ua.com.company.entity.Publication;
import ua.com.company.exception.DBException;
import ua.com.company.exception.UserNotFoundException;
import ua.com.company.service.PersonService;
import ua.com.company.service.PublicationService;
import ua.com.company.utils.ClassConverter;
import ua.com.company.utils.CurrentSessionsThreadLocal;
import ua.com.company.utils.LoggedPersonThreadLocal;
import ua.com.company.view.dto.PersonDTO;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class PersonServiceImpl implements PersonService {
    private final Logger log = LogManager.getLogger(PersonServiceImpl.class);
    private final PersonDAO personDAO = DAOFactory.getInstance().getPersonDAO();
    private final PersonAddressDAO personAddressDAO = DAOFactory.getInstance().getPersonAddressDAO();
    private final ImageDAO imageDAO = DAOFactory.getInstance().getImageDAO();
    private final PublicationDAO publicationDAO = DAOFactory.getInstance().getPublicationDAO();
    private final PublicationService publicationService = PublicationServiceImpl.getInstance();
    private static PersonService instance;

    public static synchronized PersonService getInstance() {
        if (instance == null) {
            instance = new PersonServiceImpl();
        }
        return instance;
    }

    private PersonServiceImpl() {
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
            for (Person person : personList) {
                List<Publication> publications = publicationDAO.findAllByPersonId(con, person.getId());
                person.setPublications(publications);
            }
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
            Image image = imageDAO.findById(con, person.getAvatar().getId()).get();
           Optional< PersonAddress> personAddress = personAddressDAO.findByPersonId(con, id);
           if(personAddress.isPresent()){
               person.setPersonAddress(personAddress.get());
           }
          List<Publication> publicationList = publicationDAO.findAllByPersonId(con, id);
           person.setPublications(publicationList);
            person.setAvatar(image);
        } catch (DBException | SQLException e) {
            log.error("Person not found " + id, e);
            e.printStackTrace();
        } catch (UserNotFoundException e) {
            log.warn("Person not found " + id, e);
            e.printStackTrace();
        }
        return person;
    }

    private Person findById(Connection con,int id) {
        Person person = null;
        try  {
            person = personDAO.findById(con, id)
                    .orElseThrow(() -> new UserNotFoundException("" + id));
            Image image = imageDAO.findById(con, person.getAvatar().getId()).get();
            PersonAddress personAddress = personAddressDAO.findByPersonId(con, id).get();
            person.setAvatar(image);
        } catch (DBException e) {
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
    public void changeStatusById(int id) {
        try (Connection con = getConnection()) {
            personDAO.changeStatusById(con, id);
            PersonDTO currentPersonDTO = ClassConverter.personToPersonDTO(personDAO.findById(con, id).get());

            List<HttpSession> currentSessions = CurrentSessionsThreadLocal.get();
            int index = -1;
            for (int i = 0; i < currentSessions.size(); i++) {
                if (currentSessions.get(i).getAttribute("loggedPerson").equals(currentPersonDTO)) {
                    index = i;
                    currentSessions.get(i).invalidate();
                    break;
                }
            }

            if (index > -1) {
                currentSessions.remove(index);
            }
//            currentSessions.forEach(session1 -> {
//                if (session1.getAttribute("loggedPerson").equals(currentPersonDTO)) {
//                    currentSessions.remove(session1);
//                    session1.invalidate();
//                }
//            });

        } catch (DBException | SQLException e) {
            log.error("Change status by id ex " + id, e);
        }
    }

    @Override
    public void subscribe(int pubId, int personId) {
        //todo

        try (Connection con = getConnection()) {
            Person person = personDAO.findById(con, personId).get();
            Publication publication = publicationDAO.findById(con, pubId).get();

            personDAO.decreaseFunds(con, personId, person.getFunds() - publication.getPrice());
            personDAO.subscribe(con, pubId, personId);


            PersonDTO currentPersonDTO = ClassConverter.personToPersonDTO(personDAO.findById(con, personId).get());
            HttpSession httpSession = LoggedPersonThreadLocal.get();
            httpSession.setAttribute("loggedPerson", currentPersonDTO);
        } catch (DBException | SQLException e) {
            log.error("Subscribe exception, publication id = " + pubId + " person id = " + personId, e);
            e.printStackTrace();
        }

    }
}
