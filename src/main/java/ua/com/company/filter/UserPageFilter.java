package ua.com.company.filter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import ua.com.company.view.dto.PersonDTO;

import java.io.IOException;

public class UserPageFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        HttpSession session = req.getSession(false);
        String loginURL = req.getContextPath() + "/login";
        boolean loggedIn = (session != null && session.getAttribute("loggedPerson") != null)
                && ("ROLE_CUSTOMER".equals(((PersonDTO) session.getAttribute("loggedPerson")).getRole().name())
                || "ROLE_ADMIN".equals(((PersonDTO) session.getAttribute("loggedPerson")).getRole().name()));

        if (loggedIn) {
            chain.doFilter(request, response);
        } else {
            resp.sendRedirect(loginURL);
        }
    }
}
