package ua.com.company.service.impl;

import org.junit.jupiter.api.*;
import ua.com.company.dao.TopicDAO;
import ua.com.company.entity.Topic;
import ua.com.company.service.TopicService;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

//@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
//@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class TopicServiceImplTest {
//
//    private static Connection conn = null;
//
//    public static final String dropSQL = "DROP TABLE topic";
//    public static final String createSQL = "CREATE TABLE  topic" +
//            "(" +
//            "    id    INT          NOT NULL GENERATED ALWAYS AS IDENTITY(Start with 1, Increment by 1)," +
//            "    title VARCHAR(128) NOT NULL," +
//            "    PRIMARY KEY (id)" +
//            ")";
//    private AutoCloseable closeable;
//
//
//    @BeforeAll
//    static void init()  {
//        DAOFactory.setDaoFactoryFQN(DBType.DERBY);
//    }
//
//
//    @BeforeAll
//    public void openMocks() {
//        closeable = MockitoAnnotations.openMocks(this);
//    }
//
//    @AfterAll
//    public void releaseMocks() throws Exception {
//        closeable.close();
//    }
//
//    @BeforeAll
//    public void createDB() throws SQLException {
//        conn = topicService.getConnection();
//        Statement stmt;
////        conn.setAutoCommit(false);
//        stmt = conn.createStatement();
////        stmt.execute(dropSQL);
//        stmt.execute(createSQL);
//    }
//
//    @AfterAll
//    public void cleanDB() throws SQLException {
//        conn = topicService.getConnection();
//        Statement stmt;
////        conn.setAutoCommit(false);
//        stmt = conn.createStatement();
//        stmt.execute(dropSQL);
//    }
//    /*

//    @Mock
//    private DerbyTopicDAOImpl topicDAO;
//    @InjectMocks
//    private TopicServiceImpl topicService;



    @Test
    public void test11() throws Exception {
        TopicDAO topicDAO = mock(TopicDAO.class);


        TopicService topicService  = mock(TopicService.class);
//        Field instance = topicService.getClass().getField("topicDAO");
//        instance.setAccessible(true);
//        instance.set(instance,topicDAO);

        Topic topic = new Topic("ee");

        doNothing().when(topicDAO).create(any(),eq(topic));

        topicService.create(topic);
        verify(topicDAO,times(1)).create(any(),eq(topic));

    }
//
//    @Test
//    public void abc_savesObject() throws SQLException, DBException, NoSuchFieldException, IllegalAccessException {
//
//        Topic topic = new Topic();
//        topic.setTitle("Test Topic");
//
//        Field instance = TopicServiceImpl.class.getDeclaredField("instance");
//        instance.setAccessible(true);
//        instance.set(instance,topicDAO);
//
//
//        topicService.create(topic);
//
//
//        verify(topicDAO, times(1)).create(any(),eq( topic));
//    }
//
//    @Test
//    @Order(0)
//    void whenCreateThanOk() {
//        Topic topic = new Topic();
//        topic.setTitle("Create Topic");
//        topicService.create(topic);
//        Topic actual = topicService.findAll().get(0);
//        Assertions.assertEquals(1, topicService.findAll().size(), "expected 1, get " + topicService.findAll().size());
//        Assertions.assertEquals(topic, actual, "expected " + topic + " actual " + actual);
//    }
//
//    @Test
//    @Order(1)
//    void whenCreateNULLThanOk() {
//        Assertions.assertThrows(NullPointerException.class, () -> topicService.create(null));
//    }
//
//
//    @Test
//    @Order(2)
//    void whenUpdateThenOk() {
//        Topic current = topicService.findAll().get(0);
//        current.setTitle("Update Topic");
//        topicService.update(current);
//        Topic topic = new Topic();
//        topic.setTitle("Create Topic");
//        topicService.create(topic);
//        Topic actual = topicService.findAll().get(1);
//
//        Assertions.assertEquals(2, topicService.findAll().size(), "expected 2, get " + topicService.findAll().size());
//        Assertions.assertEquals(topic, actual, "expected " + topic + " actual " + actual);
//
//    }
//
//    @Test
//    void delete() {
//    }
//
//    @Test
//    void findById() {
//    }
//
//    @Test
//    void findAll() {
//    }
//
//    @Test
//    void isExistByTitle() {
//    }
//
//    @Test
//    void findAllByPublicationId() {
//    }
//
//    @Test
//    void findByTitle() {
//    }

}

class Inn extends Topic {
    private String field = "field";
}