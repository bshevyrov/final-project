package ua.com.company.view.controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.com.company.facade.PublicationFacade;

import java.io.IOException;

public class IndexPageController extends HttpServlet {
    private final Logger log = LogManager.getLogger(IndexPageController.class);
    private PublicationFacade publicationFacade;

    @Override
    public void init() throws ServletException {
        publicationFacade = (PublicationFacade) getServletContext()
                .getAttribute("publicationFacade");
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response) {
        RequestDispatcher dispatcher = request.getRequestDispatcher(
                "/WEB-INF/jsp/index.jsp");
        try {
            dispatcher.forward(request, response);
        } catch (ServletException | IOException e) {
            log.error("IndexPageController error", e);
        }
    }

    @Override
    protected void doGet(
            HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setAttribute("publications", publicationFacade.findAll());
        processRequest(request, response);
    }

}
