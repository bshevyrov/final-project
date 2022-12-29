package ua.com.company.view.controller.admin;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.com.company.facade.PublicationFacade;

import java.io.IOException;

public class AdminPublicationsDashboardController extends HttpServlet {
    private PublicationFacade publicationFacade;
    private final Logger log = LogManager.getLogger(AdminPublicationsDashboardController.class);

    @Override
    public void init() throws ServletException {
        publicationFacade = (PublicationFacade) getServletContext().getAttribute("publicationFacade");

    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response) {
        RequestDispatcher dispatcher = request.getRequestDispatcher(
                "/WEB-INF/jsp/admin/admin-publications-dashboard.jsp");
        try {
            dispatcher.forward(request, response);
        } catch (ServletException | IOException e) {
            log.error("AdminPublicationsDashboardController error", e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("publicationList", publicationFacade.findAll());
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        publicationFacade.delete(Integer.parseInt(request.getParameter("deleteId")));
        response.sendRedirect("/admin/publication/dashboard");
    }
}
