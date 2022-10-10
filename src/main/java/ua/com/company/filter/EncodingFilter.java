package ua.com.company.filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import ua.com.company.entity.Person;

import java.io.IOException;

//@WebFilter(filterName = "EncodingFilter")
public class EncodingFilter implements Filter {
    private String encoding;

    public void init(FilterConfig config) throws ServletException {
        encoding = config.getInitParameter("encoding");
    }

    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest req = (HttpServletRequest) request;
        if (req.getCharacterEncoding() == null) {
           req.setCharacterEncoding(encoding);
            }
            chain.doFilter(request, response);
        }
    }

