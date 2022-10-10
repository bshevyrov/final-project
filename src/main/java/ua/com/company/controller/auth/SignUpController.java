package ua.com.company.controller.auth;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
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
            personService.create(person);
        }
        processRequest(request, response);
    }

    private boolean isValid(HttpServletRequest request, String email, String username, PersonService personService) {
        boolean rsl = true;
        if (personService.isExistByEmail(email)) {
            request.setAttribute("email", "true");
            rsl = false;
        } else if (personService.isExistByUsername(username)) {
            request.setAttribute("username", "true");
            rsl = false;
        }
        return rsl;
    }
}
