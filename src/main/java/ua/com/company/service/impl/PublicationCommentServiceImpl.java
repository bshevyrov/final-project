package ua.com.company.service.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.com.company.dao.DAOFactory;
import ua.com.company.dao.PublicationCommentDAO;
import ua.com.company.entity.PublicationComment;
import ua.com.company.entity.Sorting;
import ua.com.company.exception.DBException;
import ua.com.company.service.PublicationCommentService;

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
        try (Connection con = getConnection()) {
            publicationCommentDAO.create(con, publicationComment);
        } catch (SQLException | DBException e) {
            log.error("Create publicationComment error " + publicationComment, e);
        }
    }

    @Override
    public void update(PublicationComment publicationComment) {

    }

    @Override
    public void delete(int id) {

    }

    @Override
    public PublicationComment findById(int id) {
        return null;
    }

    @Override
    public List<PublicationComment> findAll() {
        return null;
    }

    @Override
    public List<PublicationComment> findAllByPublicationId(Sorting sorting, int publicationId) {
        List<PublicationComment> publicationComments = new ArrayList<>();
        try (Connection con = getConnection()) {
            publicationComments = publicationCommentDAO.findAllByPublicationId(con, sorting, publicationId);
        } catch (SQLException | DBException e) {
            log.error("Find all comments by publication id error. Sorting= " + sorting + " publicationId= " + publicationId, e);
        }
        return publicationComments;
    }

    @Override
    public int countAllByPublicationId(int publicationId) {
        int count = -1;
        try (Connection con = getConnection()) {
            count = publicationCommentDAO.countAllByPublicationId(con, publicationId);
        } catch (DBException | SQLException e) {
            log.error("countAllByPublicationId exception. PublicationId=  "+publicationId, e);
        }
        return count;
    }
}
