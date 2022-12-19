package ua.com.company.dao;

import ua.com.company.entity.PublicationComment;
import ua.com.company.entity.Sorting;
import ua.com.company.exception.DBException;

import java.sql.Connection;
import java.util.List;

public interface PublicationCommentDAO extends BaseDAO<PublicationComment>{
     List<PublicationComment> findAllByPublicationId(Connection con, Sorting sorting, int publicationId) throws DBException;
}
