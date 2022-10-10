package ua.com.company.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.com.company.dao.TopicDAO;
import ua.com.company.entity.Topic;
import ua.com.company.exception.DBException;
import ua.com.company.exception.TopicNotFoundException;
import ua.com.company.service.TopicService;

import java.util.List;

public class TopicServiceImpl implements TopicService {
    private final TopicDAO topicDao;
    private final Logger log = LoggerFactory.getLogger(TopicServiceImpl.class);

    public TopicServiceImpl(TopicDAO topicDao) {
        this.topicDao = topicDao;
    }

    @Override
    public int create(Topic topic) {
        int id = -1;
        try {
            id = topicDao.create(topic);
        } catch (DBException e) {
            log.error(String.valueOf(e));
            e.printStackTrace();
        }
        return id;
    }

    @Override
    public void update(Topic topic) {
        try {
            topicDao.update(topic);
        } catch (DBException e) {
            log.error(String.valueOf(e));
            e.printStackTrace();
        }
    }

    @Override
    public void delete(int id) {
        try {
            topicDao.delete(id);
        } catch (DBException e) {
            log.error(String.valueOf(e));
            e.printStackTrace();
        }
    }

    @Override
    public Topic findById(int id) {
        try {
            return topicDao.findById(id)
                    .orElseThrow(() -> new TopicNotFoundException("" + id));
        } catch (DBException e) {
            log.error(String.valueOf(e));
            e.printStackTrace();
        } catch (TopicNotFoundException e) {
            log.warn(String.valueOf(e));
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Topic> findAll() {
        try {
            return topicDao.findAll();
        } catch (DBException e) {
            log.error(String.valueOf(e));
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean isExistByTitle(String title) {
        boolean existByTitle = false;
        try {
            existByTitle = topicDao.IsExistByTitle(title);
        } catch (DBException e) {
            log.error(String.valueOf(e));
            e.printStackTrace();
        }
        return existByTitle;
    }
}
