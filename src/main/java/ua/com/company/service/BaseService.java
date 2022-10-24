package ua.com.company.service;

import ua.com.company.entity.BaseEntity;
import ua.com.company.exception.DBException;
import ua.com.company.listener.AppContextListener;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface BaseService<E extends BaseEntity> {
    int create(E e);

    void update(E e);

    void delete(int id);

    E findById(int id);

    List<E> findAll();


    default void close(AutoCloseable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    default void rollback(Connection con) {
        try {
            con.rollback();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    default Connection getConnection() throws DBException {
        Context initCtx;
        try {
            initCtx = new InitialContext();
            Context envCtx = (Context) initCtx.lookup("java:comp/env");
            DataSource ds = (DataSource) envCtx.lookup("jdbc/MySQL");

            return ds.getConnection();
        } catch (NamingException | SQLException e) {
            e.printStackTrace();
            throw new DBException(e);
        }

    }


}
