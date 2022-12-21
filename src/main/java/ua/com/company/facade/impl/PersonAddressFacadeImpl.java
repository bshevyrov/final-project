package ua.com.company.facade.impl;

import ua.com.company.facade.PersonAddressFacade;
import ua.com.company.service.PersonAddressService;
import ua.com.company.utils.ClassConverter;
import ua.com.company.view.dto.PersonAddressDTO;

import java.util.List;
import java.util.stream.Collectors;

public class PersonAddressFacadeImpl implements PersonAddressFacade {
    private final PersonAddressService personAddressService;

    public PersonAddressFacadeImpl(PersonAddressService personAddressService) {
        this.personAddressService = personAddressService;
    }

    @Override
    public void create(PersonAddressDTO personAddressDTO) {
        personAddressService.create(ClassConverter.personAddressDTOToPersonAddress(personAddressDTO));
    }

    @Override
    public void update(PersonAddressDTO personAddressDTO) {
        personAddressService.update(ClassConverter.personAddressDTOToPersonAddress(personAddressDTO));

    }

    @Override
    public void delete(int id) {
        personAddressService.delete(id);
    }

    @Override
    public PersonAddressDTO findById(int id) {
        return ClassConverter.personAddressToPersonAddressDTO(personAddressService.findById(id));
    }

    @Override
    public List<PersonAddressDTO> findAll() {
        return personAddressService.findAll().stream()
                .map(ClassConverter::personAddressToPersonAddressDTO)
                .collect(Collectors.toList());
    }
}
