package ua.com.company.dao;

import ua.com.company.entity.BaseEntity;
import ua.com.company.exception.DBException;

import java.sql.Connection;
import java.util.List;
import java.util.Optional;

public interface BaseDAO<T extends BaseEntity> {
    int create(Connection con, T entity) throws DBException;

    void update(Connection con, T entity) throws DBException;

    void delete(Connection con, int id) throws DBException;

    Optional<T> findById(Connection con, int id) throws DBException;

    List<T> findAll(Connection con) throws DBException;
}