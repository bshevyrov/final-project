package ua.com.company.view.controller.user;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.com.company.facade.PersonFacade;
import ua.com.company.view.dto.PersonDTO;

import java.io.IOException;

public class UserDetailsController extends HttpServlet {

    private PersonFacade personFacade;
    private final Logger log = LogManager.getLogger(UserDetailsController.class);


    @Override
    public void init() throws ServletException {
        personFacade = (PersonFacade) getServletContext().getAttribute("personFacade");
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response) {

        RequestDispatcher dispatcher = request.getRequestDispatcher(
                "/WEB-INF/jsp/user/user-details.jsp");
        try {
            dispatcher.forward(request, response);
        } catch (ServletException | IOException e) {
            log.error("UserDetailsController error", e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int personId = ((PersonDTO) request.getSession().getAttribute("loggedPerson")).getId();
        request.setAttribute("person", personFacade.findById(personId));
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //todo update
        processRequest(request, response);
    }
}
