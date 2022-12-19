package ua.com.company.facade.impl;

import ua.com.company.entity.Person;
import ua.com.company.facade.PersonFacade;
import ua.com.company.service.PersonService;
import ua.com.company.service.PublicationService;
import ua.com.company.utils.ClassConverter;
import ua.com.company.view.dto.PersonDTO;
import ua.com.company.view.dto.PublicationDTO;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class PersonFacadeImpl implements PersonFacade {
    private final PersonService personService /*= PersonServiceImpl.getInstance()*/;
    private final PublicationService publicationService /*= PublicationServiceImpl.getInstance()*/;

    public PersonFacadeImpl(PersonService personService, PublicationService publicationService) {
        this.personService = personService;
        this.publicationService = publicationService;
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
        List<PersonDTO> personDTOList = new ArrayList<>();
        List<Person> personList = personService.findAll();
        for (Person person : personList) {
            personDTOList.add(ClassConverter.personToPersonDTO(person));
        }
        return personDTOList;
    }

    @Override
    public void changeStatusById(int id) {
         personService.changeStatusById(id);
    }

    @Override
    public PersonDTO findByEmail(String email) {
        //TODO WHEN SUBSCRIBE THEN CHANGE SUBSCRIBE ICON  BY TAKE FROM LOGGED PERSON
        return ClassConverter.personToPersonDTO(personService.findByEmail(email));    }


    @Override
    public PersonDTO findById(int id) {
       return ClassConverter.personToPersonDTO(personService.findById(id));    }

   /* private PersonDTO getPersonDTOFromPerson(Person person) {
        PersonDTO personDTO;
        List<PublicationDTO> publicationList = null;
        if (person.getPublicationList()!=null) {
            publicationList = Arrays.stream(person.getPublicationsId())
                    .boxed()
                    .map(publicationService::findById)
                    .map(ClassConverter::publicationToPublicationDTO)
                    .collect(Collectors.toList());
        }
        personDTO = ClassConverter.personToPersonDTO(person);
        personDTO.setPublications(publicationList);
        return personDTO;
    }*/


    @Override
    public boolean isExistByEmail(String email) {
        return personService.isExistByEmail(email);
    }

    @Override
    public boolean isExistByUsername(String username) {
        return personService.isExistByUsername(username);
    }

    @Override
    public void subscribe(int pubId,int personId) {
        personService.subscribe(pubId,personId);
    }
}
