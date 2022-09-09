package ua.com.company.dao.mysql;

import ua.com.company.dao.DAOFactory;
import ua.com.company.dao.PersonDAO;
import ua.com.company.dao.PublicationDAO;
import ua.com.company.dao.TopicDAO;
import ua.com.company.dao.mysql.impl.MysqlPersonDAOImpl;
import ua.com.company.dao.mysql.impl.MysqlPublicationDAOImpl;
import ua.com.company.dao.mysql.impl.MysqlTopicDAOImpl;

//package?
public class MysqlDAOFactory extends DAOFactory {

    private PersonDAO personDAO;
    private PublicationDAO publicationDAO;
    private TopicDAO topicDAO;

    @Override
    public PersonDAO getPersonDAO() {
        if(personDAO==null) {
            personDAO = new MysqlPersonDAOImpl();
        }
        return personDAO;
    }

    @Override
    public PublicationDAO getPublicationDAO() {
        if(publicationDAO ==null){
            publicationDAO = new MysqlPublicationDAOImpl();
        }
        return publicationDAO;
    }

    @Override
    public TopicDAO getTopicDAO() {
        if(topicDAO==null){
            topicDAO = new MysqlTopicDAOImpl();
        }
        return topicDAO;
    }
}
