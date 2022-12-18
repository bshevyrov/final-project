package ua.com.company.service.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.com.company.dao.*;
import ua.com.company.dao.mysql.impl.MysqlPublicationCommentDAOImpl;
import ua.com.company.entity.Publication;
import ua.com.company.entity.PublicationComment;
import ua.com.company.entity.Sorting;
import ua.com.company.entity.Topic;
import ua.com.company.exception.DBException;
import ua.com.company.exception.PublicationNotFoundException;
import ua.com.company.service.PublicationService;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class PublicationServiceImpl implements PublicationService {
    private final Logger log = LogManager.getLogger(PublicationServiceImpl.class);
    private final PublicationDAO publicationDAO = DAOFactory.getInstance().getPublicationDAO();
    private final ImageDAO imageDAO = DAOFactory.getInstance().getImageDAO();
    private final TopicDAO topicDAO = DAOFactory.getInstance().getTopicDAO();
    private static PublicationService instance;
    private  final PublicationCommentDAO publicationCommentDAO = DAOFactory.getInstance().getPublicationCommentDAO();

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
    public void create(Publication publication) {
        Connection con = getConnection();
        try {
            con.setAutoCommit(false);
            imageDAO.create(con, publication.getCover());
            publicationDAO.create(con, publication);
            publication = publicationDAO.findByTitle(con, publication.getTitle());
            for (Topic topic : publication.getTopics()) {
                publicationDAO.addTopicForPublication(con, publication.getId(), topic.getId());
            }
            con.commit();
        } catch (SQLException | DBException e) {
            e.printStackTrace();
            log.error("Can`t create publication " + publication, e);
            rollback(con);
        } finally {
            close(con);
        }
    }

    @Override
    public void update(Publication publication) {
        Connection con = getConnection();
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
            log.error("Can`t update publication " + publication, e);
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
            log.error("Delete Error " + id, e);
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
            log.error("Publication not found " + id, e);
            e.printStackTrace();
        } catch (PublicationNotFoundException e) {
            log.warn("Publication not found " + id, e);
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
            log.error("findAll ex ", e);
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
            log.error("findAllByTopicId ex ", e);
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
            log.error("findAllByUserId ex ", e);
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
            log.error("findAllByTitle ex ", e);
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
            log.error("countAllByTopicId ex ", e);
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
            log.error("countAllByUserId ex ", e);
            e.printStackTrace();
        }
        return count;
    }

    @Override
    public int countAllByTitle(String searchReq) {
        int count = -1;
        try (Connection con = getConnection()) {
            count = publicationDAO.countAllByTitle(con, searchReq);
        } catch (DBException | SQLException e) {
            log.error("countAllByTitle ex ", e);
            e.printStackTrace();
        }
        return count;
    }

    @Override
    public List<PublicationComment> findAllCommentsByPublicationId(Sorting sorting, int publicationId) {
        List<PublicationComment> commentList = null;
        try (Connection con = getConnection()) {

            commentList = publicationCommentDAO.findAllCommentsByPublicationId(con, sorting, publicationId);

        } catch (DBException | SQLException e) {
            log.error("findAllCommentsByPublicationId exception  " , e);
            e.printStackTrace();
        }
        return commentList;
    }


}
