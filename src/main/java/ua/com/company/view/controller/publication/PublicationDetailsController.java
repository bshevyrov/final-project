package ua.com.company.view.controller.publication;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ua.com.company.entity.Sorting;
import ua.com.company.facade.PublicationCommentFacade;
import ua.com.company.facade.PublicationFacade;
import ua.com.company.view.dto.PersonDTO;
import ua.com.company.view.dto.PublicationCommentDTO;
import ua.com.company.view.dto.PublicationDTO;

import java.io.IOException;
import java.util.List;


///details?title=

public class PublicationDetailsController extends HttpServlet {

    private void processRequest(HttpServletRequest request, HttpServletResponse response) {

        RequestDispatcher dispatcher = request.getRequestDispatcher(
                "/WEB-INF/jsp/publication/publication-details.jsp");
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
        PublicationFacade publicationFacade = (PublicationFacade) getServletContext()
                .getAttribute("publicationFacade");
                PublicationCommentFacade publicationCommentFacade = (PublicationCommentFacade) getServletContext()
                .getAttribute("publicationCommentFacade");
        int publicationId = Integer.parseInt(request.getParameter("id"));
        PublicationDTO publication;
        publication = publicationFacade.findById(publicationId);

        request.setAttribute("publication", publication);
        Sorting sorting = new Sorting();
        sorting.setSortingField("create_date");
        int pageSize = 6;
        int currentPage =1;

        sorting.setPageSize(pageSize);
        sorting.setSortingType("ASC");
//        if(request.getParameter("startRecord")== null){
            sorting.setStarRecord(0);
//        } else{
//            sorting.setStarRecord(Integer.parseInt(request.getParameter("startRecord")));
//        }
        //coment section
        List<PublicationCommentDTO> commentList = publicationCommentFacade.findAllByPublicationId(sorting, publicationId);

        final String url = "/publication/details?id="+ publicationId;
        int countAllByPublicationId = publicationCommentFacade.countAllByPublicationId(publicationId);
        int lastPage = countAllByPublicationId % pageSize == 0 ? countAllByPublicationId / pageSize : countAllByPublicationId / pageSize + 1;
        request.setAttribute("url", url);
        request.setAttribute("lastPage", lastPage);
        request.setAttribute("currentPage", currentPage);





        request.setAttribute("comments", commentList);
        //
        processRequest(request, response);
    }

    @Override
    protected void doPost(
            HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        PublicationFacade publicationFacade = (PublicationFacade) getServletContext()
                .getAttribute("publicationFacade");
        PublicationCommentFacade publicationCommentFacade = (PublicationCommentFacade) getServletContext()
                .getAttribute("publicationCommentFacade");

        int publicationId = Integer.parseInt(request.getParameter("pubId"));
        if(request.getParameter("comment")!=null){
            PersonDTO personDTO = ((PersonDTO) request.getSession(false).getAttribute("loggedPerson"));
            String comment = request.getParameter("comment");
            PublicationCommentDTO publicationCommentDTO = new PublicationCommentDTO();
            publicationCommentDTO.setPublicationId(publicationId);
            publicationCommentDTO.setAvatarPath(personDTO.getAvatar().getPath());
            publicationCommentDTO.setPersonId(personDTO.getId());
            publicationCommentDTO.setText(comment);
            publicationCommentFacade.create(publicationCommentDTO);
            response.sendRedirect("/publication/details?id="+publicationId);
            return;
//            System.out.println("NOT redirected");
        }
        Sorting sorting = new Sorting();
        sorting.setSortingField("create_date");
        int pageSize =6;
        sorting.setPageSize(6);
        sorting.setSortingType("ASC");
//        if(request.getParameter("startRecord")== null){
//        sorting.setStarRecord(0);
//        } else{
//            sorting.setStarRecord(Integer.parseInt(request.getParameter("startRecord")));
        int currentPage = Integer.parseInt(request.getParameter("currentPage"));
        sorting.setStarRecord(currentPage == 1 ? 0 : (currentPage - 1) * pageSize);
        request.setAttribute("currentPage", currentPage);
        final String url = "/publication/details?id="+ publicationId;

        request.setAttribute("url", url);
        int countAllByPublicationId = publicationCommentFacade.countAllByPublicationId(publicationId);
        int lastPage = countAllByPublicationId % pageSize == 0 ? countAllByPublicationId / pageSize : countAllByPublicationId / pageSize + 1;
        request.setAttribute("lastPage", lastPage);
//        }

        PublicationDTO publication;
        publication = publicationFacade.findById(publicationId);
        request.setAttribute("publication", publication);

        List<PublicationCommentDTO> commentList = publicationCommentFacade.findAllByPublicationId(sorting, publicationId);
        request.setAttribute("comments", commentList);
        processRequest(request, response);
    }
}
