package ua.com.company.controller.admin;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ua.com.company.entity.Person;
import ua.com.company.exception.DBException;
import ua.com.company.service.PersonService;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "AdminUserServlet", value = "/admin/user")
public class AdminUserDashboardController extends HttpServlet {
    private void processRequest(HttpServletRequest request, HttpServletResponse response) {

        RequestDispatcher dispatcher = request.getRequestDispatcher(
                "/WEB-INF/jsp/admin/admin-user-dashboard.jsp");
        try {
            dispatcher.forward(request, response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PersonService personService = (PersonService) request.getSession(false).getAttribute("personService");
        List<Person> personList = null;
        try {
            personList = personService.findAll();
        } catch (DBException e) {
            e.printStackTrace();
        }
        request.setAttribute("personList", personList);
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
