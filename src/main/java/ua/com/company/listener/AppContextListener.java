package ua.com.company.listener;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletContextAttributeListener;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.com.company.dao.DAOFactory;
import ua.com.company.dao.PersonDAO;
import ua.com.company.dao.PublicationDAO;
import ua.com.company.dao.TopicDAO;
import ua.com.company.service.PersonService;
import ua.com.company.service.PublicationService;
import ua.com.company.service.TopicService;
import ua.com.company.service.impl.PersonServiceImpl;
import ua.com.company.service.impl.PublicationServiceImpl;
import ua.com.company.service.impl.TopicServiceImpl;
import ua.com.company.type.DBType;


@WebListener
public class AppContextListener implements ServletContextListener {
    private final Logger log = LoggerFactory.getLogger(PersonServiceImpl.class);

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        //config app

        DAOFactory.setDaoFactoryFQN(DBType.MYSQL);
        PersonDAO personDAO = null;
        PublicationDAO publicationDAO = null;
        TopicDAO topicDAO = null;
        try {
            publicationDAO = DAOFactory.getInstance().getPublicationDAO();
            personDAO = DAOFactory.getInstance().getPersonDAO();
            topicDAO =DAOFactory.getInstance().getTopicDAO();
        } catch (Exception e) {
            log.error(String.valueOf(e));
            e.printStackTrace();
        }
        ServletContext servletContext = sce.getServletContext();

        PersonService personService = new PersonServiceImpl(personDAO);
        servletContext.setAttribute("personService", personService);

        PublicationService publicationService = new PublicationServiceImpl(publicationDAO);
        servletContext.setAttribute("publicationService", publicationService);

        TopicService topicService = new TopicServiceImpl(topicDAO);
        servletContext.setAttribute("topicService",topicService);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        ServletContextListener.super.contextDestroyed(sce);
        //destroy resources
    }


}
