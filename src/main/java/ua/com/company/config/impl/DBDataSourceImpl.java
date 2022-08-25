package ua.com.company.config.impl;

import com.mysql.cj.jdbc.MysqlDataSource;
import ua.com.company.config.DBDatasource;

import javax.sql.DataSource;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class DBDataSourceImpl implements DBDatasource {
    private static final String PATH_TO_APP_PROPERTIES = "src/main/resources/db.properties";
    private static DBDataSourceImpl instance;

    private DBDataSourceImpl() {
    }

    public static synchronized DBDataSourceImpl getInstance() {
        if (instance == null) {
            return new DBDataSourceImpl();
        }
        return instance;
    }


    public DataSource getDataSource(){
        Properties properties = new Properties();
        try {
            properties.load(new FileInputStream(PATH_TO_APP_PROPERTIES));
        } catch (IOException e) {
            e.printStackTrace();
        }
        MysqlDataSource mysqlDataSource = new MysqlDataSource();
      mysqlDataSource.setURL(properties.getProperty("MYSQL_DB_URL"));
      mysqlDataSource.setUser(properties.getProperty("MYSQL_DB_USERNAME"));
      mysqlDataSource.setPassword(properties.getProperty("MYSQL_DB_PASSWORD"));


        return mysqlDataSource;
    }


}
