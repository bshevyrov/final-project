package ua.com.company.service.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.com.company.dao.DAOFactory;
import ua.com.company.dao.TopicDAO;
import ua.com.company.entity.Topic;
import ua.com.company.exception.DBException;
import ua.com.company.exception.TopicNotFoundException;
import ua.com.company.service.TopicService;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class TopicServiceImpl implements TopicService {
    private final Logger log = LogManager.getLogger(TopicServiceImpl.class);
    private final TopicDAO topicDao = DAOFactory.getInstance().getTopicDAO();
    private static TopicService instance;

    public static synchronized TopicService getInstance() {
        if (instance == null) {
            try {
                instance = new TopicServiceImpl();
            } catch (Exception e) {
                //TODO LOG
                e.printStackTrace();
            }
        }
        return instance;
    }

    private TopicServiceImpl() throws Exception {
    }

    @Override
    public void create(Topic topic) {
        try (Connection con = getConnection()) {
            topicDao.create(con, topic);
        } catch (DBException | SQLException e) {
            log.error("Can`t create publication " + topic, e);
            e.printStackTrace();
        }
    }

    @Override
    public void update(Topic topic) {
        try (Connection con = getConnection()) {
            topicDao.update(con, topic);
        } catch (DBException | SQLException e) {
            log.error("Can`t update topic " + topic, e);
            e.printStackTrace();
        }
    }

    @Override
    public void delete(int id) {
        try (Connection con = getConnection()) {
            topicDao.delete(con, id);
        } catch (DBException | SQLException e) {
            log.error("Delete Error" + id, e);
            e.printStackTrace();
        }
    }

    @Override
    public Topic findById(int id) {
        try (Connection con = getConnection()) {
            return topicDao.findById(con, id)
                    .orElseThrow(() -> new TopicNotFoundException("" + id));
        } catch (DBException | SQLException e) {
            log.error("Topic not found " + id, e);
            e.printStackTrace();
        } catch (TopicNotFoundException e) {
            log.warn("Topic not found " + id, e);

        }
        return null;
    }

    @Override
    public List<Topic> findAll() {
        try (Connection con = getConnection()) {
            return topicDao.findAll(con);
        } catch (DBException | SQLException e) {
            log.error("findAll ex ", e);
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean isExistByTitle(String title) {
        boolean existByTitle = false;
        try (Connection con = getConnection()) {
            existByTitle = topicDao.IsExistByTitle(con, title);
        } catch (DBException | SQLException e) {
            log.error("isExist ex " + title, e);
            e.printStackTrace();
        }
        return existByTitle;
    }

    @Override
    public List<Topic> findAllByPublicationId(int id) {
        try (Connection con = getConnection()) {
            return topicDao.findAllByPublicationId(con, id);
        } catch (DBException | SQLException e) {
            log.error("Find publication by id error with id= " + id, e);
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Topic findByTitle(String title) {
        try (Connection con = getConnection()) {
            return topicDao.findByTitle(con, title)
                    .orElseThrow(() -> new TopicNotFoundException("" + title));
        } catch (DBException | SQLException e) {
            log.error("Topic not found " + title, e);
            e.printStackTrace();
        } catch (TopicNotFoundException e) {
            log.warn("Topic not found " + title, e);
            e.printStackTrace();
        }
        return null;
    }
}
