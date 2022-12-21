package ua.com.company.facade.impl;

import ua.com.company.facade.PersonFacade;
import ua.com.company.service.PersonService;
import ua.com.company.utils.ClassConverter;
import ua.com.company.view.dto.PersonDTO;

import java.util.List;
import java.util.stream.Collectors;

public class PersonFacadeImpl implements PersonFacade {
    private final PersonService personService;

    public PersonFacadeImpl(PersonService personService) {
        this.personService = personService;
    }

    @Override
    public void create(PersonDTO personDTO) {
        personService.create(ClassConverter.personDTOToPerson(personDTO));
    }

    @Override
    public void update(PersonDTO personDTO) {
        personService.update(ClassConverter.personDTOToPerson(personDTO));
    }

    @Override
    public void delete(int id) {
        personService.delete(id);
    }


    @Override
    public List<PersonDTO> findAll() {
        return personService.findAll().stream()
                .map(ClassConverter::personToPersonDTO)
                .collect(Collectors.toList());
    }

    @Override
    public void changeStatusById(int id) {
        personService.changeStatusById(id);
    }

    @Override
    public PersonDTO findByEmail(String email) {
        //TODO WHEN SUBSCRIBE THEN CHANGE SUBSCRIBE ICON  BY TAKE FROM LOGGED PERSON
        return ClassConverter.personToPersonDTO(personService.findByEmail(email));
    }

    @Override
    public PersonDTO findById(int id) {
        return ClassConverter.personToPersonDTO(personService.findById(id));
    }

    @Override
    public boolean isExistByEmail(String email) {
        return personService.isExistByEmail(email);
    }

    @Override
    public boolean isExistByUsername(String username) {
        return personService.isExistByUsername(username);
    }

    @Override
    public void subscribe(int pubId, int personId) {
        personService.subscribe(pubId, personId);
    }
}
