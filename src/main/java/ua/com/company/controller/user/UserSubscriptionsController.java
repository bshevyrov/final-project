package ua.com.company.controller.user;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import ua.com.company.dao.PublicationDAO;
import ua.com.company.dao.mysql.impl.MysqlPublicationDAOImpl;
import ua.com.company.entity.Publication;
import ua.com.company.exception.DBException;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "UserSubscriptionsServlet", value = "/user/subscriptions")
public class UserSubscriptionsController extends HttpServlet {

    private void processRequest(HttpServletRequest request, HttpServletResponse response) {
//        String studentID = request.getParameter("id");
//        if (studentID != null) {
//            int id = Integer.parseInt(studentID);
//            studentService.getStudent(id)
//                    .ifPresent(s -> request.setAttribute("studentRecord", s));
        RequestDispatcher dispatcher = request.getRequestDispatcher(
                "/WEB-INF/jsp/user/user-subscriptions.jsp");
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
        PublicationDAO publicationDAO = new MysqlPublicationDAOImpl();
        int userId = (int) request.getSession().getAttribute("id");
        List<Publication> publicationList = null;
        try {
            publicationList = publicationDAO.findAllByUserId(userId);
        } catch (DBException e) {
            e.printStackTrace();
        }
        request.setAttribute("publications",publicationList);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
