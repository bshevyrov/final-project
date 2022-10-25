package ua.com.company.dao;

import ua.com.company.entity.Image;
import ua.com.company.entity.Publication;
import ua.com.company.entity.Sorting;
import ua.com.company.entity.Topic;
import ua.com.company.exception.DBException;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface PublicationDAO extends BaseDAO<Publication> {

//    void create(Publication p,Image... images) throws DBException;
    List<Publication> findAllByTitle(Connection con,String title) throws DBException;
    List<Publication> findAllByTopicId(Connection con,Sorting obj, int id) throws DBException;
//    void addImage(Connection con, Publication publication, Image... images);
//    void addTopicForPublication(Publication publication, Topic... topics);

    Publication findByTitle(Connection con,String title) throws DBException;

    List<Publication> findAllByUserId(Connection con,int userId) throws DBException;
     void addTopicForPublication(Connection con, int pubId, int topicId) throws SQLException;

    void deleteOrphanTopic(Connection con) throws SQLException;

    void deleteFromPublicationHasTopicByPublicationId(Connection con, int id) throws SQLException;

    void updateCoverForPublication(Connection con, int id, Image cover) throws SQLException;

    int countAllByTopicId(Connection con, int topicId) throws DBException;
//    List<Publication> findAllLikeTitle(String searchReq);
}
