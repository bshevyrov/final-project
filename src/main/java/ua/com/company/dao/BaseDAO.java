package ua.com.company.dao;

import ua.com.company.entity.BaseEntity;
import ua.com.company.exception.DBException;

import java.util.List;
import java.util.Optional;

public interface BaseDAO<T extends BaseEntity> {
    void create(T entity);

    void update(T entity) throws DBException;

    void delete(int id);

    Optional<T> findById(int id) throws DBException;

    List<T> findAll() throws DBException;

    default void close(AutoCloseable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
//    https://elearn.epam.com/courses/course-v1:RD_CEE+OnlineUAJava+2022-Spring/courseware/5ecd5c2b45a249169c2af0fbecc2a0d8/b302e28df8c34c37a410f36182128255/3?activate_block_id=block-v1%3ARD_CEE%2BOnlineUAJava%2B2022-Spring%2Btype%40vertical%2Bblock%40f6d242f4e80b4bf6aef3def55c70950e
//2;35
}