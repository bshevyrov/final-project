package ua.com.company.facade.impl;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import ua.com.company.entity.Person;
import ua.com.company.service.PersonService;
import ua.com.company.view.dto.PersonDTO;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class PersonFacadeImplTest {
    @Mock
    PersonService personService;
    @InjectMocks
    PersonFacadeImpl personFacade;
    Person person;
    PersonDTO personDTO;
    List<Person> persons;

    @BeforeAll
    void init() {
        MockitoAnnotations.openMocks(this);
        person = new Person();
        persons = new ArrayList<>();
        personDTO = new PersonDTO();
    }

    @Test
    void create() {
        doNothing().when(personService).create(person);
        personFacade.create(personDTO);
        verify(personService, times(1)).create(person);
    }

    @Test
    void update() {
        doNothing().when(personService).update(person);
        personFacade.update(personDTO);
        verify(personService, times(1)).update(person);
    }

    @Test
    void delete() {
        doNothing().when(personService).delete(anyInt());
        personFacade.delete(anyInt());
        verify(personService, times(1)).delete(anyInt());
    }

    @Test
    void findAll() {
        when(personService.findAll()).thenReturn(persons);
        personFacade.findAll();
        verify(personService, times(1)).findAll();
    }

    @Test
    void changeStatusById() {
        doNothing().when(personService).changeStatusById(anyInt());
        personFacade.changeStatusById(anyInt());
        verify(personService, times(1)).changeStatusById(anyInt());
    }

    @Test
    void findByEmail() {
        when(personService.findByEmail(anyString())).thenReturn(person);
        personFacade.findByEmail(anyString());
        verify(personService, times(1)).findByEmail(anyString());
    }

    @Test
    void findById() {
        when(personService.findById(anyInt())).thenReturn(person);
        personFacade.findById(anyInt());
        verify(personService, times(1)).findById(anyInt());
    }

    @Test
    void isExistByEmail() {
        when(personService.isExistByEmail(anyString())).thenReturn(true);
        personFacade.isExistByEmail(anyString());
        verify(personService, times(1)).isExistByEmail(anyString());
    }

    @Test
    void isExistByUsername() {
        when(personService.isExistByUsername(anyString())).thenReturn(true);
        personFacade.isExistByUsername(anyString());
        verify(personService, times(1)).isExistByUsername(anyString());
    }

    @Test
    void subscribe() {
        doNothing().when(personService).subscribe(anyInt(),anyInt());
        personFacade.subscribe(anyInt(),anyInt());
        verify(personService, times(1)).subscribe(anyInt(),anyInt());
    }
}