package ua.com.company.view.controller.category;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ua.com.company.entity.Publication;
import ua.com.company.service.PublicationService;
import ua.com.company.service.TopicService;

import java.io.IOException;
import java.util.List;

public class CategoryDetailsController extends HttpServlet {
    private void processRequest(HttpServletRequest request, HttpServletResponse response) {

        RequestDispatcher dispatcher = request.getRequestDispatcher(
                "/WEB-INF/jsp/category/category-details.jsp");
        try {
            dispatcher.forward(request, response);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        int topicId = Integer.parseInt(request.getParameter("id"));

        TopicService topicService = (TopicService) getServletContext()
                .getAttribute("topicService");
        PublicationService publicationService = (PublicationService) getServletContext()
                .getAttribute("publicationService");
        String topicName = topicService.findById(topicId).getTitle();

        List<Publication> publications = publicationService.findAllByTopicId(topicId);

        request.setAttribute("topicName", topicName);
        request.setAttribute("publications", publications);
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }
}
