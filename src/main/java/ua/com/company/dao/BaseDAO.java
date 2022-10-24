package ua.com.company.dao;

import ua.com.company.entity.BaseEntity;
import ua.com.company.entity.Topic;
import ua.com.company.exception.DBException;
import ua.com.company.listener.AppContextListener;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface BaseDAO<T extends BaseEntity> {
    int create(Connection con, T entity) throws DBException, SQLException;

    void update(Connection con,T entity) throws DBException;

    void delete(Connection con,int id) throws DBException;

    Optional<T> findById(Connection con,int id) throws DBException;

    List<T> findAll(Connection con) throws DBException;
    default void close(AutoCloseable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
/*
    default  Connection getConnection() throws DBException {
        Context initCtx;
        try {
            initCtx = new InitialContext();
            Context envCtx = (Context) initCtx.lookup("java:comp/env");
            DataSource ds = (DataSource) envCtx.lookup("jdbc/MySQL");
//            String FQN = String.valueOf(DriverManager.getDriver(ds.getConnection().getMetaData().getURL()).getClass());
//            DAOFactory.setDaoFactoryFQN(FQN);
//            DAOFactory.setDaoFactoryFQN( "com.mysql.cj.jdbc.Driver");
            return ds.getConnection();
        } catch (NamingException | SQLException e) {
            e.printStackTrace();
            throw new DBException(e);
        }
           Connection con;
     try {
            con = AppContextListener.initializeDao().getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DBException(e);
        }
        return con;
    }*/
//    https://elearn.epam.com/courses/course-v1:RD_CEE+OnlineUAJava+2022-Spring/courseware/5ecd5c2b45a249169c2af0fbecc2a0d8/b302e28df8c34c37a410f36182128255/3?activate_block_id=block-v1%3ARD_CEE%2BOnlineUAJava%2B2022-Spring%2Btype%40vertical%2Bblock%40f6d242f4e80b4bf6aef3def55c70950e
//2;35
}