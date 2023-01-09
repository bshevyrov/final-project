package ua.com.company.view.controller.user;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.com.company.facade.ImageFacade;
import ua.com.company.facade.PersonAddressFacade;
import ua.com.company.facade.PersonFacade;
import ua.com.company.view.dto.ImageDTO;
import ua.com.company.view.dto.PersonAddressDTO;
import ua.com.company.view.dto.PersonDTO;

import java.io.IOException;

public class UserDetailsUpdateController extends HttpServlet {
    private PersonFacade personFacade;
    private PersonAddressFacade personAddressFacade;
    private ImageFacade imageFacade;
    private PersonDTO personDTO;

    private final Logger log = LogManager.getLogger(UserDetailsUpdateController.class);

    @Override
    public void init() throws ServletException {
        personFacade = (PersonFacade) getServletContext().getAttribute("personFacade");
        personAddressFacade = (PersonAddressFacade) getServletContext().getAttribute("personAddressFacade");
        imageFacade = (ImageFacade) getServletContext().getAttribute("imageFacade");
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response) {
        RequestDispatcher dispatcher = request.getRequestDispatcher(
                "/WEB-INF/jsp/user/user-details-update.jsp");
        try {
            dispatcher.forward(request, response);
        } catch (ServletException | IOException e) {
            log.error("UserDetailsUpdateController error", e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int personId = ((PersonDTO) request.getSession().getAttribute("loggedPerson")).getId();
        personDTO = personFacade.findById(personId);
        request.setAttribute("person", personFacade.findById(personId));
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        PersonDTO currentPersonDTO = new PersonDTO();
        currentPersonDTO.setId(personDTO.getId());
        String newUsername = request.getParameter("username");
        String newEmail = request.getParameter("email");
        String avatarPath = request.getParameter("avatarPath");
        boolean updatable = false;
        if(!avatarPath.equals(personDTO.getAvatar().getPath())){
            imageFacade.create(new ImageDTO("User "+currentPersonDTO.getId()+" avatar",avatarPath));
            currentPersonDTO.setAvatar(imageFacade.findByPath(avatarPath));
            updatable = true;
        } else {
            currentPersonDTO.setAvatar(personDTO.getAvatar());
        }
        if (!personDTO.getUsername().equals(newUsername)) {
            updatable = true;
            if (!personFacade.isExistByUsername(newUsername)) {
                currentPersonDTO.setUsername(newUsername);
            } else {
                request.setAttribute("usernameExist", true);
                request.setAttribute("person", personDTO);
                processRequest(request, response);
            }
        } else {
            currentPersonDTO.setUsername(personDTO.getUsername());
        }
        if (!personDTO.getEmail().equals(newEmail)) {
            updatable = true;
            if (!personFacade.isExistByEmail(newEmail)) {
                currentPersonDTO.setEmail(newEmail);
            } else {
                request.setAttribute("emailExist", true);
                request.setAttribute("person", personDTO);
                processRequest(request, response);
            }
        } else {
            currentPersonDTO.setEmail(personDTO.getEmail());
        }
        if (updatable) {
            personFacade.update(currentPersonDTO);
        }


        PersonAddressDTO personAddressDTO = getPersonAddressDTO(request);

        if (personDTO.getPersonAddressDTO() != null) {
            if (!personDTO.getPersonAddressDTO().equals(personAddressDTO)) {
                personAddressDTO.setId(personDTO.getPersonAddressDTO().getId());
                personAddressFacade.update(personAddressDTO);
            }
        } else {
            personAddressFacade.create(personAddressDTO);
        }
//TODO        personDTO.setAvatar();
        response.sendRedirect("/user/details");
    }

    private PersonAddressDTO getPersonAddressDTO(HttpServletRequest request) {
        PersonAddressDTO personAddressDTO = new PersonAddressDTO();
        personAddressDTO.setFirstName(request.getParameter("firstName"));
        personAddressDTO.setLastName(request.getParameter("lastName"));
        personAddressDTO.setAddress(request.getParameter("address"));
        personAddressDTO.setCity(request.getParameter("city"));
        personAddressDTO.setCountry(request.getParameter("country"));
        personAddressDTO.setPhone(Integer.parseInt(request.getParameter("phone")));
        personAddressDTO.setPostalCode(Integer.parseInt(request.getParameter("postalCode")));
        personAddressDTO.setPersonId(personDTO.getId());
        return personAddressDTO;
    }
}
