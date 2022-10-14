package ua.com.company.controller.admin;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import ua.com.company.entity.Person;
import ua.com.company.service.PersonService;

import java.io.IOException;
import java.util.List;

public class AdminUserDashboardController extends HttpServlet {
    private void processRequest(HttpServletRequest request, HttpServletResponse response) {
        RequestDispatcher dispatcher = request.getRequestDispatcher(
                "/WEB-INF/jsp/admin/admin-user-dashboard.jsp");
        try {
            dispatcher.forward(request, response);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PersonService personService = (PersonService) getServletContext().getAttribute("personService");
        List<Person> personList = personService.findAll();
        request.setAttribute("personList", personList);
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("changeStatusId");
        PersonService personService = (PersonService) getServletContext().getAttribute("personService");

        if (personService.changeStatusById(Integer.parseInt(id))) {
//            HttpSession session = getServletContext().
            //TODO exit session
        }
        response.sendRedirect("/admin/user/dashboard");

    }
}
