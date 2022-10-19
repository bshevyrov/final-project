package ua.com.company.view.controller.admin;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ua.com.company.facade.PublicationFacade;
import ua.com.company.facade.TopicFacade;
import ua.com.company.view.dto.ImageDTO;
import ua.com.company.view.dto.PublicationDTO;
import ua.com.company.view.dto.TopicDTO;

import java.io.IOException;
import java.util.ArrayList;
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
        if (request.getParameter("id") != null) {
            int id = Integer.parseInt(request.getParameter("id"));
            request.setAttribute("publication", ((PublicationFacade) getServletContext().getAttribute("publicationFacade")).findById(id));
        }
        TopicFacade topicFacade = (TopicFacade) getServletContext().getAttribute("topicFacade");
        request.setAttribute("topics", topicFacade.findAll());
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PublicationDTO publication = new PublicationDTO();
        ImageDTO image = new ImageDTO();
        TopicFacade topicFacade = (TopicFacade) getServletContext().getAttribute("topicFacade");
        PublicationFacade publicationFacade = (PublicationFacade) getServletContext().getAttribute("publicationFacade");

        //VALIDATION
        image.setPath(request.getParameter("coverPath"));
        image.setName(request.getParameter("title") + " cover");
        publication.setCover(image);
        List<TopicDTO> topicList = new ArrayList<>();
        if (request.getParameterValues("topics") != null) {
            String[] topics = request.getParameterValues("topics");

            topicList = Arrays.stream(topics).map(Integer::parseInt)
                    .map(topicFacade::findById).collect(Collectors.toList());
        }
        if (request.getParameter("newTopics") != null && !request.getParameter("newTopics").equals("")) {
            String[] newTopic = request.getParameter("newTopics").split(",");

            for (String s : newTopic) {
                TopicDTO currentTopic = new TopicDTO(s);
                currentTopic.setId(topicFacade.create(currentTopic));
                topicList.add(currentTopic);
            }
        }
        publication.setTopics(topicList);
        publication.setPrice(Double.parseDouble(request.getParameter("price")));
        publication.setTitle(request.getParameter("title"));
        publication.setDescription(request.getParameter("description"));
        if(request.getParameter("id")==null){
            publicationFacade.create(publication);
            System.out.println("NEW");
        } else {
            publication.setId(Integer.parseInt(request.getParameter("id")));
            publicationFacade.update(publication);
            for (TopicDTO topic : publication.getTopics()) {
            }
        }

        response.sendRedirect("/admin/publication/dashboard");

    }
}
