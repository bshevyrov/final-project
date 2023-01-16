package ua.com.company.service.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.com.company.dao.TopicDAO;
import ua.com.company.entity.Publication;
import ua.com.company.entity.Topic;
import ua.com.company.exception.DBException;
import ua.com.company.exception.TopicNotFoundException;
import ua.com.company.service.TopicService;
import ua.com.company.utils.DBConnection;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TopicServiceImpl implements TopicService {
    private final Logger log = LogManager.getLogger(TopicServiceImpl.class);
    private final TopicDAO topicDAO;

    public TopicServiceImpl(TopicDAO topicDAO) {
        this.topicDAO = topicDAO;
    }

    /**
     * @param topic entity to put in Database
     */
    @Override
    public void create(Topic topic) {
        try (Connection con = DBConnection.getConnection()) {
            topicDAO.create(con, topic);
        } catch (DBException | SQLException e) {
            log.error("Can`t create topic ", e);
        }
    }

    /**
     * @param topic entity to update in Database
     */
    @Override
    public void update(Topic topic) {
        try (Connection con = DBConnection.getConnection()) {
            topicDAO.update(con, topic);
        } catch (DBException | SQLException e) {
            log.error("Can`t update topic ", e);
        }
    }

    /**
     * @param id id of entity  that need to delete
     */
    @Override
    public void delete(int id) {
        try (Connection con = DBConnection.getConnection()) {
            topicDAO.delete(con, id);
        } catch (DBException | SQLException e) {
            log.error("Delete Error", e);
        }
    }

    /**
     * @param id id  of entity that need to find
     * @return entity or throw {@link TopicNotFoundException}
     */
    @Override
    public Topic findById(int id) {
        Topic topic = null;
        try (Connection con = DBConnection.getConnection()) {
            topic = topicDAO.findById(con, id)
                    .orElseThrow(() -> new TopicNotFoundException("" + id));
        } catch (DBException | SQLException e) {
            log.error("Find by id error", e);
        }
        return topic;
    }

    /**
     * @return List of entities
     */
    @Override
    public List<Topic> findAll() {
        List<Topic> topics = new ArrayList<>();
        try (Connection con = DBConnection.getConnection()) {
            topics = topicDAO.findAll(con);
        } catch (DBException | SQLException e) {
            log.error("findAll ex ", e);
        }
        return topics;
    }

    /**
     * @param id id of {@link Publication}
     * @return List of entities
     */
    @Override
    public List<Topic> findAllByPublicationId(int id) {
        List<Topic> topics = new ArrayList<>();
        try (Connection con = DBConnection.getConnection()) {
            topics = topicDAO.findAllByPublicationId(con, id);
        } catch (DBException | SQLException e) {
            log.error("Find publication by id error. ", e);
        }
        return topics;
    }

    /**
     * @param title of {@link Publication}
     * @return List of entities
     */
    @Override
    public Topic findByTitle(String title) {
        Topic topic = null;
        try (Connection con = DBConnection.getConnection()) {
            topic = topicDAO.findByTitle(con, title)
                    .orElseThrow(() -> new TopicNotFoundException("" + title));
        } catch (DBException | SQLException e) {
            log.error("Topic find by title error ", e);
        }
        return topic;
    }
}
