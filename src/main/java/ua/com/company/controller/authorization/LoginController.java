package ua.com.company.controller.authorization;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import ua.com.company.entity.Person;
import ua.com.company.service.PersonService;
import ua.com.company.utils.PasswordUtil;

import java.io.IOException;

@WebServlet(name = "LoginServlet", value = "/login")
public class LoginController extends HttpServlet {
    private void processRequest(HttpServletRequest request, HttpServletResponse response) {

        RequestDispatcher dispatcher = request.getRequestDispatcher(
                "/WEB-INF/jsp/authorization/login.jsp");
        try {
            dispatcher.forward(request, response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
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
        Person person = personService.findByEmail(email).get();
        if (PasswordUtil.validatePassword(pass, person.getPassword())) {
//            set session person
            HttpSession session = request.getSession(false);

            session.setAttribute("loggedPerson", person);
            response.sendRedirect("/");
            return;
        }

        processRequest(request, response);

    }
}