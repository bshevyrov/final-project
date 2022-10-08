package ua.com.company.service.impl;

import ua.com.company.dao.TopicDAO;
import ua.com.company.dao.mysql.impl.MysqlTopicDAOImpl;
import ua.com.company.entity.Topic;
import ua.com.company.exception.DBException;
import ua.com.company.service.TopicService;

import java.util.List;

public class TopicServiceImpl implements TopicService {
    TopicDAO topicDao = new MysqlTopicDAOImpl();
    @Override
    public void create(Topic topic) {
    topicDao.create(topic);
    }

    @Override
    public void update(Topic topic) {
        try {
            topicDao.update(topic);
        } catch (DBException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(int id) {
    topicDao.delete(id);
    }

    @Override
    public Topic findById(int id) {
        try {
            return topicDao.findById(id).get();
        } catch (DBException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Topic> findAll() {
        try {
            return topicDao.findAll();
        } catch (DBException e) {
            e.printStackTrace();
        }
        return null;
    }
}
