package ua.com.company.view.controller.publication;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.com.company.entity.Sorting;
import ua.com.company.facade.PublicationCommentFacade;
import ua.com.company.facade.PublicationFacade;
import ua.com.company.view.dto.PersonDTO;
import ua.com.company.view.dto.PublicationCommentDTO;
import ua.com.company.view.dto.PublicationDTO;

import java.io.IOException;
import java.util.List;


public class PublicationDetailsController extends HttpServlet {
    private final Logger log = LogManager.getLogger(PublicationDetailsController.class);
    private PublicationFacade publicationFacade;
    private PublicationCommentFacade publicationCommentFacade;

    @Override
    public void init() throws ServletException {
        publicationFacade = (PublicationFacade) getServletContext()
                .getAttribute("publicationFacade");
        publicationCommentFacade = (PublicationCommentFacade) getServletContext()
                .getAttribute("publicationCommentFacade");
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response) {
        RequestDispatcher dispatcher = request.getRequestDispatcher(
                "/WEB-INF/jsp/publication/publication-details.jsp");
        try {
            dispatcher.forward(request, response);
        } catch (ServletException | IOException e) {
            log.error("PublicationDetailsController error", e);
        }
    }

    @Override
    protected void doGet(
            HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int publicationId = Integer.parseInt(request.getParameter("id"));
        request.setAttribute("publication", publicationFacade.findById(publicationId));
        Sorting sorting = new Sorting();
        sorting.setSortingField("create_date");
        int pageSize = 6;
        int currentPage = 1;

        sorting.setPageSize(pageSize);
        sorting.setStarRecord(0);
        List<PublicationCommentDTO> commentList = publicationCommentFacade.findAllByPublicationId(sorting, publicationId);

        final String url = "/publication/details?id=" + publicationId;
        int countAllByPublicationId = publicationCommentFacade.countAllByPublicationId(publicationId);
        int lastPage = countAllByPublicationId % pageSize == 0 ? countAllByPublicationId / pageSize : countAllByPublicationId / pageSize + 1;
        request.setAttribute("url", url);
        request.setAttribute("lastPage", lastPage);
        request.setAttribute("currentPage", currentPage);
        request.setAttribute("comments", commentList);

        processRequest(request, response);
    }

    @Override
    protected void doPost(
            HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int publicationId = Integer.parseInt(request.getParameter("pubId"));
        if (request.getParameter("comment") != null) {
            PersonDTO personDTO = ((PersonDTO) request.getSession(false).getAttribute("loggedPerson"));
            String comment = request.getParameter("comment");

            PublicationCommentDTO publicationCommentDTO = new PublicationCommentDTO();
            publicationCommentDTO.setPublicationId(publicationId);
        //    publicationCommentDTO.setAvatarPath(personDTO.getAvatar().getPath());
            publicationCommentDTO.setPersonId(personDTO.getId());
            publicationCommentDTO.setText(comment);
            publicationCommentFacade.create(publicationCommentDTO);
            response.sendRedirect("/publication/details?id=" + publicationId);
            return;
        }
        Sorting sorting = new Sorting();
        sorting.setSortingField("create_date");
        int pageSize = 6;
        sorting.setPageSize(pageSize);

        int currentPage = Integer.parseInt(request.getParameter("currentPage"));
        sorting.setStarRecord(currentPage == 1 ? 0 : (currentPage - 1) * pageSize);
        request.setAttribute("currentPage", currentPage);
        final String url = "/publication/details?id=" + publicationId;

        request.setAttribute("url", url);
        int countAllByPublicationId = publicationCommentFacade.countAllByPublicationId(publicationId);
        int lastPage = countAllByPublicationId % pageSize == 0 ? countAllByPublicationId / pageSize : countAllByPublicationId / pageSize + 1;
        request.setAttribute("lastPage", lastPage);
        request.setAttribute("publication", publicationFacade.findById(publicationId));
        request.setAttribute("comments", publicationCommentFacade.findAllByPublicationId(sorting, publicationId));
        processRequest(request, response);
    }
}
