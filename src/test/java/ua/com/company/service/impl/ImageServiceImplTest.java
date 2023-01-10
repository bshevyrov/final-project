package ua.com.company.service.impl;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.*;
import ua.com.company.dao.ImageDAO;
import ua.com.company.entity.Image;
import ua.com.company.exception.DBException;
import ua.com.company.utils.DBConnection;

import java.sql.Connection;
import java.util.List;
import java.util.Optional;

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
    private int id;
    private String path;
    private List<Image> images;

    @BeforeAll
    public void init() {
        MockitoAnnotations.openMocks(this);
        id = 1;
        path = "path";

    }

    @Test
    void create() throws DBException {
        try (MockedStatic<DBConnection> utilities = Mockito.mockStatic(DBConnection.class)) {
            utilities.when(DBConnection::getConnection).thenReturn(con);
            doNothing().when(imageDAO).create(any(), any());
            imageService.create(new Image());
            verify(imageDAO, times(1)).create(any(), eq(new Image()));
        }
    }

    @Test
    void update() throws DBException {
        try (MockedStatic<DBConnection> utilities = Mockito.mockStatic(DBConnection.class)) {
            utilities.when(DBConnection::getConnection).thenReturn(con);
            doNothing().when(imageDAO).update(any(), any());
            imageService.update(new Image());
            verify(imageDAO, times(1)).update(any(), eq(new Image()));
        }
    }

    @Test
    void delete() throws DBException {
        try (MockedStatic<DBConnection> utilities = Mockito.mockStatic(DBConnection.class)) {
            utilities.when(DBConnection::getConnection).thenReturn(con);
            doNothing().when(imageDAO).delete(any(), anyInt());
            imageService.delete(id);
            verify(imageDAO, times(1)).delete(any(), eq(id));
        }
    }

    @Test
    void findById() throws DBException {
        try (MockedStatic<DBConnection> utilities = Mockito.mockStatic(DBConnection.class)) {
            utilities.when(DBConnection::getConnection).thenReturn(con);
            when(imageDAO.findById(any(), anyInt())).thenReturn(Optional.ofNullable(image));
            imageService.findById(id);
            verify(imageDAO, times(1)).findById(any(), eq(id));
        }
    }

    @Test
    void findAll() throws DBException {
        try (MockedStatic<DBConnection> utilities = Mockito.mockStatic(DBConnection.class)) {
            utilities.when(DBConnection::getConnection).thenReturn(con);
           when(imageDAO.findAll(any())).thenReturn(images);
            imageService.findAll();
            verify(imageDAO, times(1)).findAll(any());
        }
    }

    @Test
    void findByPath() throws DBException {
        try (MockedStatic<DBConnection> utilities = Mockito.mockStatic(DBConnection.class)) {
            utilities.when(DBConnection::getConnection).thenReturn(con);
            when(imageDAO.findByPath(any(), anyString())).thenReturn(Optional.ofNullable(image));
            imageService.findByPath(path);
            verify(imageDAO, times(1)).findByPath(any(), eq(path));
        }
    }
}
