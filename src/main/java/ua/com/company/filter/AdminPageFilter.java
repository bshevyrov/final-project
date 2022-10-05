package ua.com.company.filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import ua.com.company.entity.Person;

import java.io.IOException;

@WebFilter(filterName = "AdminPageFilter")
public class AdminPageFilter implements Filter {
    public void init(FilterConfig config) throws ServletException {
    }

    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        HttpSession session = req.getSession(false);
        String loginURL = req.getContextPath() + "/login";
        boolean loggedIn = (session != null)
                && (session.getAttribute("person") != null)
                &&"ROLE_ADMIN".equals(((Person)session.getAttribute("person")).getRole().name());
        if (loggedIn) {//&&requestId==curentuserId)|| ROLE_ADMIN
            chain.doFilter(request, response);
        } else {
            resp.sendRedirect(loginURL);
        }
    }
}
