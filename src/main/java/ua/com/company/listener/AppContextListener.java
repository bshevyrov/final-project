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

        servletContext.setAttribute("openSessions", new ArrayList<HttpSession>());
        servletContext.setAttribute("topicFacade", new TopicFacadeImpl(new TopicServiceImpl(DAOFactory.getInstance().getTopicDAO())));
        servletContext.setAttribute("imageFacade", new ImageFacadeImpl(new ImageServiceImpl(DAOFactory.getInstance().getImageDAO())));
        servletContext.setAttribute("publicationCommentFacade", new PublicationCommentFacadeImpl(new PublicationCommentServiceImpl(
                DAOFactory.getInstance().getPublicationCommentDAO())));
        servletContext.setAttribute("personAddressFacade", new PersonAddressFacadeImpl(new PersonAddressServiceImpl(
                DAOFactory.getInstance().getPersonAddressDAO())));
        servletContext.setAttribute("personFacade", new PersonFacadeImpl(new PersonServiceImpl(
                DAOFactory.getInstance().getPersonDAO(),
                DAOFactory.getInstance().getPersonAddressDAO(),
                DAOFactory.getInstance().getImageDAO(),
                DAOFactory.getInstance().getPublicationDAO())));
        servletContext.setAttribute("publicationFacade", new PublicationFacadeImpl(new PublicationServiceImpl(
                DAOFactory.getInstance().getPublicationDAO(),
                DAOFactory.getInstance().getImageDAO(),
                DAOFactory.getInstance().getTopicDAO())));
    }
}
