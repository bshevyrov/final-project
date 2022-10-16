package ua.com.company.view.controller.auth;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import ua.com.company.entity.Person;
import ua.com.company.service.PersonService;

import java.io.IOException;

public class SignUpController extends HttpServlet {

    private void processRequest(HttpServletRequest request, HttpServletResponse response) {

        RequestDispatcher dispatcher = request.getRequestDispatcher(
                "/WEB-INF/jsp/auth/signup.jsp");
        try {
            dispatcher.forward(request, response);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
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
        PersonService personService = (PersonService) getServletContext().getAttribute("personService");
        if (isValid(request, email, username, personService)) {
            Person person = new Person();
            person.setEmail(email);
            person.setUsername(username);
            person.setPassword(password);

            int id = personService.create(person);
            person.setId(id);
            HttpSession session = request.getSession(false);
            session.setAttribute("loggedPerson", person);
            response.sendRedirect("/");
            return;

        }
        processRequest(request, response);
    }

    private boolean isValid(HttpServletRequest request, String email, String username, PersonService personService) {
        boolean rsl = true;
        if (personService.isExistByEmail(email)) {
            request.setAttribute("emailBusy", true);
            rsl = false;
        } else if (personService.isExistByUsername(username)) {
            request.setAttribute("usernameBusy", true);
            rsl = false;
        }
        return rsl;
    }
}
