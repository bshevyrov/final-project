package ua.com.company.filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import ua.com.company.entity.Person;

import java.io.IOException;

//@WebFilter(filterName = "RoleFilter", value = "*")
public class RoleFilter implements Filter {
    //before authorization
 /*   private   PersonService personService;
    public void init(FilterConfig config) throws ServletException {
         personService = (PersonService) config.getServletContext().getAttribute("personService");
    }

*/
    public void destroy() {
    }

    //after
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
      //loging
//        System.out.println("RoleFilter before chain");
//
//        HttpServletRequest req = (HttpServletRequest) request;
//        HttpSession session = req.getSession(false);
//        if (session != null){
//            Person person = (Person) session.getAttribute("person");
//            if(!"ROLE_ADMIN".equals(person.getRole().name())){
//                ((HttpServletResponse) response).sendRedirect("/");
//                return;
//            }
//        }
        chain.doFilter(request, response);
        System.out.println("RoleFilter after chain");
    }
}
