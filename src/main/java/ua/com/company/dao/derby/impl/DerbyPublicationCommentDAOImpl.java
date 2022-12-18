package ua.com.company.dao.derby.impl;

import ua.com.company.dao.PublicationCommentDAO;
import ua.com.company.entity.PublicationComment;
import ua.com.company.entity.Sorting;
import ua.com.company.exception.DBException;

import java.sql.Connection;
import java.util.List;
import java.util.Optional;

public class DerbyPublicationCommentDAOImpl implements PublicationCommentDAO {
    @Override
    public void create(Connection con, PublicationComment entity) throws DBException {

    }

    @Override
    public void update(Connection con, PublicationComment entity) throws DBException {

    }

    @Override
    public void delete(Connection con, int id) throws DBException {

    }

    @Override
    public Optional<PublicationComment> findById(Connection con, int id) throws DBException {
        return Optional.empty();
    }

    @Override
    public List<PublicationComment> findAll(Connection con) throws DBException {
        return null;
    }

    @Override
    public List<PublicationComment> findAllCommentsByPublicationId(Connection con, Sorting sorting, int publicationId) throws DBException {
        return null;
    }
}
