package ua.com.company.type;

import ua.com.company.dao.derby.DerbyDAOFactory;
import ua.com.company.dao.mysql.MysqlDAOFactory;

public enum DBType {
    MYSQL(MysqlDAOFactory.class.getName()), DERBY(DerbyDAOFactory.class.getName());
    private final String className;

    DBType(String className) {
        this.className = className;
    }

    public String getClassName() {
        return className;
    }
}
