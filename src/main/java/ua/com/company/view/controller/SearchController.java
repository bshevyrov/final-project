package ua.com.company.view.controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.com.company.entity.Sorting;
import ua.com.company.facade.PublicationFacade;

import java.io.IOException;

public class SearchController extends HttpServlet {
    private final Logger log = LogManager.getLogger(SearchController.class);
    private PublicationFacade publicationFacade;

    @Override
    public void init() throws ServletException {
        publicationFacade = (PublicationFacade) getServletContext()
                .getAttribute("publicationFacade");
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response) {

        RequestDispatcher dispatcher = request.getRequestDispatcher(
                "WEB-INF/jsp/category/category-details.jsp");
        try {
            dispatcher.forward(request, response);
        } catch (ServletException | IOException e) {
            log.error("SearchController error", e);
        }
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws
            ServletException, IOException {

        String searchReq = "";
        if (request.getParameter("search") != null) {

            searchReq = request.getParameter("search");
        } else if (request.getParameter("currentSearch") != null) {
            searchReq = request.getParameter("currentSearch");
        }


        Sorting sorting = new Sorting();
        String sort;
        if (request.getParameter("sort") != null) {
            sort = request.getParameter("sort");
        } else if (request.getParameter("currentSort") != null) {
            sort = request.getParameter("currentSort");

        } else {
            sort = "titleAsc";
        }
        sorting.setSortingField(sort.substring(0, 5));
        sorting.setSortingType(sort.substring(5).toUpperCase());
        String pageSize;
        if (request.getParameter("pageSize") != null) {
            pageSize = request.getParameter("pageSize");
        } else if (request.getParameter("currentSize") != null) {
            pageSize = request.getParameter("currentSize");

        } else {
            pageSize = "6";
        }
        sorting.setPageSize(Integer.parseInt(pageSize));
        String currentPage;
        if (request.getParameter("currentPage") == null) {
            currentPage = "1";
        } else {
            currentPage = request.getParameter("currentPage");
        }
        sorting.setStarRecord(Integer.parseInt(currentPage) == 1 ? 0 : (Integer.parseInt(currentPage) - 1) * Integer.parseInt(pageSize));


        final String url = "/search";
        int countAllByTitle = publicationFacade.countAllByTitle(searchReq);
        int lastPage = countAllByTitle % Integer.parseInt(pageSize) == 0 ? countAllByTitle / Integer.parseInt(pageSize) : countAllByTitle / Integer.parseInt(pageSize) + 1;
        request.setAttribute("lastPage", lastPage);
        request.setAttribute("currentPage", currentPage);
        request.setAttribute("currentSort", sort);
        request.setAttribute("currentSize", pageSize);
        request.setAttribute("url", url);
        request.setAttribute("topicName", searchReq);
        request.setAttribute("publications", publicationFacade.findAllByTitle(sorting, searchReq));
        request.setAttribute("currentSearch", searchReq);
        processRequest(request, response);
    }
}
//todo make code smaller