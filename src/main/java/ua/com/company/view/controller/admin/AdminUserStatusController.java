//package ua.com.company.controller.admin;
//
//import jakarta.servlet.RequestDispatcher;
//import jakarta.servlet.ServletException;
//import jakarta.servlet.http.HttpServlet;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import ua.com.company.entity.Person;
//import ua.com.company.service.PersonService;
//
//import java.io.IOException;
//import java.util.List;
//
//public class AdminUserStatusController extends HttpServlet {
//    private void processRequest(HttpServletRequest request, HttpServletResponse response) {
//
//        RequestDispatcher dispatcher = request.getRequestDispatcher(
//                "/WEB-INF/jsp/admin/admin-user-dashboard.jsp");
//        try {
//            dispatcher.forward(request, response);
//        } catch (ServletException | IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//    @Override
//    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        String id = request.getParameter("id");
//        PersonService personService = (PersonService) getServletContext().getAttribute("personService");
//        personService.changeStatusById(Integer.parseInt(id)));
//       List<Person> personList =  personService.findAll();
//        request.setAttribute("personList", personList);
//        processRequest(request, response);
//    }
//
//    @Override
//    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//
//    }
//}