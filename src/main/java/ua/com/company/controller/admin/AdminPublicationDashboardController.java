package ua.com.company.controller.admin;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import ua.com.company.entity.Publication;
import ua.com.company.exception.DBException;
import ua.com.company.service.PublicationService;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "AdminPublicationDashboardServlet", value = "/admin/publication/dashboard")
public class AdminPublicationDashboardController extends HttpServlet {
    private void processRequest(HttpServletRequest request, HttpServletResponse response) {

        RequestDispatcher dispatcher = request.getRequestDispatcher(
                "/WEB-INF/jsp/admin/admin-publication-dashboard.jsp");
        try {
            dispatcher.forward(request, response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PublicationService publicationService = (PublicationService) getServletContext().getAttribute("publicationService");
            List<Publication> publicationList = null;
            try {
                publicationList = publicationService.findAll();
            } catch (DBException e) {
                e.printStackTrace();
            }
            request.setAttribute("publicationList",publicationList);
        processRequest(request, response);
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
