package ua.com.company.view.controller.admin;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import ua.com.company.facade.PersonFacade;
import ua.com.company.facade.PublicationFacade;
import ua.com.company.service.PersonService;
import ua.com.company.utils.CurrentSessionsThreadLocal;
import ua.com.company.view.dto.PersonDTO;

import java.io.IOException;
import java.util.List;

public class AdminUserDashboardController extends HttpServlet {
    private void processRequest(HttpServletRequest request, HttpServletResponse response) {
        RequestDispatcher dispatcher = request.getRequestDispatcher(
                "/WEB-INF/jsp/admin/admin-user-dashboard.jsp");
        try {
            dispatcher.forward(request, response);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PersonFacade personFacade = (PersonFacade) getServletContext().getAttribute("personFacade");
        List<PersonDTO> personList = personFacade.findAll();
        request.setAttribute("personList", personList);
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("changeStatusId");
        PersonFacade personFacade = (PersonFacade) getServletContext().getAttribute("personFacade");

        CurrentSessionsThreadLocal.set((List<HttpSession>) getServletContext().getAttribute("openSessions"));
        personFacade.changeStatusById(Integer.parseInt(id));
        CurrentSessionsThreadLocal.unset();
        response.sendRedirect("/admin/user/dashboard");

    }
}
