package ua.com.company.dao;

import ua.com.company.type.DBType;

public abstract class DAOFactory {
    //3:22
//    https://ru.stackoverflow.com/questions/534987/daofactory-%D0%BF%D0%B5%D1%80%D0%B5%D0%B4%D0%B0%D1%87%D0%B0-%D1%81%D0%BE%D0%B5%D0%B4%D0%BD%D0%B8%D0%B5%D0%BD%D0%B8%D1%8F
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
//        DAOFactory.daoFactoryFQN = daoFactoryFQN;
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
}
