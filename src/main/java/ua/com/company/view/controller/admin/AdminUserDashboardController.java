package ua.com.company.view.controller.admin;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.com.company.facade.PersonFacade;
import ua.com.company.view.dto.PersonDTO;

import java.io.IOException;
import java.util.List;

public class AdminUserDashboardController extends HttpServlet {
    private PersonFacade personFacade;
    private final Logger log = LogManager.getLogger(AdminUserDashboardController.class);

    @Override
    public void init() throws ServletException {
        personFacade = (PersonFacade) getServletContext().getAttribute("personFacade");
    }


    private void processRequest(HttpServletRequest request, HttpServletResponse response) {
        RequestDispatcher dispatcher = request.getRequestDispatcher(
                "/WEB-INF/jsp/admin/admin-user-dashboard.jsp");
        try {
            dispatcher.forward(request, response);
        } catch (ServletException | IOException e) {
            log.error("AdminUserDashboardController error", e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("personList", personFacade.findAll());
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<HttpSession> currentSessions = (List<HttpSession>) getServletContext().getAttribute("openSessions");
        int personId = Integer.parseInt(request.getParameter("changeStatusId"));
        personFacade.changeStatusById(personId);
        for (int i = 0; i < currentSessions.size(); i++) {
            if (((PersonDTO) currentSessions.get(i).getAttribute("loggedPerson")).getId() == personId) {
                currentSessions.get(i).invalidate();
                currentSessions.remove(i);
                break;
            }
        }
        response.sendRedirect("/admin/user/dashboard");
    }
}
