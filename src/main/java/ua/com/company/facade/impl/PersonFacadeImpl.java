package ua.com.company.facade.impl;

import ua.com.company.dao.DAOFactory;
import ua.com.company.entity.Person;
import ua.com.company.entity.Publication;
import ua.com.company.facade.PersonFacade;
import ua.com.company.service.PersonService;
import ua.com.company.service.PublicationService;
import ua.com.company.service.impl.PersonServiceImpl;
import ua.com.company.service.impl.PublicationServiceImpl;
import ua.com.company.view.dto.PersonDTO;
import ua.com.company.view.dto.PublicationDTO;

import java.util.ArrayList;
import java.util.List;

public class PersonFacadeImpl implements PersonFacade {
    private final PersonService personService = new PersonServiceImpl(DAOFactory.getInstance().getPersonDAO());
    private final PublicationService publicationService = new PublicationServiceImpl(DAOFactory.getInstance().getPublicationDAO());
    @Override
    public void create(PersonDTO personDTO) {

    }

    @Override
    public void update(PersonDTO personDTO) {

    }

    @Override
    public void delete(int id) {

    }

    @Override
    public PersonDTO findById(int id) {
        PersonDTO personDTO = new PersonDTO();
        PublicationDTO publicationDTO = new PublicationDTO();
        Person person = personService.findById(id);
        List<Publication> publicationList = new ArrayList<>();
        for (int i : person.getPublicationsId()) {
            publicationList.add(publicationService.findById(i));
        }

        return null;

    }

    @Override
    public List<PersonDTO> findAll() {
        return null;
    }
}
