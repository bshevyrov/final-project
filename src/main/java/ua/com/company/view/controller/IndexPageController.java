package ua.com.company.view.controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ua.com.company.entity.Image;
import ua.com.company.entity.Publication;
import ua.com.company.facade.PublicationFacade;
import ua.com.company.service.ImageService;
import ua.com.company.service.PublicationService;
import ua.com.company.service.impl.ImageServiceImpl;
import ua.com.company.view.dto.PublicationDTO;

import java.io.IOException;
import java.util.List;

//@WebServlet(name = "IndexPageServlet", value = "")
public class IndexPageController extends HttpServlet {

    private void processRequest(HttpServletRequest request, HttpServletResponse response) {
        RequestDispatcher dispatcher = request.getRequestDispatcher(
                "/WEB-INF/jsp/index.jsp");
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
        List<PublicationDTO> publications;
        publications = publicationFacade.findAll();
        request.setAttribute("publications", publications);

        processRequest(request, response);
    }

    @Override
    protected void doPost(
            HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
}
