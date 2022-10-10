package ua.com.company.listener;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletContextAttributeListener;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import ua.com.company.dao.DAOFactory;
import ua.com.company.dao.PersonDAO;
import ua.com.company.dao.PublicationDAO;
import ua.com.company.service.PersonService;
import ua.com.company.service.PublicationService;
import ua.com.company.service.impl.PersonServiceImpl;
import ua.com.company.service.impl.PublicationServiceImpl;
import ua.com.company.type.DBType;


@WebListener
public class AppContextListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        //config app
//        initializeDao();


        DAOFactory.setDaoFactoryFQN(DBType.MYSQL);
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


}
