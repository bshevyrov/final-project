package ua.com.company.service.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.com.company.dao.DAOFactory;
import ua.com.company.dao.PublicationCommentDAO;
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
    private final PublicationCommentDAO publicationCommentDAO = DAOFactory.getInstance().getPublicationCommentDAO();
    private static PublicationCommentServiceImpl instance;

    public static PublicationCommentService getInstance() {
        if (instance == null) {
            instance = new PublicationCommentServiceImpl();
        }
        return instance;
    }

    @Override
    public void create(PublicationComment publicationComment) {
        try (Connection con = DBConnection.getConnection()) {
            publicationCommentDAO.create(con, publicationComment);
        } catch (SQLException | DBException e) {
            log.error("Create publicationComment error ", e);
        }
    }

    @Override
    public void update(PublicationComment publicationComment) {
        try (Connection con = DBConnection.getConnection()) {
            publicationCommentDAO.update(con, publicationComment);
        } catch (SQLException | DBException e) {
            log.error("Update publicationComment error ", e);
        }
    }

    @Override
    public void delete(int id) {
        try (Connection con = DBConnection.getConnection()) {
            publicationCommentDAO.delete(con, id);
        } catch (SQLException | DBException e) {
            log.error("Delete publicationComment error ", e);
        }
    }

    @Override
    public PublicationComment findById(int id) {
        PublicationComment publicationComment = null;
        try (Connection con = DBConnection.getConnection()) {
            publicationComment = publicationCommentDAO.findById(con, id).orElseThrow(() -> new PublicationCommentNotFoundException("" + id));
        } catch (SQLException | DBException e) {
            log.error("Find by id  publicationComment error ", e);
        } catch (PublicationCommentNotFoundException e) {
            log.warn("Publication comment not found. id= " + id, e);
        }
        return publicationComment;
    }

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
