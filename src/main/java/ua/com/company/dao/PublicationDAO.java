package ua.com.company.dao;

import ua.com.company.entity.Image;
import ua.com.company.entity.Publication;
import ua.com.company.entity.Sorting;
import ua.com.company.entity.Topic;
import ua.com.company.exception.DBException;

import java.sql.SQLException;
import java.util.List;

public interface PublicationDAO extends BaseDAO<Publication> {

//    void create(Publication p,Image... images) throws DBException;
    List<Publication> findAllByTitle(String title) throws DBException;
    List<Publication> findAllByTopicId(Sorting obj, int id) throws DBException;
//    void addImage(Connection con, Publication publication, Image... images);
//    void addTopicForPublication(Publication publication, Topic... topics);

    Publication findByTitle(String title) throws DBException;

    List<Publication> findAllByUserId(int userId) throws DBException;

//    List<Publication> findAllLikeTitle(String searchReq);
}
