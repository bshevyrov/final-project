package ua.com.company.service.impl;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.*;
import ua.com.company.dao.ImageDAO;
import ua.com.company.dao.PersonAddressDAO;
import ua.com.company.dao.PersonDAO;
import ua.com.company.dao.PublicationDAO;
import ua.com.company.entity.Image;
import ua.com.company.entity.Person;
import ua.com.company.entity.Publication;
import ua.com.company.exception.DBException;
import ua.com.company.utils.DBConnection;

import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class PersonServiceImplTest {

    @Mock
    PersonDAO personDAO;

    @Mock
    PersonAddressDAO personAddressDAO;
    @Mock
    ImageDAO imageDAO;

    @Mock
    PublicationDAO publicationDAO;
    @Mock
    Connection con;

    @InjectMocks
    PersonServiceImpl personService;

    private int id;

    private int idSecond;
    private List<Person> persons;
    private Person person;
    private Image image;
    private Publication publication;
    private String email;
    private String username;

    @BeforeAll
    public void init() {
        MockitoAnnotations.openMocks(this);
        id = 1;
        idSecond = 2;
        email = "email";
        username = "username";
        persons = new ArrayList<>();
        image = new Image();
        person = new Person();
        person.setAvatar(image);
        publication = new Publication();
    }

    @Test
    void create() throws DBException {
        try (MockedStatic<DBConnection> utilities = Mockito.mockStatic(DBConnection.class)) {
            utilities.when(DBConnection::getConnection).thenReturn(con);
            doNothing().when(personDAO).create(any(), any());
            personService.create(person);
            verify(personDAO, times(1)).create(con, person);
        }
    }

    @Test
    void update() throws DBException {
        try (MockedStatic<DBConnection> utilities = Mockito.mockStatic(DBConnection.class)) {
            utilities.when(DBConnection::getConnection).thenReturn(con);
            doNothing().when(personDAO).update(any(), any());
            personService.update(person);
            verify(personDAO, times(1)).update(any(), eq(person));
        }
    }

    @Test
    void delete() throws DBException {
        try (MockedStatic<DBConnection> utilities = Mockito.mockStatic(DBConnection.class)) {
            utilities.when(DBConnection::getConnection).thenReturn(con);
            doNothing().when(personDAO).delete(any(), anyInt());
            personService.delete(id);
            verify(personDAO, times(1)).delete(any(), eq(id));
        }
    }

    @Test
    void findAll() throws DBException {
        try (MockedStatic<DBConnection> utilities = Mockito.mockStatic(DBConnection.class)) {
            utilities.when(DBConnection::getConnection).thenReturn(con);
            when(personDAO.findAll(any())).thenReturn(persons);
            personService.findAll();
            verify(personDAO, times(1)).findAll(any());
        }
    }

    @Test
    void findById() throws DBException {
        try (MockedStatic<DBConnection> utilities = Mockito.mockStatic(DBConnection.class)) {
            utilities.when(DBConnection::getConnection).thenReturn(con);
            when(personDAO.findById(any(), anyInt())).thenReturn(Optional.ofNullable(person));
            when(imageDAO.findById(any(), anyInt())).thenReturn(Optional.ofNullable(image));
            personService.findById(id);
            verify(personDAO, times(1)).findById(any(), eq(id));
        }
    }

    @Test
    void findByEmail() throws DBException {
        try (MockedStatic<DBConnection> utilities = Mockito.mockStatic(DBConnection.class)) {
            utilities.when(DBConnection::getConnection).thenReturn(con);
            when(personDAO.findPersonByEmail(any(), anyString())).thenReturn(Optional.ofNullable(person));
            personService.findByEmail(email);
            verify(personDAO, times(1)).findPersonByEmail(any(), eq(email));
        }
    }

    @Test
    void isExistByEmail() throws DBException {
        try (MockedStatic<DBConnection> utilities = Mockito.mockStatic(DBConnection.class)) {
            utilities.when(DBConnection::getConnection).thenReturn(con);
            when(personDAO.isExistByEmail(any(), anyString())).thenReturn(true);
            personService.isExistByEmail(email);
            verify(personDAO, times(1)).isExistByEmail(any(), eq(email));
        }
    }

    @Test
    void isExistByUsername() throws DBException {
        try (MockedStatic<DBConnection> utilities = Mockito.mockStatic(DBConnection.class)) {
            utilities.when(DBConnection::getConnection).thenReturn(con);
            when(personDAO.isExistByUsername(any(), any())).thenReturn(true);
            personService.isExistByUsername(username);
            verify(personDAO, times(1)).isExistByUsername(any(), eq(username));
        }
    }

    @Test
    void changeStatusById() throws DBException {
        try (MockedStatic<DBConnection> utilities = Mockito.mockStatic(DBConnection.class)) {
            utilities.when(DBConnection::getConnection).thenReturn(con);
            doNothing().when(personDAO).changeStatusById(any(), anyInt());
            personService.changeStatusById(id);
            verify(personDAO, times(1)).changeStatusById(any(), eq(id));
        }
    }

    @Test
    void subscribe() throws DBException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        try (MockedStatic<DBConnection> utilities = Mockito.mockStatic(DBConnection.class)) {
            utilities.when(DBConnection::getConnection).thenReturn(con);
            doNothing().when(personDAO).subscribe(any(), anyInt(), anyInt());
            when(personDAO.findById(any(), anyInt())).thenReturn(Optional.ofNullable(person));
            when(publicationDAO.findById(any(), anyInt())).thenReturn(Optional.ofNullable(publication));
            personService.subscribe(id, idSecond);
            verify(personDAO, times(1)).subscribe(any(), eq(id), eq(idSecond));
        }
    }

    @Test
    void updateAvatar() throws DBException {
        try (MockedStatic<DBConnection> utilities = Mockito.mockStatic(DBConnection.class)) {
            utilities.when(DBConnection::getConnection).thenReturn(con);
            doNothing().when(personDAO).updateAvatar(any(), anyInt(), anyInt());
            personService.updateAvatar(id, idSecond);
            verify(personDAO, times(1)).updateAvatar(any(), eq(id), eq(idSecond));
        }
    }
}