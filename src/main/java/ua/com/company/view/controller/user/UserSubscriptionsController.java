package ua.com.company.view.controller.user;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ua.com.company.facade.PublicationFacade;
import ua.com.company.view.dto.PublicationDTO;

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
        PublicationFacade publicationFacade = (PublicationFacade) getServletContext().getAttribute("publicationFacade");
        int userId = (int) request.getSession().getAttribute("id");
        List<PublicationDTO> publicationList;
        publicationList = publicationFacade.findAllByUserId(userId);
        request.setAttribute("publications", publicationList);
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }
}
