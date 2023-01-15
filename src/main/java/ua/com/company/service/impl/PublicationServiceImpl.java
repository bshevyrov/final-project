package ua.com.company.service.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.com.company.dao.ImageDAO;
import ua.com.company.dao.PublicationDAO;
import ua.com.company.dao.TopicDAO;
import ua.com.company.entity.*;
import ua.com.company.exception.DBException;
import ua.com.company.exception.PublicationNotFoundException;
import ua.com.company.service.PublicationService;
import ua.com.company.utils.DBConnection;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class PublicationServiceImpl implements PublicationService {
    private final Logger log = LogManager.getLogger(PublicationServiceImpl.class);
    private final PublicationDAO publicationDAO;
    private final ImageDAO imageDAO;
    private final TopicDAO topicDAO;

    public PublicationServiceImpl(PublicationDAO publicationDAO, ImageDAO imageDAO, TopicDAO topicDAO) {
        this.publicationDAO = publicationDAO;
        this.imageDAO = imageDAO;
        this.topicDAO = topicDAO;
    }

    /**
     * @param publication entity to put in Database
     */
    @Override
    public void create(Publication publication) {
        Connection con = DBConnection.getConnection();
        try {
            con.setAutoCommit(false);
            publication.getCover().setId(imageDAO.create(con, publication.getCover()));
            publication.setId(publicationDAO.create(con, publication));
            for (Topic topic : publication.getTopics()) {
                int topicId = topicDAO.create(con, topic);
                publicationDAO.addTopicToPublication(con, publication.getId(), topicId);
            }
            con.commit();
        } catch (SQLException | DBException e) {
            log.error("Can`t create publication ", e);
            rollback(con);
        } finally {
            close(con);
        }
    }

    /**
     * @param publication entity to update in Database
     */
    @Override
    public void update(Publication publication) {
        Connection con = DBConnection.getConnection();
        try {
            con.setAutoCommit(false);
            publicationDAO.update(con, publication);
            publicationDAO.deleteFromPublicationHasTopicByPublicationId(con, publication.getId());
            for (Topic topic : publication.getTopics()) {
                if (topic.getId() == 0) {
                    topic.setId(topicDAO.create(con, topic));
                }
                publicationDAO.addTopicToPublication(con, publication.getId(), topic.getId());
            }
            publicationDAO.deleteOrphanTopic(con);
            con.commit();
        } catch (SQLException | DBException e) {
            log.error("Can`t update publication ", e);
            rollback(con);
        } finally {
            close(con);
        }

    }

    /**
     * @param id id of entity  that need to delete
     */
    @Override
    public void delete(int id) {
        try (Connection con = DBConnection.getConnection()) {
            publicationDAO.delete(con, id);
        } catch (DBException | SQLException e) {
            log.error("Delete Error ", e);
        }
    }

    /**
     * @param id id of entity that need to find
     * @return entity or throw {@link PublicationNotFoundException}
     */
    @Override
    public Publication findById(int id) {
        Publication publication = null;
        try (Connection con = DBConnection.getConnection()) {
            publication = publicationDAO.findById(con, id)
                    .orElseThrow(() -> new PublicationNotFoundException("" + id));
            publication.setCover(imageDAO.findById(con, publication.getCover().getId()).get());
            publication.setTopics(topicDAO.findAllByPublicationId(con, publication.getId()));
        } catch (DBException | SQLException e) {
            log.error("Find by id error", e);
        }
        return publication;
    }

    /**
     * @return List of entities
     */
    @Override
    public List<Publication> findAll() {
        List<Publication> publicationList = null;
        try (Connection con = DBConnection.getConnection()) {
            publicationList = publicationDAO.findAll(con);
            for (Publication publication : publicationList) {
                publication.setCover(imageDAO.findById(con, publication.getCover().getId()).get());
                publication.setTopics(topicDAO.findAllByPublicationId(con, publication.getId()));
            }
        } catch (DBException | SQLException e) {
            log.error("findAll ex ", e);
        }
        return publicationList;
    }

    /**
     * @param sorting {@link Sorting} object with attributes
     * @param topicId id of {@link Topic}
     * @return List of entities
     */
    @Override
    public List<Publication> findAllByTopicId(Sorting sorting, int topicId) {
        List<Publication> publicationList = null;
        try (Connection con = DBConnection.getConnection()) {
            publicationList = publicationDAO.findAllByTopicId(con, sorting, topicId);
            for (Publication currentPub : publicationList) {
                currentPub.setCover(imageDAO.findById(con, currentPub.getCover().getId()).get());
                currentPub.setTopics(topicDAO.findAllByPublicationId(con, currentPub.getId()));
            }
        } catch (DBException | SQLException e) {
            log.error("findAllByTopicId ex ", e);
        }
        return publicationList;
    }

    /**
     * @param sorting {@link Sorting} object with attributes
     * @param userId  id of {@link Person}
     * @return List of entities
     */
    @Override
    public List<Publication> findAllByUserId(Sorting sorting, int userId) {
        List<Publication> publicationList = null;
        try (Connection con = DBConnection.getConnection()) {
            publicationList = publicationDAO.findAllByPersonId(con, sorting, userId);
            for (Publication publication : publicationList) {
                publication.setCover(imageDAO.findById(con, publication.getCover().getId()).get());
                publication.setTopics(topicDAO.findAllByPublicationId(con, publication.getId()));
            }
        } catch (DBException | SQLException e) {
            log.error("findAllByUserId ex ", e);
        }
        return publicationList;
    }

    /**
     * @param sorting   {@link Sorting} object with attributes
     * @param searchReq part of search request
     * @return List of entities
     */
    @Override
    public List<Publication> findAllByTitle(Sorting sorting, String searchReq) {
        List<Publication> publicationList = null;
        try (Connection con = DBConnection.getConnection()) {
            publicationList = publicationDAO.findAllByTitle(con, sorting, searchReq);
            for (Publication publication : publicationList) {
                publication.setCover(imageDAO.findById(con, publication.getCover().getId()).get());
                publication.setTopics(topicDAO.findAllByPublicationId(con, publication.getId()));
            }
        } catch (DBException | SQLException e) {
            log.error("findAllByTitle ex ", e);
        }
        return publicationList;
    }

    /**
     * @param topicId id of {@link Topic}
     * @return count of publication with topicId
     */
    @Override
    public int countAllByTopicId(int topicId) {
        int count = -1;
        try (Connection con = DBConnection.getConnection()) {
            count = publicationDAO.countAllByTopicId(con, topicId);
        } catch (DBException | SQLException e) {
            log.error("countAllByTopicId ex ", e);
        }
        return count;
    }

    /**
     * @param userId id of {@link Person}
     * @return count of publication associated with userId
     */
    @Override
    public int countAllByUserId(int userId) {
        int count = -1;
        try (Connection con = DBConnection.getConnection()) {
            count = publicationDAO.countAllByUserId(con, userId);
        } catch (DBException | SQLException e) {
            log.error("countAllByUserId ex ", e);
        }
        return count;
    }

    /**
     * @param searchReq part of search request
     * @return count of publication that have sea rchReq in title
     */
    @Override
    public int countAllByTitle(String searchReq) {
        int count = -1;
        try (Connection con = DBConnection.getConnection()) {
            count = publicationDAO.countAllByTitle(con, searchReq);
        } catch (DBException | SQLException e) {
            log.error("countAllByTitle ex ", e);
        }
        return count;
    }

    /**
     * @param pubId   id of{@link Publication}
     * @param coverId id of{@link Image}
     */
    @Override
    public void updateCover(int pubId, int coverId) {
        try (Connection con = DBConnection.getConnection()) {
            publicationDAO.updateCover(con, pubId, coverId);
        } catch (DBException | SQLException e) {
            log.error("Update cover ex ", e);
        }
    }
}
