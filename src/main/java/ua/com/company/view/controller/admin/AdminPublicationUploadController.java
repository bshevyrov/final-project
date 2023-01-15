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
import java.util.*;
import java.util.stream.Collectors;

public class AdminPublicationUploadController extends HttpServlet {
    private TopicFacade topicFacade;
    private PublicationFacade publicationFacade;
    private Set<String> errorFields;

    private ImageFacade imageFacade;
    private final Logger log = LogManager.getLogger(AdminPublicationUploadController.class);


    @Override
    public void init() throws ServletException {
        topicFacade = (TopicFacade) getServletContext().getAttribute("topicFacade");
        publicationFacade = (PublicationFacade) getServletContext().getAttribute("publicationFacade");
        imageFacade = (ImageFacade) getServletContext().getAttribute("imageFacade");
        errorFields = new HashSet<>();
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
            request.setAttribute("publication", ((PublicationFacade) getServletContext().getAttribute("publicationFacade")).findById(id));
        }
        request.setAttribute("topics", topicFacade.findAll());
        request.setAttribute("errorFields", errorFields);
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PublicationDTO newPublicationDTO = new PublicationDTO();
        ImageDTO cover = new ImageDTO();
        errorFields.clear();
        boolean newPublication = !StringUtils.isStrictlyNumeric(request.getParameter("id"));
        String coverPath = request.getParameter("coverPath");
        String title = request.getParameter("title");
        String description = request.getParameter("description");
        double price = Double.parseDouble(request.getParameter("price"));

        cover.setPath(coverPath);
        cover.setName(title + " cover");
        newPublicationDTO.setCover(cover);
        newPublicationDTO.setTopics(getTopicDTOS(request));
        newPublicationDTO.setPrice(price);
        newPublicationDTO.setTitle(title);
        newPublicationDTO.setDescription(description);

        if (!isValid(newPublicationDTO)) {
            if (!newPublication) {
                newPublicationDTO.setId(Integer.parseInt(request.getParameter("id")));
            }
            request.setAttribute("publication", newPublicationDTO);
            request.setAttribute("topics", topicFacade.findAll());
            request.setAttribute("errorFields", errorFields);
            processRequest(request, response);
            return;
        }


        if (newPublication) {
            publicationFacade.create(newPublicationDTO);
        } else {
            newPublicationDTO.setId(Integer.parseInt(request.getParameter("id")));
            publicationFacade.update(newPublicationDTO);
        }
        response.sendRedirect("/admin/publication/dashboard");
    }

    private List<TopicDTO> getTopicDTOS(HttpServletRequest request) {
        List<TopicDTO> topicList = new LinkedList<>();
        if (request.getParameterValues("topics") != null) {
            topicList = Arrays.stream(request.getParameterValues("topics"))
                    .map(Integer::parseInt)
                    .map(topicFacade::findById)
                    .collect(Collectors.toList());
        }

        if (!StringUtils.isNullOrEmpty(request.getParameter("newTopics"))) {
            for (String s : request.getParameter("newTopics").split(",")) {
                String topicName = s.trim();
                topicList.add(new TopicDTO(topicName));
            }
        }
        return topicList;
    }

    private boolean isValid(PublicationDTO publicationDTO) {

        publicationDTO.getTopics().forEach(topicDTO -> stringValidation("newTopics", topicDTO.getTitle()));
        numberValidation("price", publicationDTO.getPrice());
        stringValidation("title", publicationDTO.getTitle());
        stringValidation("description", publicationDTO.getDescription());
        stringValidation("coverPath", publicationDTO.getCover().getPath());
        return errorFields.isEmpty();
    }

    private void stringValidation(String fieldName, String value) {
        if (StringUtils.isNullOrEmpty(value)
                || StringUtils.isEmptyOrWhitespaceOnly(value)
                || StringUtils.isStrictlyNumeric(value)) {
            errorFields.add(fieldName);
        }
    }

    private void numberValidation(String fieldName, double value) {
        if (value <= 0) {
            errorFields.add(fieldName);
        }
    }
}
