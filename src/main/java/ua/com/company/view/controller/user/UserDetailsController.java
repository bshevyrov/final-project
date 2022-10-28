package ua.com.company.view.controller.user;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ua.com.company.facade.PersonFacade;
import ua.com.company.facade.impl.PersonFacadeImpl;
import ua.com.company.service.PersonService;
import ua.com.company.view.dto.PersonDTO;

import java.io.IOException;

public class UserDetailsController extends HttpServlet {

    private PersonService personService;
    private ServletContext sc;

/*    @Override
    public void init(ServletConfig config) throws ServletException {
        sc = config.getServletContext();
        personService = (PersonService) sc.getAttribute("personService");
        try {
            sc.setAttribute("person",personService.findById(1));
        } catch (DBException e) {
            e.printStackTrace();
        }
    }*/

    private void processRequest(HttpServletRequest request, HttpServletResponse response) {

        RequestDispatcher dispatcher = request.getRequestDispatcher(
                "/WEB-INF/jsp/user/user-details.jsp");
        try {
            dispatcher.forward(request, response);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int personId = ((PersonDTO) request.getSession().getAttribute("loggedPerson")).getId();
        PersonDTO person;
        PersonFacade personFacade = new PersonFacadeImpl();
        person = personFacade.findById(personId);

        request.setAttribute("person", person);
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //todo update
        processRequest(request, response);

    }
}
