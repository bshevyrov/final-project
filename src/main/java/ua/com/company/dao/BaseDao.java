package ua.com.company.dao;

import ua.com.company.entity.BaseEntity;
import ua.com.company.exception.DBException;

import java.util.List;
import java.util.Optional;

public interface BaseDao<E extends BaseEntity> {
    void create(E e);
    void update(E e) throws DBException;
    void delete(int id);
    Optional<E> findById(int id) throws DBException;
    List<E> findAll() throws DBException;

     default void close(AutoCloseable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
