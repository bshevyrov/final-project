package ua.com.company.filter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import ua.com.company.view.dto.PersonDTO;

import java.io.IOException;

public class UserPageFilter implements Filter {
    public void init(FilterConfig config) throws ServletException {
    }

    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        HttpSession session = req.getSession(false);
//        String userDetailUrl = req.getContextPath() + "/user/user-details.jsp";
//        System.out.println("userDetailUrl = " + userDetailUrl);
//        String userSubscriptionsUrl = req.getContextPath() + "/user/user-subscriptions.jsp";
//        System.out.println("userSubscriptionsUrl = " + userSubscriptionsUrl);
        String loginURL = req.getContextPath() + "/login";
//        int currentUserId = ((Person) session.getAttribute("person")).getId();
        //    System.out.println("currentUserId = " + currentUserId);
        boolean loggedIn = /*(session != null)
                && */
                (session.getAttribute("loggedPerson") != null)
                        && ("ROLE_CUSTOMER".equals(((PersonDTO) session.getAttribute("loggedPerson")).getRole().name())
                        || "ROLE_ADMIN".equals(((PersonDTO) session.getAttribute("loggedPerson")).getRole().name()));

        if (loggedIn) {//&&requestId==curentuserId)|| ROLE_ADMIN
            chain.doFilter(request, response);
        } else {
            resp.sendRedirect(loginURL);
        }
    }
}
