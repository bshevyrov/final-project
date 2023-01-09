package ua.com.company.view.controller.admin;

import com.mysql.cj.util.StringUtils;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.com.company.facade.ImageFacade;
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

    private ImageFacade imageFacade;
    private final Logger log = LogManager.getLogger(AdminPublicationUploadController.class);


    @Override
    public void init() throws ServletException {
        topicFacade = (TopicFacade) getServletContext().getAttribute("topicFacade");
        publicationFacade = (PublicationFacade) getServletContext().getAttribute("publicationFacade");
        imageFacade = (ImageFacade) getServletContext().getAttribute("imageFacade");
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
        if (!StringUtils.isNullOrEmpty(request.getParameter("id"))) {
            int id = Integer.parseInt(request.getParameter("id"));
            //TODO NOT NUMBER
            request.setAttribute("publication", ((PublicationFacade) getServletContext().getAttribute("publicationFacade")).findById(id));
        }
        request.setAttribute("topics", topicFacade.findAll());
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PublicationDTO newPublicationDTO = new PublicationDTO();
        boolean updatable = !StringUtils.isEmptyOrWhitespaceOnly(request.getParameter("id"));
        String coverPath = request.getParameter("coverPath");
        int id = Integer.parseInt(request.getParameter("id"));

        if (!updatable) {
            PublicationDTO currentPublicationDTO = publicationFacade.findById(id);
            ImageDTO cover;
            if (!coverPath.equals(currentPublicationDTO.getCover().getPath())) {
                imageFacade.create(new ImageDTO(request.getParameter("title") + " cover",coverPath));
                cover = imageFacade.findByPath(coverPath);
            }else {
                cover= currentPublicationDTO.getCover();
            }
            newPublicationDTO.setCover(cover);

        }

        List<TopicDTO> topicList = new ArrayList<>();
        if (request.getParameterValues("topics") != null) {
            topicList = Arrays.stream(request.getParameterValues("topics"))
                    .map(Integer::parseInt)
                    .map(topicFacade::findById)
                    .collect(Collectors.toList());
        }

        if (!StringUtils.isNullOrEmpty(request.getParameter("newTopics"))) {
            for (String s : request.getParameter("newTopics").split(",")) {
                TopicDTO currentTopic = new TopicDTO(s.trim());
                topicFacade.create(currentTopic);
                topicList.add(topicFacade.findByTitle(currentTopic.getTitle()));
            }
        }

        newPublicationDTO.setTopics(topicList);
        newPublicationDTO.setPrice(Double.parseDouble(request.getParameter("price")));
        newPublicationDTO.setTitle(request.getParameter("title"));
        newPublicationDTO.setDescription(request.getParameter("description"));
        //todo search all not null or empty
        if (updatable) {
            newPublicationDTO.setId(id);
            publicationFacade.update(newPublicationDTO);
        } else {
            publicationFacade.create(newPublicationDTO);
        }
        response.sendRedirect("/admin/newPublicationDTO/dashboard");
    }
}
