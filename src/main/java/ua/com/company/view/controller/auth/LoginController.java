package ua.com.company.view.controller.auth;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.com.company.facade.PersonFacade;
import ua.com.company.type.StatusType;
import ua.com.company.utils.PasswordUtil;
import ua.com.company.view.dto.PersonDTO;

import java.io.IOException;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;

public class LoginController extends HttpServlet {
    private final Logger log = LogManager.getLogger(LoginController.class);
    PersonFacade personFacade;

    @Override
    public void init() throws ServletException {
        personFacade = (PersonFacade) getServletContext().getAttribute("personFacade");
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response) {
        RequestDispatcher dispatcher = request.getRequestDispatcher(
                "/WEB-INF/jsp/auth/login.jsp");
        try {
            dispatcher.forward(request, response);
        } catch (ServletException | IOException e) {
            log.error("LoginController error", e);
        }
    }

    @Override
    protected void doGet(
            HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String pass = request.getParameter("pass");
        if (personFacade.isExistByEmail(email)) {
            PersonDTO personDTO = personFacade.findByEmail(email);
            if (PasswordUtil.validatePassword(pass, personDTO.getPassword())) {
                if (personDTO.getStatus() == StatusType.ENABLED) {
                    HttpSession session = request.getSession(false);
                    session.setAttribute("loggedPerson", personDTO);
                    ArrayList<HttpSession> currentSessions = (ArrayList<HttpSession>) getServletContext().getAttribute("openSessions");
                    for (int i = 0; i < currentSessions.size(); i++) {
                       try {
                           if ((currentSessions.get(i).getMaxInactiveInterval() * 1000L) <= ((Timestamp.from(Instant.now()).getTime() - currentSessions.get(i).getLastAccessedTime()))) {
                               currentSessions.remove(i);
                           }
                       }catch (IllegalStateException e){
                           currentSessions.remove(i);
                       }
                        if (currentSessions.get(i).getAttribute("loggedPerson").equals(personDTO)) {
                            currentSessions.get(i).invalidate();
                            currentSessions.remove(i);
                            break;
                        }
                    }
                    currentSessions.add(session);
                    response.sendRedirect("/");
                    return;
                } else {
                    request.setAttribute("ban", true);
                }
            }
            request.setAttribute("passWrong", true);
        } else {
            request.setAttribute("emailWrong", true);
        }
        processRequest(request, response);
    }
}