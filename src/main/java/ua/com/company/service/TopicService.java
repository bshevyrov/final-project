package ua.com.company.service;

import ua.com.company.entity.Topic;

import java.util.List;

public interface TopicService extends BaseService<Topic> {

    List<Topic> findAllByPublicationId(int id);

    Topic findByTitle(String title);
}
