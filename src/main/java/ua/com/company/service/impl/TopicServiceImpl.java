package ua.com.company.service.impl;

import ua.com.company.dao.TopicDAO;
import ua.com.company.entity.Topic;
import ua.com.company.exception.DBException;
import ua.com.company.exception.TopicNotFoundException;
import ua.com.company.service.TopicService;

import java.util.List;

public class TopicServiceImpl implements TopicService {
   private final TopicDAO topicDao;

    public TopicServiceImpl(TopicDAO topicDao) {
        this.topicDao = topicDao;
    }

    @Override
    public void create(Topic topic) {
        try {
            topicDao.create(topic);
        } catch (DBException e) {
            e.printStackTrace();
        }
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
        try {
            topicDao.delete(id);
        } catch (DBException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Topic findById(int id) throws TopicNotFoundException {
        try {
            return topicDao.findById(id)
                    .orElseThrow(()-> new TopicNotFoundException(""+id));
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
