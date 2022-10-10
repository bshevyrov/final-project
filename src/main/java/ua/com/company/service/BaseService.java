package ua.com.company.service;

import ua.com.company.entity.BaseEntity;

import java.util.List;

public interface BaseService<E extends BaseEntity> {
    int create(E e);

    void update(E e);

    void delete(int id);

    E findById(int id);

    List<E> findAll();


}
