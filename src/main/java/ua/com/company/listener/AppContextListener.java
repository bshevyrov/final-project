package ua.com.company.listener;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebListener;
import ua.com.company.dao.DAOFactory;
import ua.com.company.dao.PersonDAO;
import ua.com.company.dao.PublicationDAO;
import ua.com.company.dao.mysql.MysqlDAOFactory;
import ua.com.company.service.PersonService;
import ua.com.company.service.PublicationService;
import ua.com.company.service.impl.PersonServiceImpl;
import ua.com.company.service.impl.PublicationServiceImpl;
import ua.com.company.type.DBType;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.DriverManager;
import java.sql.SQLException;

@WebListener
public class AppContextListener implements ServletContextListener, ServletContextAttributeListener {

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

   /* public static DataSource initializeDao() {
        Context initCtx;
        DataSource ds = null;
        try {
            initCtx = new InitialContext();
            Context envCtx = (Context) initCtx.lookup("java:comp/env");
            ds = (DataSource) envCtx.lookup("jdbc/MySQL");
            String FQN = String.valueOf(DriverManager.getDriver(ds.getConnection().getMetaData().getURL()).getClass());
            DAOFactory.setDaoFactoryFQN(FQN);
        } catch (NamingException | SQLException e) {
            e.printStackTrace();
        }
        return ds;
    }*/
}
