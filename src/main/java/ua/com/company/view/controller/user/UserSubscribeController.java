package ua.com.company.view.controller.user;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.com.company.facade.PersonFacade;
import ua.com.company.utils.ClassConverter;
import ua.com.company.view.dto.PersonDTO;

import java.io.IOException;

public class UserSubscribeController extends HttpServlet {
    private final Logger log = LogManager.getLogger(UserSubscribeController.class);
    private PersonFacade personFacade;

    @Override
    public void init() throws ServletException {
        personFacade = (PersonFacade) getServletContext().getAttribute("personFacade");
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response) {
        RequestDispatcher dispatcher = request.getRequestDispatcher(
                "/WEB-INF/jsp/publication/publication-details.jsp");
        try {
            dispatcher.forward(request, response);
        } catch (ServletException | IOException e) {
            log.error("UserSubscribeController error", e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int publicationId = Integer.parseInt(request.getParameter("id"));
        int currentPersonId = ((PersonDTO) request.getSession(false).getAttribute("loggedPerson")).getId();
        personFacade. subscribe(publicationId, currentPersonId);
        request.getSession(false).setAttribute("loggedPerson", personFacade.findById(currentPersonId));
        response.sendRedirect("/publication/details?id=" + publicationId);
    }
}


