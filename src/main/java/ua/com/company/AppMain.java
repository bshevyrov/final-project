package ua.com.company;


import ua.com.company.dao.PersonDAO;
import ua.com.company.dao.PublicationDAO;
import ua.com.company.dao.TopicDAO;
import ua.com.company.dao.mysql.impl.MysqlPersonDAOImpl;
import ua.com.company.dao.mysql.impl.MysqlPublicationDAOImpl;
import ua.com.company.dao.mysql.impl.MysqlTopicDAOImpl;
import ua.com.company.entity.Person;
import ua.com.company.entity.Publication;
import ua.com.company.entity.Topic;
import ua.com.company.exception.DBException;
import ua.com.company.type.RoleType;

import java.util.List;

public class AppMain {

    public static void main(String[] args) {

       /* System.out.println("Hello World!");
        System.out.println(PasswordUtil.encryptPassword("111"));
        System.out.println(PasswordUtil.validatePassword("111","65535:ee2371f001922a7be621d1f1598465f6:dd8ee816129b683221aeeac1ffa67682"));

        AdminDaoImpl adminDao = new AdminDaoImpl();
        System.out.println(adminDao.findAll());*/

//        PersonDAO personDAO = MysqlDAOFactory.getInstance().getPersonDAO();
//        PublicationDAO publicationDAO =





  /*      Logger log = LoggerFactory.getLogger(AppMain.class);
        log.info("APP");
        PublicationDAO publicationDao = new MysqlPublicationDAOImpl();
        Publication p = new Publication();
        p.setTitle("title");
        p.setPrice(9.9);
        p.setSample("sample");
        Image image = new Image();
        image.setName("Iname");
        image.setPath("path");
        publicationDao.create(p, image);*/


//        2.58
    }

    private static void testTopic() throws DBException {
        TopicDAO topicDao = new MysqlTopicDAOImpl();
        Topic topic = new Topic();
        topic.setTitle("SCI-FI");

        topicDao.create(topic);
        try {
            System.out.println(topicDao.findAll().get(0));
        } catch (DBException e) {
            e.printStackTrace();
        }
        try {
            topic = topicDao.findAll().get(0);
        } catch (DBException e) {
            e.printStackTrace();
        }
        topic.setTitle("HEALTH");
        try {
            topicDao.update(topic);
        } catch (DBException e) {
            e.printStackTrace();
        }
        try {
            System.out.println(topicDao.findById(topic.getId()));
        } catch (DBException e) {
            e.printStackTrace();
        }
        topicDao.delete(topic.getId());
        System.out.println("f" + topicDao.findAll().size());
    }

    private static void testPublication() {
        PublicationDAO publicationDao = new MysqlPublicationDAOImpl();
        Publication p = new Publication();
        p.setTitle("Title One");
        p.setDescription("Sample One");
        p.setPrice(5.99);
        publicationDao.create(p);
        try {
            System.out.println(publicationDao.findAll().toString());
        } catch (DBException e) {
            e.printStackTrace();
        }
        try {
            p = publicationDao.findAllByTitle("Title One").get(0);
        } catch (DBException e) {
            e.printStackTrace();
        }

        p.setTitle("Title two");
        try {
            publicationDao.update(p);
        } catch (DBException e) {
            e.printStackTrace();
        }
        System.out.println("p = " + p);
        publicationDao.delete(p.getId());

        try {
            System.out.println(publicationDao.findById(p.getId()));
        } catch (DBException e) {
            e.printStackTrace();
        }
        try {
            System.out.println(publicationDao.findAll().size());
        } catch (DBException e) {
            e.printStackTrace();
        }
    }

    private static void testPerson() throws DBException {
        PersonDAO personDao = new MysqlPersonDAOImpl();
        Person p = new Person("22@mail.ru", "111", RoleType.ROLE_CUSTOMER);
        personDao.create(p);

        p = personDao.findPersonByEmail("22@mail.ru").get();

        System.out.println(p);

        p.setId(1);
        p.setEmail("33@mail.ru");

        try {
            personDao.update(p);
        } catch (DBException e) {
            e.printStackTrace();
        }

        List<Person> people = personDao.findAll();
        System.out.println(people.toString());

        personDao.delete(p.getId());
        people = personDao.findAll();
        System.out.println(people.toString());
    }
}
