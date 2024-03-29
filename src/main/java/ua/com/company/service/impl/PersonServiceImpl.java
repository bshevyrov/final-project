package ua.com.company.service.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.com.company.dao.ImageDAO;
import ua.com.company.dao.PersonAddressDAO;
import ua.com.company.dao.PersonDAO;
import ua.com.company.dao.PublicationDAO;
import ua.com.company.entity.Image;
import ua.com.company.entity.Person;
import ua.com.company.entity.Publication;
import ua.com.company.exception.DBException;
import ua.com.company.exception.ImageNotFoundException;
import ua.com.company.exception.UserNotFoundException;
import ua.com.company.service.PersonService;
import ua.com.company.utils.DBConnection;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class PersonServiceImpl implements PersonService {
    private final Logger log = LogManager.getLogger(PersonServiceImpl.class);
    private final PersonDAO personDAO;
    private final PersonAddressDAO personAddressDAO;
    private final ImageDAO imageDAO;
    private final PublicationDAO publicationDAO;

    public PersonServiceImpl(PersonDAO personDAO, PersonAddressDAO personAddressDAO, ImageDAO imageDAO, PublicationDAO publicationDAO) {
        this.personDAO = personDAO;
        this.personAddressDAO = personAddressDAO;
        this.imageDAO = imageDAO;
        this.publicationDAO = publicationDAO;
    }

    /**
     * @param person entity to put in Database
     */
    @Override
    public void create(Person person) {
        try (Connection con = DBConnection.getConnection()) {
            personDAO.create(con, person);
        } catch (DBException | SQLException e) {
            log.error("Can`t create person ", e);
        }
    }

    /**
     * @param person entity to update in Database
     */
    @Override
    public void update(Person person) {
        try (Connection con = DBConnection.getConnection()) {
            personDAO.update(con, person);
        } catch (DBException | SQLException e) {
            log.error("Can`t update person ", e);
        }
    }

    /**
     * @param id id of entity  that need to delete
     */
    @Override
    public void delete(int id) {
        try (Connection con = DBConnection.getConnection()) {
            personDAO.delete(con, id);
        } catch (DBException | SQLException e) {
            log.error("Delete Error ", e);
        }
    }

    /**
     * @return List of entities
     */
    @Override
    public List<Person> findAll() {
        List<Person> personList = null;
        try (Connection con = DBConnection.getConnection()) {
            personList = personDAO.findAll(con);
            for (Person person : personList) {
                List<Publication> publications = publicationDAO.findAllByPersonId(con, person.getId());
                person.setPublications(publications);
            }
        } catch (DBException | SQLException e) {
            log.error("FindAll error ", e);
        }
        return personList;
    }

    /**
     * @param id id of entity that need to find
     * @return entity or throw {@link UserNotFoundException}
     */
    @Override
    public Person findById(int id) {
        Person person = new Person();
        try (Connection con = DBConnection.getConnection()) {
            person = personDAO.findById(con, id)
                    .orElseThrow(() -> new UserNotFoundException("" + id));
            person.setPersonAddress(personAddressDAO.findByPersonId(con, id).get());
            person.setPublications(publicationDAO.findAllByPersonId(con, id));
            person.setAvatar(imageDAO.findById(con, person.getAvatar().getId()).get());
        } catch (DBException | SQLException e) {
            log.error("Find by id error ", e);

        }
        return person;
    }

    /**
     * @param email of {@link Person} that need to find
     * @return entity or throw {@link ImageNotFoundException}
     */
    @Override
    public Person findByEmail(String email) {
        Person person = null;
        try (Connection con = DBConnection.getConnection()) {
            person = personDAO.findPersonByEmail(con, email)
                    .orElseThrow(() -> new UserNotFoundException(email));
            person.setPersonAddress(personAddressDAO.findByPersonId(con, person.getId()).get());
            person.setPublications(publicationDAO.findAllByPersonId(con, person.getId()));
            person.setAvatar(imageDAO.findById(con, person.getAvatar().getId()).get());
        } catch (DBException | SQLException e) {
            log.error("Find by email exception ", e);
        }
        return person;
    }

    /**
     * @param email email of {@link Person} that need to check
     * @return true if {@link Person} exist
     */
    @Override
    public boolean isExistByEmail(String email) {
        boolean existByUEmail = false;
        try (Connection con = DBConnection.getConnection()) {
            existByUEmail = personDAO.isExistByEmail(con, email);
        } catch (DBException | SQLException e) {
            log.error("Is exist by email error ", e);
        }
        return existByUEmail;
    }

    /**
     * @param username username  of {@link Person} that need to check
     * @return true if {@link Person} exist
     */
    @Override
    public boolean isExistByUsername(String username) {
        boolean existByUsername = false;
        try (Connection con = DBConnection.getConnection()) {
            existByUsername = personDAO.isExistByUsername(con, username);
        } catch (DBException | SQLException e) {
            log.error("Is exist by username error ", e);
        }
        return existByUsername;
    }

    /**
     * @param id of {@link Person} that need to change status
     */
    @Override
    public void changeStatusById(int id) {
        try (Connection con = DBConnection.getConnection()) {
            personDAO.changeStatusById(con, id);
        } catch (DBException | SQLException e) {
            log.error("Change status by id error.", e);
        }
    }

    /**
     * @param pubId    id of {@link Publication}
     * @param personId id of {@link Person}
     */
    @Override
    public void subscribe(int pubId, int personId) {
        try (Connection con = DBConnection.getConnection()) {
            Person person = personDAO.findById(con, personId).get();
            Publication publication = publicationDAO.findById(con, pubId).get();
            personDAO.updateFunds(con, personId, person.getFunds() - publication.getPrice());
            personDAO.subscribe(con, pubId, personId);
        } catch (DBException | SQLException e) {
            log.error("Subscribe exception ", e);
        }
    }

    /**
     * @param personId id of {@link Person}
     * @param avatarId id of {@link Image}
     */
    @Override
    public void updateAvatar(int personId, int avatarId) {
        try (Connection con = DBConnection.getConnection()) {
            personDAO.updateAvatar(con, personId, avatarId);
        } catch (DBException | SQLException e) {
            log.error("Update avatar exception ", e);
        }
    }
}
