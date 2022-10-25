package ua.com.company.view.controller.user;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ua.com.company.facade.PersonFacade;
import ua.com.company.facade.PublicationFacade;
import ua.com.company.view.dto.PersonDTO;
import ua.com.company.view.dto.PublicationDTO;

import java.io.IOException;

public class UserSubscribeController extends HttpServlet {

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
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PersonFacade personFacade = (PersonFacade) getServletContext().getAttribute("personFacade");
        int publicationId  = Integer.parseInt(request.getParameter("id"));

        personFacade.subscribe(publicationId,((PersonDTO)request.getSession(false).getAttribute("loggedPerson")).getId());
        PublicationDTO publication = ((PublicationFacade) getServletContext().getAttribute("publicationFacade")).findById(publicationId);
        request.setAttribute("publication", publication);

        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }
}


