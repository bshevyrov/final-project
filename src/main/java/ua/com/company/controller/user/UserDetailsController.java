package ua.com.company.controller.user;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ua.com.company.dao.PersonDAO;
import ua.com.company.dao.mysql.impl.MysqlPersonDAOImpl;
import ua.com.company.entity.Person;
import ua.com.company.exception.DBException;
import ua.com.company.service.PersonService;

import java.io.IOException;

@WebServlet(name = "UserServlet", value = "/user/details")
public class UserDetailsController extends HttpServlet {

    private PersonService personService;
    private ServletContext sc;

/*    @Override
    public void init(ServletConfig config) throws ServletException {
        sc = config.getServletContext();
        personService = (PersonService) sc.getAttribute("personService");
        try {
            sc.setAttribute("person",personService.findById(1));
        } catch (DBException e) {
            e.printStackTrace();
        }
    }*/

    private void processRequest(HttpServletRequest request, HttpServletResponse response) {

        RequestDispatcher dispatcher = request.getRequestDispatcher(
                "/WEB-INF/jsp/user/user-details.jsp");
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
////            String userId = request.getParameter("id");
//        int personId = ((Person) request.getSession().getAttribute("person")).getId();
//
//        PersonDAO personDAO = new MysqlPersonDAOImpl();
////        int userId = (int) request.getSession().getAttribute("userId");
//        Person person = null;
//        try {
//            person = personDAO.findById(personId).get();
//        } catch (DBException e) {
//            e.printStackTrace();
//        }
//        request.setAttribute("person", person);
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);

    }
}