package ua.com.company.filter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.List;

public class BanFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;

        if (req.getSession(false).getAttribute("loggedPerson") != null
                && !((List<HttpSession>) req.getServletContext().getAttribute("openSessions"))
                .contains(req.getSession(false))) {
            req.getSession(false).invalidate();
            resp.sendRedirect("/");
        }
        chain.doFilter(request, response);
    }
}
