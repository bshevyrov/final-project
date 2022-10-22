package ua.com.company.dao;

import ua.com.company.entity.Topic;
import ua.com.company.exception.DBException;

import java.util.List;

public interface TopicDAO extends BaseDAO<Topic> {
    boolean IsExistByTitle(String title) throws DBException;
    List<Topic> findAllByPublicationId(int pubId) throws DBException;
}
