package ua.com.company.controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ua.com.company.dao.PublicationDAO;
import ua.com.company.dao.mysql.impl.MysqlPublicationDAOImpl;
import ua.com.company.entity.Publication;
import ua.com.company.exception.DBException;
import ua.com.company.service.PublicationService;
import ua.com.company.service.impl.PublicationServiceImpl;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "IndexPageServlet", value = "")
public class IndexPageController extends HttpServlet {
    private PublicationService publicationService = new PublicationServiceImpl();

    private void processRequest(HttpServletRequest request, HttpServletResponse response) {
//        String studentID = request.getParameter("id");
//        if (studentID != null) {
//            int id = Integer.parseInt(studentID);
//            studentService.getStudent(id)
//                    .ifPresent(s -> request.setAttribute("studentRecord", s));
        RequestDispatcher dispatcher = request.getRequestDispatcher(
                "/WEB-INF/jsp/index.jsp");
        try {
            dispatcher.forward(request, response);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doGet(
            HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        PublicationDAO publicationDAO = new MysqlPublicationDAOImpl();
        List<Publication> publications;
        try {
            publications = publicationDAO.findAll();
            request.setAttribute("publications", publications);
        } catch (DBException e) {
            e.printStackTrace();
        }
        processRequest(request, response);
    }

    @Override
    protected void doPost(
            HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        processRequest(request, response);
    }
}
