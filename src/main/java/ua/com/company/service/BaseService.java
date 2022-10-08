package ua.com.company.service;

import ua.com.company.config.DBDatasource;
import ua.com.company.entity.BaseEntity;
import ua.com.company.exception.DBException;

import java.sql.Connection;
import java.util.List;

public interface BaseService<E extends BaseEntity> {
    void create(E e);
    void update(E e) throws DBException;
    void delete(int id);
    E findById (int id) throws DBException;
    List<E> findAll() throws DBException;


}
