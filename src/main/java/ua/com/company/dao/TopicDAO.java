package ua.com.company.dao;

import ua.com.company.entity.Topic;
import ua.com.company.exception.DBException;

import java.sql.Connection;
import java.util.List;
import java.util.Optional;

public interface TopicDAO extends BaseDAO<Topic> {
    List<Topic> findAllByPublicationId(Connection con, int pubId) throws DBException;

    Optional<Topic> findByTitle(Connection con, String title) throws DBException;
}
