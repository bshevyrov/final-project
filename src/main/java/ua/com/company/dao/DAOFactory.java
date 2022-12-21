package ua.com.company.dao;

import ua.com.company.type.DBType;

import java.lang.reflect.InvocationTargetException;

public abstract class DAOFactory {
    private static DAOFactory instance;

    public static synchronized DAOFactory getInstance() {
        if (instance == null) {
            Class<?> clazz;
            try {
                clazz = Class.forName(DAOFactory.daoFactoryFQN);

                instance = (DAOFactory) clazz.getDeclaredConstructor().newInstance();
            } catch (ClassNotFoundException | InvocationTargetException | InstantiationException |
                     IllegalAccessException | NoSuchMethodException e) {
                e.printStackTrace();
                //TODO LOG
                //exit db not valid
            }
        }
        return instance;
    }

    protected DAOFactory() {
    }

    private static String daoFactoryFQN;

    public static void setDaoFactoryFQN(DBType dbType) {
        instance = null;
        DAOFactory.daoFactoryFQN = dbType.getClassName();
    }

    public static String getDaoFactoryFQN() {
        return daoFactoryFQN;
    }

    public abstract PersonDAO getPersonDAO();

    public abstract PublicationDAO getPublicationDAO();

    public abstract PublicationCommentDAO getPublicationCommentDAO();

    public abstract TopicDAO getTopicDAO();

    public abstract ImageDAO getImageDAO();

    public abstract PersonAddressDAO getPersonAddressDAO();
}
