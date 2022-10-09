package ua.com.company.service;

import ua.com.company.config.DBDatasource;
import ua.com.company.entity.BaseEntity;
import ua.com.company.exception.DBException;
import ua.com.company.exception.PublicationNotFoundException;
import ua.com.company.exception.TopicNotFoundException;
import ua.com.company.exception.UserNotFoundException;

import java.sql.Connection;
import java.util.List;

public interface BaseService<E extends BaseEntity> {
    void create(E e);
    void update(E e) ;
    void delete(int id);
    E findById (int id) throws TopicNotFoundException, PublicationNotFoundException, UserNotFoundException;
    List<E> findAll() ;


}
