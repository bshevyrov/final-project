package ua.com.company.dao;

import ua.com.company.entity.Image;
import ua.com.company.exception.DBException;

import java.util.Optional;

public interface ImageDAO extends BaseDAO<Image> {
    Optional<Image> findByPublicationId(int id) throws DBException;
}
