package ua.com.company.filter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import ua.com.company.entity.Person;

import java.io.IOException;

//@WebFilter(filterName = "UserPageFilter")
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
        boolean loggedIn = (session != null)
                && (session.getAttribute("person") != null)
                        &&("ROLE_USER".equals(((Person)session.getAttribute("person")).getRole().name())||"ROLE_ADMIN".equals(((Person)session.getAttribute("person")).getRole().name()));

        if (loggedIn) {//&&requestId==curentuserId)|| ROLE_ADMIN
            chain.doFilter(request, response);
        } else {
            resp.sendRedirect(loginURL);
        }
    }
}
