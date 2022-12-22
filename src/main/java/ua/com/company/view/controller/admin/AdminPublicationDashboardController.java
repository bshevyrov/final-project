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

public class AdminPublicationDashboardController extends HttpServlet {
    PublicationFacade publicationFacade;
    Logger log = LogManager.getLogger(AdminPublicationDashboardController.class);

    @Override
    public void init(ServletConfig config) throws ServletException {
        publicationFacade = (PublicationFacade) getServletContext().getAttribute("publicationFacade");
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response) {

        RequestDispatcher dispatcher = request.getRequestDispatcher(
                "/WEB-INF/jsp/admin/admin-publication-dashboard.jsp");
        try {
            dispatcher.forward(request, response);
        } catch (ServletException | IOException e) {
            log.error("AdminPublicationDashboardController error", e);
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
