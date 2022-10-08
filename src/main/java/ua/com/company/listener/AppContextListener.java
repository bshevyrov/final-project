package ua.com.company.listener;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebListener;
import ua.com.company.dao.DAOFactory;
import ua.com.company.dao.PersonDAO;
import ua.com.company.dao.PublicationDAO;
import ua.com.company.service.PersonService;
import ua.com.company.service.PublicationService;
import ua.com.company.service.impl.PersonServiceImpl;
import ua.com.company.service.impl.PublicationServiceImpl;

@WebListener
public class AppContextListener implements ServletContextListener, ServletContextAttributeListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        //config app

        PersonDAO personDAO = null;
        PublicationDAO publicationDAO = null;
        try {
            publicationDAO = DAOFactory.getInstance().getPublicationDAO();
            personDAO = DAOFactory.getInstance().getPersonDAO();
        } catch (Exception e) {
            e.printStackTrace();
        }
        ServletContext servletContext = sce.getServletContext();

        PersonService personService = new PersonServiceImpl(personDAO);
        servletContext.setAttribute("personService", personService);

        PublicationService publicationService = new PublicationServiceImpl(publicationDAO);
        servletContext.setAttribute("publicationService", publicationService);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        ServletContextListener.super.contextDestroyed(sce);
        //destroy resources
    }

    @Override
    public void attributeAdded(ServletContextAttributeEvent event) {
        System.out.println("event.getName() = " + event.getName());
        System.out.println("event.getValue() = " + event.getValue());
        System.out.println("added");
    }


    @Override
    public void attributeRemoved(ServletContextAttributeEvent event) {
        System.out.println("event.getName() = " + event.getName());
        System.out.println("event.getValue() = " + event.getValue());
        System.out.println("removed");
    }

    @Override
    public void attributeReplaced(ServletContextAttributeEvent event) {
        System.out.println("event.getName() = " + event.getName());
        System.out.println("event.getValue() = " + event.getValue());
        System.out.println("replaced");
    }
}
