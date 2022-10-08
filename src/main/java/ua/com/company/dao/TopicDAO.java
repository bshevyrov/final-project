package ua.com.company.dao;

import ua.com.company.entity.Topic;

import java.util.Optional;

public interface TopicDAO extends BaseDAO<Topic> {

    Optional<Topic> findByTitle(String topic);
}
