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

    //    public TopicServiceImpl(TopicDAO topicDao) {
//        this.topicDao = topicDao;
//    }
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
    public int create(Topic topic) {
        int id = -1;
        try (Connection con = getConnection()) {
            id = topicDao.create(con, topic);
        } catch (DBException | SQLException e) {
            log.error(String.valueOf(e));
            e.printStackTrace();
        }
        return id;
    }

    @Override
    public void update(Topic topic) {
        try (Connection con = getConnection()) {
            topicDao.update(con, topic);
        } catch (DBException | SQLException e) {
            log.error(String.valueOf(e));
            e.printStackTrace();
        }
    }

    @Override
    public void delete(int id) {
        try (Connection con = getConnection()) {
            topicDao.delete(con, id);
        } catch (DBException | SQLException e) {
            log.error(String.valueOf(e));
            e.printStackTrace();
        }
    }

    @Override
    public Topic findById(int id) {
        try (Connection con = getConnection()) {
            return topicDao.findById(con, id)
                    .orElseThrow(() -> new TopicNotFoundException("" + id));
        } catch (DBException | SQLException e) {
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
        try (Connection con = getConnection()) {
            return topicDao.findAll(con);
        } catch (DBException | SQLException e) {
            log.error(String.valueOf(e));
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
            log.error(String.valueOf(e));
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
}
