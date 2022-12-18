package ua.com.company.dao.derby;

import ua.com.company.dao.*;
import ua.com.company.dao.derby.impl.*;

//package?
public class DerbyDAOFactory extends DAOFactory {

    private PersonDAO personDAO;
    private PersonAddressDAO personAddressDAO;
    private PublicationDAO publicationDAO;
    private PublicationCommentDAO publicationCommentDAO;
    private TopicDAO topicDAO;
    private ImageDAO imageDAO;

    @Override
    public PersonDAO getPersonDAO() {
        if (personDAO == null) {
            personDAO = new DerbyPersonDAOImpl();
        }
        return personDAO;
    }

    @Override
    public PublicationDAO getPublicationDAO() {
        if (publicationDAO == null) {
            publicationDAO = new DerbyPublicationDAOImpl();
        }
        return publicationDAO;
    }

    @Override
    public TopicDAO getTopicDAO() {
        if (topicDAO == null) {
            topicDAO = new DerbyTopicDAOImpl();
        }
        return topicDAO;
    }

    @Override
    public ImageDAO getImageDAO() {
        if (imageDAO == null) {
            imageDAO = new DerbyImageDAOImpl();
        }
        return imageDAO;
    }

    @Override
    public PersonAddressDAO getPersonAddressDAO() {
        if (personAddressDAO == null) {
            personAddressDAO = new DerbyPersonAddressDAOImpl();
        }
        return personAddressDAO;
    }

    @Override
    public PublicationCommentDAO getPublicationCommentDAO() {
        if (publicationCommentDAO == null) {
            publicationCommentDAO = new DerbyPublicationCommentDAOImpl();
        }
        return publicationCommentDAO;
    }
}
