package ua.com.company.filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebFilter(filterName = "ExceptionFilter")
public class ExceptionFilter implements Filter {

/*    public class MyFilter extends CustomFilter{

        private static final Map<String, String> exceptionMap = new HashMap<>();

        public void init(FilterConfig config) throws ServletException {
            super.init(config);
            exceptionMap.put("/requestURL", "/redirectURL");
            exceptionMap.put("/someOtherrequestURL", "/someOtherredirectURL");
        }*/

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletResponse req = (HttpServletResponse) response;

        try{
                chain.doFilter(request, response);
            }catch(IllegalArgumentException e){
            //log
                System.out.println("CATCH");
//            String errURL = exceptionMap.get(request.getRequestURI());
//            if(errURL != null){
                req.sendRedirect("/npe");
            }
        }
    }
/*
}
    public void init(FilterConfig config) throws ServletException {
    }

    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        chain.doFilter(request, response);
    }
}
*/
