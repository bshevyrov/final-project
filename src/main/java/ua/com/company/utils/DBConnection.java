package ua.com.company.utils;

import org.apache.derby.jdbc.EmbeddedDriver;
import ua.com.company.dao.DAOFactory;
import ua.com.company.service.impl.PersonServiceImpl;
import ua.com.company.type.DBType;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;

import static ua.com.company.service.BaseService.log;

public class DBConnection {
   public  static Connection getConnection() {
        if (DAOFactory.getDaoFactoryFQN().equals(DBType.MYSQL.getClassName())) {
            try {
                Context envCtx = (Context) new InitialContext().lookup("java:comp/env");
                DataSource ds = (DataSource) envCtx.lookup("jdbc/MySQL");
                return ds.getConnection();
            } catch (NamingException | SQLException e) {
                log.error("NO CONNECTION TO MYSQL DB. APP SHUTDOWN", e);
                System.exit(1);
            }
        }
        if (DAOFactory.getDaoFactoryFQN().equals(DBType.DERBY.getClassName())) {
            Driver derbyEmbeddedDriver = new EmbeddedDriver();
            try {
                DriverManager.registerDriver(derbyEmbeddedDriver);

                return DriverManager.getConnection
                        ("jdbc:derby:memory:jdbc:derby:final_project;create=true");
//                        ("jdbc:derby:final_project;create=true");
            } catch (SQLException e) {
                log.error("NO CONNECTION TO DERBY DB. APP SHUTDOWN", e);
                System.exit(1);
            }
        }
        return null;
    }
}
