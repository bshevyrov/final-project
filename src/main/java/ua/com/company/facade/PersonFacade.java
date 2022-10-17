package ua.com.company.facade;

import ua.com.company.view.dto.PersonDTO;

public interface PersonFacade extends BaseFacade<PersonDTO>{
    boolean changeStatusById(int parseInt);

    PersonDTO findByEmail(String email);

    boolean isExistByEmail(String email);

    boolean isExistByUsername(String username);
}
