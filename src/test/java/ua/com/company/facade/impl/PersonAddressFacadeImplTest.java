package ua.com.company.facade.impl;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import ua.com.company.entity.PersonAddress;
import ua.com.company.service.PersonAddressService;
import ua.com.company.view.dto.PersonAddressDTO;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class PersonAddressFacadeImplTest {
    @InjectMocks
    PersonAddressFacadeImpl personAddressFacade;

    @Mock
    PersonAddressService personAddressService;

    PersonAddress personAddress;
    List<PersonAddress> persons;
    PersonAddressDTO personAddressDTO;

    @BeforeAll
    void init() {
        MockitoAnnotations.openMocks(this);
        personAddress = new PersonAddress();
        personAddressDTO = new PersonAddressDTO();
        persons = new ArrayList<>();
    }

    @Test
    void create() {
        doNothing().when(personAddressService).create(personAddress);
        personAddressFacade.create(personAddressDTO);
        verify(personAddressService, times(1)).create(personAddress);
    }

    @Test
    void delete() {
        doNothing().when(personAddressService).delete(anyInt());
        personAddressFacade.delete(anyInt());
        verify(personAddressService, times(1)).delete(anyInt());
    }

    @Test
    void update() {
        doNothing().when(personAddressService).update(personAddress);
        personAddressFacade.update(personAddressDTO);
        verify(personAddressService, times(1)).update(personAddress);
    }

    @Test
    void findById() {
        when(personAddressService.findById(anyInt())).thenReturn(personAddress);
        personAddressFacade.findById(anyInt());
        verify(personAddressService, times(1)).findById(anyInt());
    }

    @Test
    void findAll() {
        when(personAddressService.findAll()).thenReturn(persons);
        personAddressFacade.findAll();
        verify(personAddressService, times(1)).findAll();
    }
}