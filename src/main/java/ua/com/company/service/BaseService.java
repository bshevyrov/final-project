package ua.com.company.service;

import org.apache.derby.jdbc.EmbeddedDriver;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.com.company.dao.DAOFactory;
import ua.com.company.entity.BaseEntity;
import ua.com.company.type.DBType;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

public interface BaseService<E extends BaseEntity> {
    Logger log = LogManager.getLogger(BaseService.class);

    void create(E e);

    void update(E e);

    void delete(int id);

    E findById(int id);

    List<E> findAll();


    default void close(AutoCloseable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (Exception e) {
                log.warn("Close problem: ", e);
            }
        }
    }

    default void rollback(Connection con) {
        try {
            con.rollback();
        } catch (SQLException ex) {
            log.error("Rollback trouble: ", ex);
        }
    }
}
