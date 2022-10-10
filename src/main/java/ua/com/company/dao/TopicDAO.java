package ua.com.company.dao;

import ua.com.company.entity.Topic;
import ua.com.company.exception.DBException;

import java.util.Optional;

public interface TopicDAO extends BaseDAO<Topic> {
    boolean IsExistByTitle(String title) throws DBException;
}
