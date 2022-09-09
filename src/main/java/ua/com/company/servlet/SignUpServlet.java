package ua.com.company.servlet;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ua.com.company.entity.Person;
import ua.com.company.service.PersonService;
import ua.com.company.service.impl.PersonServiceImpl;

import java.io.IOException;

@WebServlet(name = "SignUpServlet", value = "/signup")
public class SignUpServlet extends HttpServlet {


    private void processRequest(HttpServletRequest request, HttpServletResponse response) {

        RequestDispatcher dispatcher = request.getRequestDispatcher(
                "/WEB-INF/jsp/signup.jsp");
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
        Person person = new Person();
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        person.setEmail(email);
        person.setPassword(password);
        PersonService personService = new PersonServiceImpl();
        personService.create(person);


        processRequest(request, response);
    }
}
