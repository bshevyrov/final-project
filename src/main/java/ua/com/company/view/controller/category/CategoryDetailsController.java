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


        int currentPage = 1;
        String pageSize = "6";
        Sorting sort = new Sorting();
        sort.setPageSize(Integer.parseInt(pageSize));
        List<PublicationDTO> publications = publicationFacade.findAllByTopicId(sort, topicId);
        final String url = "/category?id=" + topicId;
        int lastPage = publicationFacade.countAllByTopicId(topicId) % Integer.parseInt(pageSize) == 0 ? publicationFacade.countAllByTopicId(topicId) / Integer.parseInt(pageSize) : publicationFacade.countAllByTopicId(topicId) / Integer.parseInt(pageSize) + 1;

        request.setAttribute("lastPage", lastPage);
        request.setAttribute("currentSort", "titleAsc");
        request.setAttribute("currentSize", pageSize);
        request.setAttribute("currentPage", currentPage);
        request.setAttribute("url", url);
        request.setAttribute("topicName", topicName);
        request.setAttribute("publications", publications);


        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws
            ServletException, IOException {
        int topicId = Integer.parseInt(request.getParameter("id"));

        TopicFacade topicFacade = (TopicFacade) getServletContext()
                .getAttribute("topicFacade");
        PublicationFacade publicationFacade = (PublicationFacade) getServletContext()
                .getAttribute("publicationFacade");
        String topicName = topicFacade.findById(topicId).getTitle();

        Sorting sorting = new Sorting();
        String sort;
        if (request.getParameter("sort") != null) {
            sort = request.getParameter("sort");
        } else {
            sort = request.getParameter("currentSort");
        }
        switch (sort) {
            case "priceDesc": {
                sorting.setSortingType("DESC");
                sorting.setSortingField("price");
                break;
            }
            case "priceAsc": {
                sorting.setSortingType("ASC");
                sorting.setSortingField("price");
                break;
            }
            case "titleAsc": {
                sorting.setSortingType("ASC");
                sorting.setSortingField("p.title");
                break;
            }
            case "titleDesc": {
                sorting.setSortingType("DESC");
                sorting.setSortingField("p.title");
                break;
            }
        }
        String pageSize;
        if (request.getParameter("pageSize") == null) {
            pageSize = request.getParameter("currentSize");
        } else {
            pageSize = request.getParameter("pageSize");
        }
        switch (pageSize) {
            case "6":
                sorting.setPageSize(6);
                break;
            case "12":
                sorting.setPageSize(12);
                break;
            case "24":
                sorting.setPageSize(24);
                break;
        }

        String currentPage;
        if (request.getParameter("currentPage") == null) {
            currentPage = "1";
        } else {
            currentPage = request.getParameter("currentPage");
        }
        sorting.setStarRecord(Integer.parseInt(currentPage) == 1 ? 0 : (Integer.parseInt(currentPage) - 1) * Integer.parseInt(pageSize));


        List<PublicationDTO> publications = publicationFacade.findAllByTopicId(sorting, topicId);
        final String url = "/category?id=" + topicId;
        int lastPage = publicationFacade.countAllByTopicId(topicId) % Integer.parseInt(pageSize) == 0 ? publicationFacade.countAllByTopicId(topicId) / Integer.parseInt(pageSize) : publicationFacade.countAllByTopicId(topicId) / Integer.parseInt(pageSize) + 1;
        request.setAttribute("lastPage", lastPage);
        request.setAttribute("currentPage", currentPage);
        request.setAttribute("currentSort", sort);
        request.setAttribute("currentSize", pageSize);
        request.setAttribute("url", url);
        request.setAttribute("topicName", topicName);
        request.setAttribute("publications", publications);
        processRequest(request, response);
    }
}
