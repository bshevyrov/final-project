package ua.com.company.view.controller.category;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ua.com.company.entity.Sorting;
import ua.com.company.facade.PublicationFacade;
import ua.com.company.facade.TopicFacade;
import ua.com.company.view.dto.PublicationDTO;

import java.io.IOException;
import java.util.List;

public class CategoryDetailsController extends HttpServlet {
    private void processRequest(HttpServletRequest request, HttpServletResponse response) {

        RequestDispatcher dispatcher = request.getRequestDispatcher(
                "WEB-INF/jsp/category/category-details.jsp");
        try {
            dispatcher.forward(request, response);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        int topicId = Integer.parseInt(request.getParameter("id"));

        TopicFacade topicFacade = (TopicFacade) getServletContext()
                .getAttribute("topicFacade");
        PublicationFacade publicationFacade = (PublicationFacade) getServletContext()
                .getAttribute("publicationFacade");
        String topicName = topicFacade.findById(topicId).getTitle();

        List<PublicationDTO> publications = publicationFacade.findAllByTopicId(new Sorting(),topicId);

        int totalRec=publications.size();
        int currentPage=1;
        int pageSize = 12;
        System.out.println(getServletInfo());
        System.out.println(getServletContext().getContextPath());
        final  String url="/category?id="+topicId;




        request.setAttribute("url", url);
        request.setAttribute("topicName", topicName);
        request.setAttribute("publications", publications);
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }
}
