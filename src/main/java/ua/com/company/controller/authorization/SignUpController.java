package ua.com.company.controller.authorization;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ua.com.company.dao.PersonDAO;
import ua.com.company.dao.mysql.impl.MysqlPersonDAOImpl;
import ua.com.company.entity.Person;
import ua.com.company.service.PersonService;
import ua.com.company.service.impl.PersonServiceImpl;

import java.io.IOException;

@WebServlet(name = "SignUpServlet", value = "/signup")
public class SignUpController extends HttpServlet {


    private void processRequest(HttpServletRequest request, HttpServletResponse response) {

        RequestDispatcher dispatcher = request.getRequestDispatcher(
                "/WEB-INF/jsp/authorization/signup.jsp");
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
        PersonDAO personDAO = new MysqlPersonDAOImpl();
        PersonService personService = new PersonServiceImpl(personDAO);
        if(
        isValid(request, email, username, personService)){
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
        if (personService.findByEmail(email).isPresent()) {
            request.setAttribute("email", "true");
            rsl = false;
        } else if (personService.findByUsername(username).isPresent()) {
            request.setAttribute("username", "true");
            rsl = false;
        }

        return rsl;
    }
}
