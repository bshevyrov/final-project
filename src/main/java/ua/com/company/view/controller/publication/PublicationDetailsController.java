package ua.com.company.view.controller.publication;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ua.com.company.entity.Sorting;
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
        int publicationId = Integer.parseInt(request.getParameter("id"));
        PublicationDTO publication;
        publication = publicationFacade.findById(publicationId);

        request.setAttribute("publication", publication);
        Sorting sorting = new Sorting();
        sorting.setSortingField("update_date");
        sorting.setPageSize(6);
        sorting.setSortingType("ASC");
//        if(request.getParameter("startRecord")== null){
            sorting.setStarRecord(0);
//        } else{
//            sorting.setStarRecord(Integer.parseInt(request.getParameter("startRecord")));
//        }
        //coment section
        List<PublicationCommentDTO> commentList = publicationFacade.findAllCommentsByPublicationId(sorting, publicationId);
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

        int publicationId = Integer.parseInt(request.getParameter("pubId"));
        if(request.getParameter("comment")!=null){
            int personId = ((PersonDTO) request.getSession(false).getAttribute("loggedPerson")).getId();
            String comment = request.getParameter("comment");
            publicationFacade.createComment(publicationId, personId, comment);
            response.sendRedirect("/publication/details?id="+publicationId);
            return;
//            System.out.println("NOT redirected");
        }
        Sorting sorting = new Sorting();
        sorting.setSortingField("update_date");
        sorting.setPageSize(6);
        sorting.setSortingType("ASC");
//        if(request.getParameter("startRecord")== null){
//        sorting.setStarRecord(0);
//        } else{
            sorting.setStarRecord(Integer.parseInt(request.getParameter("startRecord")));
//        }

        PublicationDTO publication;
        publication = publicationFacade.findById(publicationId);
        request.setAttribute("publication", publication);

        List<PublicationCommentDTO> commentList = publicationFacade.findAllCommentsByPublicationId(sorting, publicationId);
        request.setAttribute("comments", commentList);
        processRequest(request, response);
    }
}
