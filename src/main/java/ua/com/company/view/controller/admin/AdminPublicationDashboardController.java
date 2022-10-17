package ua.com.company.view.controller.admin;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ua.com.company.facade.PublicationFacade;
import ua.com.company.view.dto.PublicationDTO;

import java.io.IOException;
import java.util.List;

public class AdminPublicationDashboardController extends HttpServlet {
    private void processRequest(HttpServletRequest request, HttpServletResponse response) {

        RequestDispatcher dispatcher = request.getRequestDispatcher(
                "/WEB-INF/jsp/admin/admin-publication-dashboard.jsp");
        try {
            dispatcher.forward(request, response);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PublicationFacade publicationFacade = (PublicationFacade) getServletContext().getAttribute("publicationFacade");
        List<PublicationDTO> publicationList = publicationFacade.findAll();
        request.setAttribute("publicationList", publicationList);
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
