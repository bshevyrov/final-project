package ua.com.company.filter;

import com.mysql.cj.util.StringUtils;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;

public class RequestParameterFilter implements Filter {
   private final static Logger log = LogManager.getLogger(RequestParameterFilter.class);

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        if (req.getQueryString() != null && req.getQueryString().contains("=")) {
            String query = req.getQueryString().substring(req.getQueryString().indexOf("=") + 1);
            System.out.println(req.getQueryString());
            if (req.getMethod().equalsIgnoreCase("GET")
                    && (StringUtils.isEmptyOrWhitespaceOnly(query)
                    || !StringUtils.isStrictlyNumeric(query)
                    || Integer.parseInt(query) <= 0)) {
                res.sendRedirect("/");
                log.warn("Illegal request "+req.getQueryString() );
                return;
            }
        }
        chain.doFilter(request, response);
    }
}
