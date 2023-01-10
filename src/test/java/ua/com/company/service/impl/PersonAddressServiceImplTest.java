package ua.com.company.service.impl;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.*;
import ua.com.company.dao.PersonAddressDAO;
import ua.com.company.entity.PersonAddress;
import ua.com.company.exception.DBException;
import ua.com.company.utils.DBConnection;

import java.sql.Connection;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class PersonAddressServiceImplTest {

    @Mock
    Connection con;

    @Mock
    private PersonAddressDAO personAddressDAO;

    @InjectMocks
    private PersonAddressServiceImpl personAddressService;
    private int id;
    private PersonAddress personAddress;
    private List<PersonAddress> personAddresses;

    @BeforeAll
    public void init() {
        MockitoAnnotations.openMocks(this);
        id = 1;
    }

    @Test
    void create() throws DBException {
        try (MockedStatic<DBConnection> utilities = Mockito.mockStatic(DBConnection.class)) {
            utilities.when(DBConnection::getConnection).thenReturn(con);
            doNothing().when(personAddressDAO).create(any(), any());
            personAddressService.create(new PersonAddress());
            verify(personAddressDAO, times(1)).create(any(), eq(new PersonAddress()));
        }
    }

    @Test
    void update() throws DBException {
        try (MockedStatic<DBConnection> utilities = Mockito.mockStatic(DBConnection.class)) {
            utilities.when(DBConnection::getConnection).thenReturn(con);
            doNothing().when(personAddressDAO).update(any(), any());
            personAddressService.update(new PersonAddress());
            verify(personAddressDAO, times(1)).update(any(), eq(new PersonAddress()));
        }
    }

    @Test
    void delete() throws DBException {
        try (MockedStatic<DBConnection> utilities = Mockito.mockStatic(DBConnection.class)) {
            utilities.when(DBConnection::getConnection).thenReturn(con);
            doNothing().when(personAddressDAO).delete(any(), anyInt());
            personAddressService.delete(id);
            verify(personAddressDAO, times(1)).delete(any(), eq(id));
        }
    }

    @Test
    void findById() throws DBException {
        try (MockedStatic<DBConnection> utilities = Mockito.mockStatic(DBConnection.class)) {
            utilities.when(DBConnection::getConnection).thenReturn(con);
            when(personAddressDAO.findById(any(), anyInt())).thenReturn(Optional.ofNullable(personAddress));
            personAddressService.findById(id);
            verify(personAddressDAO, times(1)).findById(any(), eq(id));
        }
    }

    @Test
    void findAll() throws DBException {
        try (MockedStatic<DBConnection> utilities = Mockito.mockStatic(DBConnection.class)) {
            utilities.when(DBConnection::getConnection).thenReturn(con);
           when(personAddressDAO.findAll(any())).thenReturn(personAddresses);
            personAddressService.findAll();
            verify(personAddressDAO, times(1)).findAll(any());
        }
    }
}
