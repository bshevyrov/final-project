package ua.com.company.listener;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import jakarta.servlet.http.HttpSession;
import ua.com.company.dao.DAOFactory;
import ua.com.company.facade.impl.*;
import ua.com.company.service.impl.*;
import ua.com.company.type.DBType;

import java.util.ArrayList;


@WebListener
public class AppContextListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {

        DAOFactory.setDaoFactoryFQN(DBType.MYSQL);

        ServletContext servletContext = sce.getServletContext();

        servletContext.setAttribute("topicFacade", new TopicFacadeImpl(TopicServiceImpl.getInstance()));
        servletContext.setAttribute("personFacade", new PersonFacadeImpl(PersonServiceImpl.getInstance()));
        servletContext.setAttribute("personAddressFacade", new PersonAddressFacadeImpl(PersonAddressServiceImpl.getInstance()));
        servletContext.setAttribute("publicationFacade", new PublicationFacadeImpl(PublicationServiceImpl.getInstance()));
        servletContext.setAttribute("publicationCommentFacade", new PublicationCommentFacadeImpl(PublicationCommentServiceImpl.getInstance()));
        servletContext.setAttribute("openSessions", new ArrayList<HttpSession>());
    }
}
