package ua.com.company.controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ua.com.company.dao.PublicationDAO;
import ua.com.company.dao.TopicDAO;
import ua.com.company.dao.mysql.impl.MysqlPublicationDAOImpl;
import ua.com.company.dao.mysql.impl.MysqlTopicDAOImpl;
import ua.com.company.entity.Publication;
import ua.com.company.exception.DBException;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "CategoriesServlet", value = "/categories")
public class CategoriesController extends HttpServlet {
    private void processRequest(HttpServletRequest request, HttpServletResponse response) {
//        String studentID = request.getParameter("id");
//        if (studentID != null) {
//            int id = Integer.parseInt(studentID);
//            studentService.getStudent(id)
//                    .ifPresent(s -> request.setAttribute("studentRecord", s));
        RequestDispatcher dispatcher = request.getRequestDispatcher(
                "/WEB-INF/jsp/categories.jsp");
        try {
            dispatcher.forward(request, response);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int topicId = Integer.parseInt(request.getParameter("id"));
        TopicDAO topicDAO = new MysqlTopicDAOImpl();
        String topicName ="";
        try {
           topicName = topicDAO.findById(topicId).get().getTitle();
        } catch (DBException e) {
            e.printStackTrace();
        }
        PublicationDAO publicationDAO = new MysqlPublicationDAOImpl();
        List<Publication> publications = null;
        try {
            publications = publicationDAO.findAllByTopicId(topicId);
        } catch (DBException e) {
            e.printStackTrace();
        }
        request.setAttribute("topicName",topicName);
        request.setAttribute("publications", publications);
        processRequest(request, response);


}

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);

    }
}
