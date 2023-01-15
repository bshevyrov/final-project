package ua.com.company.service.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.com.company.dao.PublicationCommentDAO;
import ua.com.company.entity.Publication;
import ua.com.company.entity.PublicationComment;
import ua.com.company.entity.Sorting;
import ua.com.company.exception.DBException;
import ua.com.company.exception.PublicationCommentNotFoundException;
import ua.com.company.service.PublicationCommentService;
import ua.com.company.utils.DBConnection;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PublicationCommentServiceImpl implements PublicationCommentService {
    private final Logger log = LogManager.getLogger(PublicationCommentServiceImpl.class);
    private final PublicationCommentDAO publicationCommentDAO;

    public PublicationCommentServiceImpl(PublicationCommentDAO publicationCommentDAO) {
        this.publicationCommentDAO = publicationCommentDAO;
    }

    /**
     * @param publicationComment entity to put in Database
     */
    @Override
    public void create(PublicationComment publicationComment) {
        try (Connection con = DBConnection.getConnection()) {
            publicationCommentDAO.create(con, publicationComment);
        } catch (SQLException | DBException e) {
            log.error("Create publicationComment error ", e);
        }
    }

    /**
     * @param publicationComment entity to update in Database
     */
    @Override
    public void update(PublicationComment publicationComment) {
        try (Connection con = DBConnection.getConnection()) {
            publicationCommentDAO.update(con, publicationComment);
        } catch (SQLException | DBException e) {
            log.error("Update publicationComment error ", e);
        }
    }

    /**
     * @param id id of entity  that need to delete
     */
    @Override
    public void delete(int id) {
        try (Connection con = DBConnection.getConnection()) {
            publicationCommentDAO.delete(con, id);
        } catch (SQLException | DBException e) {
            log.error("Delete publicationComment error ", e);
        }
    }

    /**
     * @param id id of entity that need to find
     * @return entity or throw {@link PublicationCommentNotFoundException}
     */
    @Override
    public PublicationComment findById(int id) {
        PublicationComment publicationComment = null;
        try (Connection con = DBConnection.getConnection()) {
            publicationComment = publicationCommentDAO.findById(con, id).orElseThrow(() -> new PublicationCommentNotFoundException("" + id));
        } catch (SQLException | DBException e) {
            log.error("Find by id  publicationComment error ", e);
        }
        return publicationComment;
    }

    /**
     * @return List of entities
     */
    @Override
    public List<PublicationComment> findAll() {
        List<PublicationComment> publicationComments = new ArrayList<>();
        try (Connection con = DBConnection.getConnection()) {
            publicationComments = publicationCommentDAO.findAll(con);
        } catch (SQLException | DBException e) {
            log.error("Delete publicationComment error ", e);
        }
        return publicationComments;
    }

    /**
     * @param sorting       {@link Sorting} object with attributes
     * @param publicationId id of {@link Publication}
     * @return List of entities
     */
    @Override
    public List<PublicationComment> findAllByPublicationId(Sorting sorting, int publicationId) {
        List<PublicationComment> publicationComments = new ArrayList<>();
        try (Connection con = DBConnection.getConnection()) {
            publicationComments = publicationCommentDAO.findAllByPublicationId(con, sorting, publicationId);
        } catch (SQLException | DBException e) {
            log.error("Find all comments by publication id error. ", e);
        }
        return publicationComments;
    }

    /**
     * @param publicationId id of {@link Publication}
     * @return count of commentaries associated with publicationId
     */
    @Override
    public int countAllByPublicationId(int publicationId) {
        int count = -1;
        try (Connection con = DBConnection.getConnection()) {
            count = publicationCommentDAO.countAllByPublicationId(con, publicationId);
        } catch (DBException | SQLException e) {
            log.error("countAllByPublicationId exception. ", e);
        }
        return count;
    }
}
