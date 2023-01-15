package ua.com.company.filter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.com.company.exception.*;

import java.io.IOException;

public class ExceptionFilter implements Filter {

    public final Logger log = LogManager.getLogger(this);

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletResponse res = (HttpServletResponse) response;

        try {
            chain.doFilter(request, response);
        } catch (UserNotFoundException e) {
            log.warn("Person not found ", e);
        } catch (ImageNotFoundException e) {
            log.warn("Image not found ", e);
        } catch (PersonAddressNotFoundException e) {
            log.warn("PersonAddress not found ", e);
        } catch (PublicationCommentNotFoundException e) {
            log.warn("Publication comment not found ", e);
        } catch (PublicationNotFoundException e) {
            log.warn("Publication not found " , e);

        } finally {
            res.sendRedirect("/");
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
