package ua.com.company.view.controller.admin;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ua.com.company.entity.Image;
import ua.com.company.entity.Publication;
import ua.com.company.entity.Topic;
import ua.com.company.service.PublicationService;
import ua.com.company.service.TopicService;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class AdminPublicationUploadController extends HttpServlet {
    private void processRequest(HttpServletRequest request, HttpServletResponse response) {

        RequestDispatcher dispatcher = request.getRequestDispatcher(
                "/WEB-INF/jsp/admin/admin-publication-upload.jsp");
        try {
            dispatcher.forward(request, response);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        TopicService topicService = (TopicService) getServletContext().getAttribute("topicService");
        request.setAttribute("topics", topicService.findAll());
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Publication publication = new Publication();
        Image image = new Image();
        Topic topic = new Topic();
        TopicService topicService = (TopicService) getServletContext().getAttribute("topicService");
        PublicationService publicationService = (PublicationService) getServletContext().getAttribute("publicationService");

        //VALIDATION
        image.setPath(request.getParameter("coverPath"));
        image.setName(request.getParameter("title") + " cover");
        publication.setImages((List<Image>) image);

        String[] topics = request.getParameterValues("topics");
        List<Topic> topicList = Arrays.stream(topics).map(Topic::new).collect(Collectors.toList());
        publication.setTopics(topicList);
        if (request.getParameter("newTopics") != null) {
            String[] newTopic = request.getParameter("newTopics").split(",");
            for (String s : newTopic) {
                Topic currentTopic = new Topic(s);
                topicList.add(currentTopic);
                topicService.create(currentTopic);
            }
        }


        publication.setPrice(Double.parseDouble(request.getParameter("price")));
        publication.setTitle(request.getParameter("title"));
        publication.setDescription(request.getParameter("description"));
        publicationService.create(publication);
        processRequest(request, response);

    }
}
