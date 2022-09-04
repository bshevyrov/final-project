package ua.com.company.service.impl;

import ua.com.company.dao.TopicDao;
import ua.com.company.dao.impl.TopicDaoImpl;
import ua.com.company.entity.Topic;
import ua.com.company.service.TopicService;

import java.util.List;

public class TopicServiceImpl implements TopicService {
    TopicDao topicDao = new TopicDaoImpl();
    @Override
    public void create(Topic topic) {
    topicDao.create(topic);
    }

    @Override
    public void update(Topic topic) {
    topicDao.update(topic);
    }

    @Override
    public void delete(int id) {
    topicDao.delete(id);
    }

    @Override
    public Topic findById(int id) {
        return topicDao.findById(id);
    }

    @Override
    public List<Topic> findAll() {
        return topicDao.findAll();
    }
}
