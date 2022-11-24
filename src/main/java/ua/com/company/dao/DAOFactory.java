package ua.com.company.dao;

import ua.com.company.type.DBType;

public abstract class DAOFactory {
    private static DAOFactory instance;

    public static synchronized DAOFactory getInstance() throws Exception {
        if (instance == null) {
            Class<?> clazz = Class.forName(DAOFactory.daoFactoryFQN);
            instance = (DAOFactory) clazz.getDeclaredConstructor().newInstance();
        }
        return instance;
    }

    protected DAOFactory() {
    }

    private static String daoFactoryFQN;

    public static void setDaoFactoryFQN(DBType dbType) {
        instance = null;
        switch (dbType.name()) {
            case ("MYSQL"):
                DAOFactory.daoFactoryFQN = dbType.getClassName();
                break;
            default:
                break;
        }
    }

    public abstract PersonDAO getPersonDAO();

    public abstract PublicationDAO getPublicationDAO();

    public abstract TopicDAO getTopicDAO();

    public abstract ImageDAO getImageDAO();
}
