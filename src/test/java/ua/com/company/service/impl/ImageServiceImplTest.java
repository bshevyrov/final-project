package ua.com.company.service.impl;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.*;
import ua.com.company.dao.DAOFactory;
import ua.com.company.dao.ImageDAO;
import ua.com.company.entity.Image;
import ua.com.company.exception.DBException;
import ua.com.company.service.BaseService;
import ua.com.company.type.DBType;
import ua.com.company.utils.DBConnection;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class ImageServiceImplTest {


@Mock
    Connection con;

    @Mock
    private ImageDAO imageDAO;


@InjectMocks
    private ImageServiceImpl imageService;

    private Image image;

    @BeforeAll
    public void init(){
        MockitoAnnotations.openMocks(this);

    }
    @Test
    void getInstance() {
    }

    @Test
    void create() throws NamingException, SQLException {

        image = new Image();
        image.setName("Test img");
        image.setPath("Test path");

            try (MockedStatic<DBConnection> utilities = Mockito.mockStatic(DBConnection.class)) {
            utilities.when(DBConnection::getConnection).thenReturn(con);
            imageService.create(image);

            try {

                verify(imageDAO, times(1)).create(any(), eq(image));
            } catch (DBException e) {
                e.printStackTrace();
            }

        }
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
