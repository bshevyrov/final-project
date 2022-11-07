package ua.com.company.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.com.company.dao.DAOFactory;
import ua.com.company.dao.ImageDAO;
import ua.com.company.dao.PublicationDAO;
import ua.com.company.dao.TopicDAO;
import ua.com.company.entity.Publication;
import ua.com.company.entity.Sorting;
import ua.com.company.entity.Topic;
import ua.com.company.exception.DBException;
import ua.com.company.exception.PublicationNotFoundException;
import ua.com.company.service.PublicationService;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class PublicationServiceImpl implements PublicationService {
    private final Logger log = LoggerFactory.getLogger(PublicationServiceImpl.class);
    private final PublicationDAO publicationDAO = DAOFactory.getInstance().getPublicationDAO();
    private final ImageDAO imageDAO = DAOFactory.getInstance().getImageDAO();
    private final TopicDAO topicDAO = DAOFactory.getInstance().getTopicDAO();
    private static PublicationService instance;

    public static synchronized PublicationService getInstance() {
        if (instance == null) {
            try {
                instance = new PublicationServiceImpl();
            } catch (Exception e) {
                //TODO LOG
                e.printStackTrace();
            }
        }
        return instance;
    }

    private PublicationServiceImpl() throws Exception {
    }

    @Override
    public int create(Publication publication) {
        Connection con = null;
        try {
            con = getConnection();
        } catch (DBException e) {
            e.printStackTrace();
        }
        int id = -1;
        try {
            con.setAutoCommit(false);
            imageDAO.create(con, publication.getCover());
            id = publicationDAO.create(con, publication);
            publication.setId(id);
            for (Topic topic : publication.getTopics()) {
                publicationDAO.addTopicForPublication(con, publication.getId(), topic.getId());
            }
            con.commit();
        } catch (SQLException | DBException e) {
            e.printStackTrace();
            rollback(con);
        } finally {
            close(con);
        }
        return id;
    }

    @Override
    public void update(Publication publication) {
        Connection con = null;
        try {
            con = getConnection();
        } catch (DBException e) {
            e.printStackTrace();
        }
        try {
            con.setAutoCommit(false);
            publicationDAO.update(con, publication);
            publicationDAO.updateCoverForPublication(con, publication.getId(), publication.getCover());
            publicationDAO.deleteFromPublicationHasTopicByPublicationId(con, publication.getId());
            for (Topic topic : publication.getTopics()) {
                publicationDAO.addTopicForPublication(con, publication.getId(), topic.getId());
            }
            publicationDAO.deleteOrphanTopic(con);
            con.commit();
        } catch (SQLException | DBException e) {
            e.printStackTrace();
            rollback(con);
        } finally {
            close(con);
        }

    }

    @Override
    public void delete(int id) {
        try (Connection con = getConnection()) {
            publicationDAO.delete(con, id);
        } catch (DBException | SQLException e) {
            log.error(String.valueOf(e));
            e.printStackTrace();
        }
    }

    @Override
    public Publication findById(int id) {
        Publication publication = null;
        try (Connection con = getConnection()) {
            publication = publicationDAO.findById(con, id)
                    .orElseThrow(() -> new PublicationNotFoundException("" + id));
            publication.setTopics(topicDAO.findAllByPublicationId(con, publication.getId()));
        } catch (DBException | SQLException e) {
            log.error(String.valueOf(e));
            e.printStackTrace();
        } catch (PublicationNotFoundException e) {
            log.warn(String.valueOf(e));
            e.printStackTrace();
        }
        return publication;
    }

    @Override
    public List<Publication> findAll() {
        List<Publication> publicationList = null;
        try (Connection con = getConnection()) {
            publicationList = publicationDAO.findAll(con);
            for (Publication publication : publicationList) {
                publication.setTopics(topicDAO.findAllByPublicationId(con, publication.getId()));
            }
        } catch (DBException | SQLException e) {
            log.error(String.valueOf(e));
            e.printStackTrace();
        }
        return publicationList;
    }

    @Override
    public List<Publication> findAllByTopicId(Sorting obj, int topicId) {
        List<Publication> publicationList = null;
        try (Connection con = getConnection()) {
            publicationList = publicationDAO.findAllByTopicId(con, obj, topicId);
            for (Publication currentPub : publicationList) {
                List<Topic> topicList = topicDAO.findAllByPublicationId(con, currentPub.getId());
                currentPub.setTopics(topicList);
            }
        } catch (DBException | SQLException e) {
            log.error(String.valueOf(e));
            e.printStackTrace();
        }
        return publicationList;
    }

    @Override
    public List<Publication> findAllByUserId(Sorting obj, int userId) {
        List<Publication> publicationList = null;
        try (Connection con = getConnection()) {
            publicationList = publicationDAO.findAllByUserId(con, obj, userId);
            for (Publication publication : publicationList) {
                publication.setTopics(topicDAO.findAllByPublicationId(con, publication.getId()));
            }
        } catch (DBException | SQLException e) {
            log.error(String.valueOf(e));
            e.printStackTrace();
        }
        return publicationList;
    }

    @Override
    public List<Publication> findAllByTitle(Sorting sorting, String searchReq) {
        List<Publication> publicationList = null;
        try (Connection con = getConnection()) {
            publicationList = publicationDAO.findAllByTitle(con, sorting, searchReq);
            for (Publication publication : publicationList) {
                publication.setTopics(topicDAO.findAllByPublicationId(con, publication.getId()));
            }
        } catch (DBException | SQLException e) {
            log.error(String.valueOf(e));
            e.printStackTrace();
        }
        return publicationList;
    }

    @Override
    public int countAllByTopicId(int topicId) {
        int count = -1;
        try (Connection con = getConnection()) {
            count = publicationDAO.countAllByTopicId(con, topicId);
        } catch (DBException | SQLException e) {
            log.error(String.valueOf(e));
            e.printStackTrace();
        }
        return count;
    }

    @Override
    public int countAllByUserId(int userId) {
        int count = -1;
        try (Connection con = getConnection()) {
            count = publicationDAO.countAllByUserId(con, userId);
        } catch (DBException | SQLException e) {
            log.error(String.valueOf(e));
            e.printStackTrace();
        }
        return count;
    }

    @Override
    public int countAllByTitle(String searchReq) {
        int count = -1;
        try (Connection con = getConnection()) {
            count = publicationDAO.countAllByTitle(con,searchReq);
        } catch (DBException | SQLException e) {
            log.error(String.valueOf(e));
            e.printStackTrace();
        }
        return count;




    }
}
