package ua.com.company.view.controller.user;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ua.com.company.dao.PublicationDAO;
import ua.com.company.dao.mysql.impl.MysqlPublicationDAOImpl;
import ua.com.company.entity.Publication;
import ua.com.company.exception.DBException;

import java.io.IOException;
import java.util.List;

public class UserSubscriptionsController extends HttpServlet {

    private void processRequest(HttpServletRequest request, HttpServletResponse response) {

        RequestDispatcher dispatcher = request.getRequestDispatcher(
                "/WEB-INF/jsp/user/user-subscriptions.jsp");
        try {
            dispatcher.forward(request, response);
        } catch (ServletException | IOException e) {
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
        request.setAttribute("publications", publicationList);
        processRequest(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }
}
