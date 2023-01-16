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
            res.sendRedirect("/");
        } catch (ImageNotFoundException e) {
            log.warn("Image not found ", e);
            res.sendRedirect("/");
        } catch (PersonAddressNotFoundException e) {
            log.warn("PersonAddress not found ", e);
            res.sendRedirect("/");
        } catch (PublicationCommentNotFoundException e) {
            log.warn("Publication comment not found ", e);
            res.sendRedirect("/");
        } catch (PublicationNotFoundException e) {
            log.warn("Publication not found ", e);
            res.sendRedirect("/");
        } catch (TopicNotFoundException e) {
            log.warn("Topic not found ", e);
            res.sendRedirect("/");
        }

    }
}

