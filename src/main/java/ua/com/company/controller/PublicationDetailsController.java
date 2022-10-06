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


///details?title=

@WebServlet(name = "PublicationDetailsServlet", value = "/details")
public class PublicationDetailsController extends HttpServlet {

    private void processRequest(HttpServletRequest request, HttpServletResponse response) {
//        String studentID = request.getParameter("id");
//        if (studentID != null) {
//            int id = Integer.parseInt(studentID);
//            studentService.getStudent(id)
//                    .ifPresent(s -> request.setAttribute("studentRecord", s));
        RequestDispatcher dispatcher = request.getRequestDispatcher(
                "/WEB-INF/jsp/publication-details.jsp");
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

//        String mangaName = request.getParameter("title");
//        Publication publication =  publicationDAO.findByTitle(mangaName);
        int publicationId = Integer.parseInt(request.getParameter("id"));
        Publication publication = null;
        try {
            publication = publicationDAO.findById(publicationId).get();

        } catch (DBException e) {
            e.printStackTrace();
        }
        request.setAttribute("publication", publication);
        processRequest(request, response);
    }

    @Override
    protected void doPost(
            HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        processRequest(request, response);
    }
}
