package ua.com.company.dao.derby;

import ua.com.company.dao.*;
import ua.com.company.dao.derby.impl.DerbyImageDAOImpl;
import ua.com.company.dao.derby.impl.DerbyPersonDAOImpl;
import ua.com.company.dao.derby.impl.DerbyPublicationDAOImpl;
import ua.com.company.dao.derby.impl.DerbyTopicDAOImpl;

//package?
public class DerbyDAOFactory extends DAOFactory {

    private PersonDAO personDAO;
    private PublicationDAO publicationDAO;
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
}
