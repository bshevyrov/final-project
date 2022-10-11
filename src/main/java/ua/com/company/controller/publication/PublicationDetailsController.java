package ua.com.company.controller.publication;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ua.com.company.entity.Publication;
import ua.com.company.service.PublicationService;

import java.io.IOException;


///details?title=

public class PublicationDetailsController extends HttpServlet {

    private void processRequest(HttpServletRequest request, HttpServletResponse response) {

        RequestDispatcher dispatcher = request.getRequestDispatcher(
                "/WEB-INF/jsp/publication/publication-details.jsp");
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
        PublicationService publicationService = (PublicationService) getServletContext()
                .getAttribute("publicationService");
        int publicationId = Integer.parseInt(request.getParameter("id"));
        Publication publication = null;
        publication = publicationService.findById(publicationId);
        request.setAttribute("publication", publication);
        processRequest(request, response);
    }

    @Override
    protected void doPost(
            HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
}
