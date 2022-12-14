package ua.com.company.view.controller.auth;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.com.company.facade.PersonFacade;
import ua.com.company.view.dto.PersonDTO;

import java.io.IOException;

public class SignUpController extends HttpServlet {
    private final Logger log = LogManager.getLogger(LoginController.class);
    PersonFacade personFacade;

    @Override
    public void init() throws ServletException {
        personFacade = (PersonFacade) getServletContext().getAttribute("personFacade");
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response) {
        RequestDispatcher dispatcher = request.getRequestDispatcher(
                "/WEB-INF/jsp/auth/signup.jsp");
        try {
            dispatcher.forward(request, response);
        } catch (ServletException | IOException e) {
            log.error("SignUpController error", e);
        }
    }

    @Override
    protected void doGet(
            HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(
            HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String email = request.getParameter("email");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        if (isValid(request, email, username, personFacade)) {
            PersonDTO person = new PersonDTO();
            person.setEmail(email);
            person.setUsername(username);
            person.setPassword(password);
            personFacade.create(person);

            HttpSession session = request.getSession(false);
            session.setAttribute("loggedPerson", personFacade.findByEmail(email));
            response.sendRedirect("/");
            return;
        }
        processRequest(request, response);
    }

    private boolean isValid(HttpServletRequest request, String email, String username, PersonFacade personFacade) {
        boolean rsl = true;
        if (personFacade.isExistByEmail(email)) {
            request.setAttribute("emailBusy", true);
            rsl = false;
        } else if (personFacade.isExistByUsername(username)) {
            request.setAttribute("usernameBusy", true);
            rsl = false;
        }
        return rsl;
    }
}
