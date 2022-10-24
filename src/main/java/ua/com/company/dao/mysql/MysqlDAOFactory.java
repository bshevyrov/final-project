package ua.com.company.dao.mysql;

import ua.com.company.dao.*;
import ua.com.company.dao.mysql.impl.MysqlImageDAOImpl;
import ua.com.company.dao.mysql.impl.MysqlPersonDAOImpl;
import ua.com.company.dao.mysql.impl.MysqlPublicationDAOImpl;
import ua.com.company.dao.mysql.impl.MysqlTopicDAOImpl;

//package?
public class MysqlDAOFactory extends DAOFactory {

    private PersonDAO personDAO;
    private PublicationDAO publicationDAO;
    private TopicDAO topicDAO;
    private ImageDAO imageDAO;

    @Override
    public PersonDAO getPersonDAO() {
        if (personDAO == null) {
            personDAO = new MysqlPersonDAOImpl();
        }
        return personDAO;
    }

    @Override
    public PublicationDAO getPublicationDAO() {
        if (publicationDAO == null) {
            publicationDAO = new MysqlPublicationDAOImpl();
        }
        return publicationDAO;
    }

    @Override
    public TopicDAO getTopicDAO() {
        if (topicDAO == null) {
            topicDAO = new MysqlTopicDAOImpl();
        }
        return topicDAO;
    }

    @Override
    public ImageDAO getImageDAO() {
        if (imageDAO == null) {
            imageDAO = new MysqlImageDAOImpl();
        }
        return imageDAO;
    }
}
