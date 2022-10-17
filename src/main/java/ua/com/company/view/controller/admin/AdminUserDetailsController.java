package ua.com.company.view.controller.admin;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ua.com.company.entity.Person;
import ua.com.company.facade.PersonFacade;
import ua.com.company.service.PersonService;
import ua.com.company.view.dto.PersonDTO;

import java.io.IOException;

public class AdminUserDetailsController extends HttpServlet {
    private void processRequest(HttpServletRequest request, HttpServletResponse response) {

        RequestDispatcher dispatcher = request.getRequestDispatcher(
                "/WEB-INF/jsp/admin/admin-user-details.jsp");
        try {
            dispatcher.forward(request, response);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        //TODO validation
        PersonFacade personFacade = (PersonFacade) getServletContext().getAttribute("personFacade");
        PersonDTO personDTO = (personFacade.findById(Integer.parseInt(id)));
        request.setAttribute("person", personDTO);
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("changeStatusId");
        PersonFacade personFacade = (PersonFacade) getServletContext().getAttribute("personFacade");

        if (personFacade.changeStatusById(Integer.parseInt(id))) {
//            HttpSession session = getServletContext().
            //TODO exit session
        }
        response.sendRedirect("/admin/user/details?id=" + id);

    }
}
