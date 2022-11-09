package ua.com.company.service;

import ua.com.company.entity.Topic;

import java.sql.Connection;
import java.util.List;

public interface TopicService extends BaseService<Topic> {

    boolean isExistByTitle(String topic);

    List<Topic> findAllByPublicationId( int id);

    Topic findByTitle(String title);
}
