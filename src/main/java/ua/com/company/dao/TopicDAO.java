package ua.com.company.dao;

import ua.com.company.entity.Topic;
import ua.com.company.exception.DBException;

import java.sql.Connection;
import java.util.List;

public interface TopicDAO extends BaseDAO<Topic> {
    boolean IsExistByTitle(Connection con, String title) throws DBException;
    List<Topic> findAllByPublicationId(Connection con,int pubId) throws DBException;
}
