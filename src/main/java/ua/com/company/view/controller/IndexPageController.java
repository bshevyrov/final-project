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
import ua.com.company.view.dto.PublicationDTO;

import java.io.IOException;
import java.util.List;

public class IndexPageController extends HttpServlet {
    private final Logger log = LogManager.getLogger(IndexPageController.class);
    private PublicationFacade publicationFacade;

    @Override
    public void init() throws ServletException {
        publicationFacade = (PublicationFacade) getServletContext()
                .getAttribute("publicationFacade");
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response) {
        RequestDispatcher dispatcher = request.getRequestDispatcher(
                "/WEB-INF/jsp/index.jsp");
        try {
            dispatcher.forward(request, response);
        } catch (ServletException | IOException e) {
            log.error("IndexPageController error", e);
        }
    }

    @Override
    protected void doGet(
            HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int currentPage = 1;
        String pageSize = "6";
        Sorting sort = new Sorting();
        sort.setPageSize(Integer.parseInt(pageSize));
        List<PublicationDTO> publications = publicationFacade.findAllSorted(sort);
        final String url = "/";
        int countAll = publicationFacade.countAll();
        int lastPage = countAll % Integer.parseInt(pageSize) == 0 ? countAll / Integer.parseInt(pageSize) : countAll / Integer.parseInt(pageSize) + 1;

        request.setAttribute("lastPage", lastPage);
        request.setAttribute("currentSort", "titleAsc");
        request.setAttribute("currentSize", pageSize);
        request.setAttribute("currentPage", currentPage);
        request.setAttribute("url", url);
        request.setAttribute("topicName", "NEW");
        request.setAttribute("publications", publications);
        processRequest(request, response);

    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws
            ServletException, IOException {
        Sorting sorting = new Sorting();
        String sort;
        if (request.getParameter("sort") != null) {
            sort = request.getParameter("sort");
        } else {
            sort = request.getParameter("currentSort");
        }
        sorting.setSortingField(sort.substring(0, 5));
        sorting.setSortingType(sort.substring(5).toUpperCase());
        String pageSize;
        if (request.getParameter("pageSize") == null) {
            pageSize = request.getParameter("currentSize");
        } else {
            pageSize = request.getParameter("pageSize");
        }
        sorting.setPageSize(Integer.parseInt(pageSize));
        String currentPage = request.getParameter("currentPage") == null ? "1" : request.getParameter("currentPage");
        sorting.setStarRecord(Integer.parseInt(currentPage) == 1 ? 0 : (Integer.parseInt(currentPage) - 1) * Integer.parseInt(pageSize));
        List<PublicationDTO> publications = publicationFacade.findAllSorted(sorting);
        final String url = "/" ;
        int countAll = publicationFacade.countAll();
        int lastPage = countAll % Integer.parseInt(pageSize) == 0 ? countAll / Integer.parseInt(pageSize) : countAll / Integer.parseInt(pageSize) + 1;
        request.setAttribute("lastPage", lastPage);
        request.setAttribute("currentPage", currentPage);
        request.setAttribute("currentSort", sort);
        request.setAttribute("currentSize", pageSize);
        request.setAttribute("url", url);
        request.setAttribute("topicName", "NEW");
        request.setAttribute("publications", publications);
        processRequest(request, response);
    }

}
