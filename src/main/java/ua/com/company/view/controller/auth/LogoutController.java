package ua.com.company.view.controller.auth;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.ArrayList;

public class LogoutController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ((ArrayList<HttpSession>) getServletContext().getAttribute("openSessions")).remove(request.getSession(false));
        request.getSession(false).invalidate();
        response.sendRedirect("/");
    }
}
