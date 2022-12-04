package ua.com.company.filter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ua.com.company.type.StatusType;
import ua.com.company.view.dto.PersonDTO;

import java.io.IOException;

public class BanFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;

        if (req.getSession(false).getAttribute("loggedPerson") != null) {
            if (((PersonDTO) req.getSession(false).getAttribute("loggedPerson")).getStatus() == StatusType.DISABLED) {
                req.getSession(false).removeAttribute("loggedPerson");
            }
            resp.sendRedirect("/");
        } else {
            chain.doFilter(request, response);
        }
    }

}
