package ua.com.company.dao;

import ua.com.company.entity.Image;
import ua.com.company.exception.DBException;

import java.sql.Connection;
import java.util.Optional;

public interface ImageDAO extends BaseDAO<Image> {
    Optional<Image> findByPath(Connection con, String path) throws DBException;

    Optional<Image> findByPublicationId(Connection con, int id) throws DBException;
}
