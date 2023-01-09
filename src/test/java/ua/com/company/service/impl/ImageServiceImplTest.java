package ua.com.company.service.impl;

import org.junit.jupiter.api.*;
import ua.com.company.dao.DAOFactory;
import ua.com.company.entity.Image;
import ua.com.company.service.ImageService;
import ua.com.company.type.DBType;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class ImageServiceImplTest {
    private static Connection con = null;
    private ImageService imageService;

    public static final String dropSQL = "DROP TABLE image";


    public static final String createSQL = "CREATE TABLE image (" +
            "id INT not null primary key GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1)," +
            " name VARCHAR(256) NOT NULL," +
            " create_date TIMESTAMP  DEFAULT CURRENT_TIMESTAMP," +
            " update_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP ," +

            " path VARCHAR(256) NOT NULL/*," +
            " PRIMARY KEY (id)*/)";

    @BeforeAll
    static void init() {
        DAOFactory.setDaoFactoryFQN(DBType.DERBY);
    }

    @BeforeAll
    public void createDB() throws SQLException {
        imageService = ImageServiceImpl.getInstance();
        con = imageService.getConnection();
        Statement stmt;
        stmt = con.createStatement();
        stmt.execute(createSQL);
    }

    @AfterAll
    public void cleanDB() throws SQLException {
        con = imageService.getConnection();
        Statement stmt;
        stmt = con.createStatement();
        stmt.execute(dropSQL);
    }

/*
    ImageServiceImpl imageService = mock(ImageServiceImpl.class);

    MysqlImageDAOImpl dao = mock(MysqlImageDAOImpl.class);
    DAOFactory daoFactory = mock(DAOFactory.class);
@BeforeAll
void setUp(){
    try {
        Field field = imageService.getClass().getDeclaredField("imageDAO");

                field.setAccessible(true);
                field.set(imageService,dao);
            } catch (NoSuchFieldException  | IllegalAccessException e) {
        e.printStackTrace();
    }
}*/


    @Test
    void getInstance() {
    }

    @Test
    void create() {

        Image image = new Image();
        image.setName("Test img");
        image.setPath("Test path");
        imageService.create(image);
        Image actual = imageService.findAll().get(0);
        Assertions.assertEquals(1, imageService.findAll().size(), "expected 1, get " + imageService.findAll().size());
        Assertions.assertEquals(image, actual, "expected " + image + " actual " + actual);


/*
//        DAOFactory.setDaoFactoryFQN(DBType.MYSQL);
//        try (MockedStatic<ImageServiceImpl> cachedImageMock = mockStatic(ImageServiceImpl.class);
//                MockedStatic<DAOFactory> cachedDaoMock = mockStatic(DAOFactory.class))
//                {
//            cachedImageMock.when(ImageServiceImpl::getInstance).thenReturn(imageService);
//
////            cachedDaoMock.when(DAOFactory::getInstance).thenReturn(daoFactory);
//            when(daoFactory.getImageDAO()).thenReturn(dao);
            doNothing().when(imageService).create(any());
            imageService.create(emp);
            System.out.println(imageService.findAll());
            try {
            verify(dao, times(1)).create(any(), eq(emp));
        } catch (DBException e) {
            e.printStackTrace();
        }
*/

    }

    @Test
    void update() {
    }

    @Test
    void delete() {
    }

    @Test
    void findById() {
    }

    @Test
    void findAll() {
    }

    @Test
    void findByPath() {
    }
}