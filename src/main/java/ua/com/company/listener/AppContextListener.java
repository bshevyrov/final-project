package ua.com.company.listener;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.com.company.dao.DAOFactory;
import ua.com.company.facade.impl.PersonFacadeImpl;
import ua.com.company.facade.impl.PublicationFacadeImpl;
import ua.com.company.facade.impl.TopicFacadeImpl;
import ua.com.company.service.impl.PersonServiceImpl;
import ua.com.company.service.impl.PublicationServiceImpl;
import ua.com.company.service.impl.TopicServiceImpl;
import ua.com.company.type.DBType;


@WebListener
public class AppContextListener implements ServletContextListener {
    private final Logger log = LogManager.getLogger(PersonServiceImpl.class);

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        //config app

        DAOFactory.setDaoFactoryFQN(DBType.MYSQL);
//        PersonDAO personDAO = null;
//        PublicationDAO publicationDAO = null;
//        TopicDAO topicDAO = null;


 /*       try {
            publicationDAO = DAOFactory.getInstance().getPublicationDAO();
            personDAO = DAOFactory.getInstance().getPersonDAO();
            topicDAO =DAOFactory.getInstance().getTopicDAO();
        } catch (Exception e) {
            log.error(String.valueOf(e));
            e.printStackTrace();
        }*/
        ServletContext servletContext = sce.getServletContext();
//
//        PersonService personService = new PersonServiceImpl(personDAO);
//        servletContext.setAttribute("personService", personService);
//
//        PublicationService publicationService = new PublicationServiceImpl(publicationDAO);
//        servletContext.setAttribute("publicationService", publicationService);
//
//        TopicService topicService = new TopicServiceImpl(topicDAO);
//        servletContext.setAttribute("topicService",topicService);
        servletContext.setAttribute("topicFacade", new TopicFacadeImpl(TopicServiceImpl.getInstance()));
        servletContext.setAttribute("personFacade", new PersonFacadeImpl(PersonServiceImpl.getInstance(), PublicationServiceImpl.getInstance()));
        servletContext.setAttribute("publicationFacade", new PublicationFacadeImpl(PublicationServiceImpl.getInstance()));

    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        ServletContextListener.super.contextDestroyed(sce);
        //destroy resources
    }


}
