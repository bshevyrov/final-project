package ua.com.company.dao.mysql;

import ua.com.company.dao.*;
import ua.com.company.dao.mysql.impl.*;


public class MysqlDAOFactory extends DAOFactory {
    private PersonDAO personDAO;
    private PersonAddressDAO personAddressDAO;
    private PublicationDAO publicationDAO;
    private PublicationCommentDAO publicationCommentDAO;
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

    @Override
    public PersonAddressDAO getPersonAddressDAO() {
        if (personAddressDAO == null) {
            personAddressDAO = new MysqlPersonAddressDAOImpl();
        }
        return personAddressDAO;
    }

    public PublicationCommentDAO getPublicationCommentDAO() {
        if (publicationCommentDAO == null) {
            publicationCommentDAO = new MysqlPublicationCommentDAOImpl();
        }
        return publicationCommentDAO;
    }
}
