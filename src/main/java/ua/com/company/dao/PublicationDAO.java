package ua.com.company.dao;

import ua.com.company.entity.Image;
import ua.com.company.entity.Publication;
import ua.com.company.entity.Sorting;
import ua.com.company.exception.DBException;

import java.sql.Connection;
import java.util.List;

public interface PublicationDAO extends BaseDAO<Publication> {

    List<Publication> findAllByTitle(Connection con, Sorting sorting, String title) throws DBException;

    List<Publication> findAllByTopicId(Connection con, Sorting obj, int id) throws DBException;

    Publication findByTitle(Connection con, String title) throws DBException;

    List<Publication> findAllByPersonId(Connection con, Sorting obj, int userId) throws DBException;

    void addTopicForPublication(Connection con, int pubId, int topicId) throws DBException;

    void deleteOrphanTopic(Connection con) throws DBException;

    void deleteFromPublicationHasTopicByPublicationId(Connection con, int id) throws DBException;

    void updateCoverForPublication(Connection con, int id, Image cover) throws DBException;

    int countAllByTopicId(Connection con, int topicId) throws DBException;

    int countAllByUserId(Connection con, int userId) throws DBException;

    int countAllByTitle(Connection con, String searchReq) throws DBException;

    List<Publication> findAllByPersonId(Connection con, int id) throws DBException;

    void updateCover(Connection con, int pubId, int coverId) throws DBException;
}
