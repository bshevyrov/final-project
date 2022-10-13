package ua.com.company.controller.auth;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import ua.com.company.entity.Person;
import ua.com.company.service.PersonService;
import ua.com.company.utils.PasswordUtil;

import java.io.IOException;

public class LoginController extends HttpServlet {
    private void processRequest(HttpServletRequest request, HttpServletResponse response) {

        RequestDispatcher dispatcher = request.getRequestDispatcher(
                "/WEB-INF/jsp/auth/login.jsp");
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
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String pass = request.getParameter("pass");
        PersonService personService = (PersonService) getServletContext().getAttribute("personService");
        if (personService.isExistByEmail(email)) {
            Person person = personService.findSimpleByEmail(email);
            if (PasswordUtil.validatePassword(pass, person.getPassword())) {
//            set session person
                HttpSession session = request.getSession(false);
                session.setAttribute("loggedPerson", person);
                response.sendRedirect("/");
                return;
            }
            request.setAttribute("passWrong", true);
        } else {
            request.setAttribute("emailWrong", true);
        }
        processRequest(request, response);

    }
}