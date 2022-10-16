package ua.com.company.view.controller.error;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import ua.com.company.exception.DBException;

import java.io.IOException;

@WebServlet(name = "ErrorController", value = "/err")
public class ErrorController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        if (name.equals("err")){
            throw new NullPointerException("Null");
        }
        throw new ServletException(new DBException("DBException"));
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
