package ua.com.company.view.controller.publication;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ua.com.company.entity.Publication;
import ua.com.company.facade.PersonFacade;
import ua.com.company.facade.PublicationFacade;
import ua.com.company.service.PublicationService;
import ua.com.company.view.dto.PublicationDTO;

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
        PublicationFacade publicationFacade = (PublicationFacade) getServletContext()
                .getAttribute("publicationFacade");
        int publicationId = Integer.parseInt(request.getParameter("id"));
        PublicationDTO publication;
        publication = publicationFacade.findById(publicationId);
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
