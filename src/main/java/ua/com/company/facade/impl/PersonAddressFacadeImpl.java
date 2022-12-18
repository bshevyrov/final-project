package ua.com.company.facade.impl;

import ua.com.company.facade.PersonAddressFacade;
import ua.com.company.service.PersonAddressService;
import ua.com.company.view.dto.PersonAddressDTO;

import java.util.List;

public class PersonAddressFacadeImpl implements PersonAddressFacade {

    private  final PersonAddressService personAddressService;

    public PersonAddressFacadeImpl(PersonAddressService personAddressService) {
        this.personAddressService = personAddressService;
    }

    @Override
    public void create(PersonAddressDTO personAddressDTO) {

    }

    @Override
    public void update(PersonAddressDTO personAddressDTO) {

    }

    @Override
    public void delete(int id) {

    }

    @Override
    public PersonAddressDTO findById(int id) {
        return null;
    }

    @Override
    public List<PersonAddressDTO> findAll() {
        return null;
    }
}
