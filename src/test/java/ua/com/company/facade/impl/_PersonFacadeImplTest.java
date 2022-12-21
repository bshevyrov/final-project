package ua.com.company.facade.impl;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import ua.com.company.entity.Person;
import ua.com.company.facade.PersonFacade;
import ua.com.company.service.PersonService;
import ua.com.company.service.PublicationService;
import ua.com.company.view.dto.PersonDTO;

import static org.mockito.Mockito.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class _PersonFacadeImplTest {

    private PersonFacade personFacade;
    private PersonService personService;

    @BeforeAll
    void init() {
        PublicationService publicationService = mock(PublicationService.class);
        personService = mock(PersonService.class);
        personFacade = new PersonFacadeImpl(personService);
    }

    @Test
    void create() {
        personFacade.create(new PersonDTO());
        verify(personService, times(1)).create(any());
    }

    @Test
    void update() {
        personFacade.update(new PersonDTO());
        verify(personService, times(1)).update(any());
    }

    @Test
    void delete() {
        personFacade.delete(isA(Integer.class));
        verify(personService, times(1)).delete(isA(Integer.class));
    }

    @Test
    void findById() {
        //can return null TODO
        when(personService.findById(isA(Integer.class))).thenReturn(new Person());
        personFacade.findById(isA(Integer.class));
        verify(personService, times(1)).findById(isA(Integer.class));
    }

    @Test
    void findByEmail() {
        when(personService.findByEmail(isA(String.class))).thenReturn(new Person());
        personFacade.findByEmail("");
        verify(personService, times(1)).findByEmail(isA(String.class));
    }


    @Test
    void findAll() {
        personFacade.findAll();
        verify(personService, times(1)).findAll();
    }

    @Test
    void changeStatusById() {
        personFacade.changeStatusById(isA(Integer.class));
        verify(personService, times(1)).changeStatusById(isA(Integer.class));
    }

    @Test
    void isExistByEmail() {
        personFacade.isExistByEmail(isA(String.class));
        verify(personService, times(1)).isExistByEmail(any());
    }

    @Test
    void isExistByUsername() {
        personFacade.isExistByUsername(isA(String.class));
        verify(personService, times(1)).isExistByUsername(any());
    }

    @Test
    void subscribe() {
        personFacade.subscribe(isA(Integer.class), isA(Integer.class));
        verify(personService, times(1)).subscribe(isA(Integer.class), isA(Integer.class));
    }
}