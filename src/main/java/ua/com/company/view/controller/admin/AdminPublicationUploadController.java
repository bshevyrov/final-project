package ua.com.company.view.controller.admin;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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
    private TopicFacade topicFacade;
    private PublicationFacade publicationFacade;
    private final Logger log = LogManager.getLogger(AdminPublicationUploadController.class);


    @Override
    public void init(ServletConfig config) throws ServletException {
        topicFacade = (TopicFacade) getServletContext().getAttribute("topicFacade");
        publicationFacade = (PublicationFacade) getServletContext().getAttribute("publicationFacade");

    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response) {
        RequestDispatcher dispatcher = request.getRequestDispatcher(
                "/WEB-INF/jsp/admin/admin-publication-upload.jsp");
        try {
            dispatcher.forward(request, response);
        } catch (ServletException | IOException e) {
            log.error("AdminPublicationUploadController error", e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getParameter("id") != null) {
            int id = Integer.parseInt(request.getParameter("id"));
            //TODO NOT NUMBER
            request.setAttribute("publication", ((PublicationFacade) getServletContext().getAttribute("publicationFacade")).findById(id));
        }
        request.setAttribute("topics", topicFacade.findAll());
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PublicationDTO publication = new PublicationDTO();
        ImageDTO image = new ImageDTO();

        // todo VALIDATION
        image.setPath(request.getParameter("coverPath"));
        image.setName(request.getParameter("title") + " cover");
        publication.setCover(image);
        List<TopicDTO> topicList = new ArrayList<>();

        if (request.getParameterValues("topics") != null) {
            topicList = Arrays.stream(request.getParameterValues("topics"))
                    .map(Integer::parseInt)
                    .map(topicFacade::findById)
                    .collect(Collectors.toList());

        }
        if (request.getParameter("newTopics") != null && !request.getParameter("newTopics").equals("")) {
            for (String s : request.getParameter("newTopics").split(",")) {
                TopicDTO currentTopic = new TopicDTO(s.trim());
                topicFacade.create(currentTopic);
                topicList.add(topicFacade.findByTitle(currentTopic.getTitle()));
            }
        }
        publication.setTopics(topicList);
        publication.setPrice(Double.parseDouble(request.getParameter("price")));
        publication.setTitle(request.getParameter("title"));
        publication.setDescription(request.getParameter("description"));

        if (request.getParameter("id") == null || request.getParameter("id").equals("")) {
            publicationFacade.create(publication);
        } else {
            publication.setId(Integer.parseInt(request.getParameter("id")));
            publicationFacade.update(publication);
        }
        response.sendRedirect("/admin/publication/dashboard");
    }
}
