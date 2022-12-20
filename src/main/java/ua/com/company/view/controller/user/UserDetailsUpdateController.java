package ua.com.company.view.controller.user;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ua.com.company.facade.PersonAddressFacade;
import ua.com.company.facade.PersonFacade;
import ua.com.company.view.dto.PersonAddressDTO;
import ua.com.company.view.dto.PersonDTO;

import java.io.IOException;

public class UserDetailsUpdateController extends HttpServlet {
    private PersonFacade personFacade;
    private PersonAddressFacade personAddressFacade;
    private PersonDTO personDTO;

    @Override
    public void init() throws ServletException {
        personFacade = (PersonFacade) getServletContext().getAttribute("personFacade");
        personAddressFacade = (PersonAddressFacade) getServletContext().getAttribute("personAddressFacade");
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response) {

        RequestDispatcher dispatcher = request.getRequestDispatcher(
                "/WEB-INF/jsp/user/user-details-update.jsp");
        try {
            dispatcher.forward(request, response);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int personId = ((PersonDTO) request.getSession().getAttribute("loggedPerson")).getId();
        personDTO = personFacade.findById(personId);
        request.setAttribute("person", personDTO);
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        PersonDTO currentPersonDTO = new PersonDTO();
        //TODO validate username not exist
        currentPersonDTO.setId(personDTO.getId());
        currentPersonDTO.setUsername(request.getParameter("username"));
        currentPersonDTO.setEmail(request.getParameter("email"));
        if (!this.personDTO.getEmail().equals(currentPersonDTO.getEmail()) || !this.personDTO.getUsername().equals(currentPersonDTO.getUsername())) {
            personFacade.update(currentPersonDTO);

        }
        PersonAddressDTO personAddressDTO = new PersonAddressDTO();
        personAddressDTO.setFirstName(request.getParameter("firstName"));
        personAddressDTO.setLastName(request.getParameter("lastName"));
        personAddressDTO.setAddress(request.getParameter("address"));
        personAddressDTO.setCity(request.getParameter("city"));
        personAddressDTO.setCountry(request.getParameter("country"));
        personAddressDTO.setPhone(Integer.parseInt(request.getParameter("phone")));
        personAddressDTO.setPostalCode(Integer.parseInt(request.getParameter("postalCode")));
        personAddressDTO.setPersonId(personDTO.getId());

        if (personDTO.getPersonAddressDTO() != null) {
            if (!personDTO.getPersonAddressDTO().equals(personAddressDTO)) {
                personAddressDTO.setId(personDTO.getPersonAddressDTO().getId());
                personAddressFacade.update(personAddressDTO);
            }
        } else {
            personAddressFacade.create(personAddressDTO);
        }

//        personDTO.setAvatar();

        response.sendRedirect("/user/details");
        //todo update
//        processRequest(request, response);

    }
}
