/*
package ua.com.company.config.impl;

import com.mysql.cj.jdbc.MysqlConnectionPoolDataSource;
import com.mysql.cj.jdbc.MysqlDataSource;
import ua.com.company.config.DBDatasource;

import javax.sql.DataSource;
import java.io.*;
import java.util.Properties;

public class DBDataSourceImpl implements DBDatasource {
//    private static final String PATH_TO_APP_PROPERTIES = "src/main/resources/db.properties";
    private static final InputStream PATH_TO_APP_PROPERTIES = DBDataSourceImpl.class.getClassLoader()
        .getResourceAsStream("db.properties");
    private static final Properties properties = new Properties();
    private static  DBDataSourceImpl instance;

    private DBDataSourceImpl() {
    }

    public static synchronized DBDataSourceImpl getInstance() {
        if (instance == null) {
            return new DBDataSourceImpl();
        }
        return instance;
    }


    public DataSource getDataSource() {
       if(properties.isEmpty()){

           try {

               properties.load(PATH_TO_APP_PROPERTIES);

           } catch (IOException e) {
               e.printStackTrace();
           }
       }
        MysqlDataSource mysqlDataSource = new MysqlDataSource();
        mysqlDataSource.setURL(properties.getProperty("MYSQL_DB_URL"));
        mysqlDataSource.setUser(properties.getProperty("MYSQL_DB_USERNAME"));
        mysqlDataSource.setPassword(properties.getProperty("MYSQL_DB_PASSWORD"));

        return mysqlDataSource;
    }
    public DataSource getPooledDataSource() {
        Properties properties = new Properties();
        try {
            properties.load(PATH_TO_APP_PROPERTIES);
        } catch (IOException e) {
            e.printStackTrace();
        }
        MysqlDataSource mysqlDataSource = new MysqlConnectionPoolDataSource();
        mysqlDataSource.setURL(properties.getProperty("MYSQL_DB_URL"));
        mysqlDataSource.setUser(properties.getProperty("MYSQL_DB_USERNAME"));
        mysqlDataSource.setPassword(properties.getProperty("MYSQL_DB_PASSWORD"));


        return mysqlDataSource;
    }


}
*/
