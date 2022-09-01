package ua.com.company.dao;

import ua.com.company.entity.BaseEntity;

import java.util.List;
import java.util.Optional;

public interface BaseDao<E extends BaseEntity> {
    void create(E e);
    void update(E e);
    void delete(E e);
    Optional<E> findById(int id);
    List<E> findAll();
}
