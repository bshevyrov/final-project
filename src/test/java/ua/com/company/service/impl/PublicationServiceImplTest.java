package ua.com.company.service.impl;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.*;
import ua.com.company.dao.ImageDAO;
import ua.com.company.dao.PublicationDAO;
import ua.com.company.dao.TopicDAO;
import ua.com.company.entity.Image;
import ua.com.company.entity.Publication;
import ua.com.company.entity.Sorting;
import ua.com.company.entity.Topic;
import ua.com.company.exception.DBException;
import ua.com.company.utils.DBConnection;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class PublicationServiceImplTest {

    @Mock
    private Connection con;
    @Mock
    TopicDAO topicDAO;
    @Mock
    ImageDAO imageDAO;

    @Mock
    PublicationDAO publicationDAO;

    @InjectMocks
    PublicationServiceImpl publicationService;
    private Publication publication;
    private Image image;
    private List<Publication> publications;
    private List<Topic> topics;
    private int id;
    private int count;
    private String name;
    private int coverId;
    private Sorting sorting;

    @BeforeAll
    void init() {
        MockitoAnnotations.openMocks(this);
        id = 1;
        publication = new Publication();
        topics = new ArrayList<>();
        publication.setTopics(topics);
        image = new Image();
        image.setId(id);
        publication.setCover(image);
        coverId = 1;
        count = 1;
        publications = new ArrayList<>();
        sorting = new Sorting();
        name = "name";

    }

    @Test
    void create() throws DBException {
        try (MockedStatic<DBConnection> utilities = Mockito.mockStatic(DBConnection.class)) {
            utilities.when(DBConnection::getConnection).thenReturn(con);
            doNothing().when(publicationDAO).create(con, publication);
            doNothing().when(imageDAO).create(any(), any());
            publicationService.create(publication);
            verify(publicationDAO, times(1)).create(con, publication);
        }
    }

    @Test
    void update() throws DBException {
        try (MockedStatic<DBConnection> utilities = Mockito.mockStatic(DBConnection.class)) {
            utilities.when(DBConnection::getConnection).thenReturn(con);
            doNothing().when(publicationDAO).update(con, publication);
            publicationService.update(publication);
            verify(publicationDAO, times(1)).update(con, publication);
        }
    }

    @Test
    void delete() throws DBException {
        try (MockedStatic<DBConnection> utilities = Mockito.mockStatic(DBConnection.class)) {
            utilities.when(DBConnection::getConnection).thenReturn(con);
            doNothing().when(publicationDAO).delete(con, id);
            publicationService.delete(id);
            verify(publicationDAO, times(1)).delete(con, id);
        }
    }

    @Test
    void findById() throws DBException {
        try (MockedStatic<DBConnection> utilities = Mockito.mockStatic(DBConnection.class)) {
            utilities.when(DBConnection::getConnection).thenReturn(con);
            when(publicationDAO.findById(con, id)).thenReturn(Optional.ofNullable(publication));
            when(imageDAO.findById(con, id)).thenReturn(Optional.ofNullable(image));
            when(topicDAO.findAllByPublicationId(con, id)).thenReturn(topics);
            publicationService.findById(id);
            verify(publicationDAO, times(1)).findById(con, id);
        }
    }

    @Test
    void findAll() throws DBException {
        try (MockedStatic<DBConnection> utilities = Mockito.mockStatic(DBConnection.class)) {
            utilities.when(DBConnection::getConnection).thenReturn(con);
            when(publicationDAO.findAll(con)).thenReturn(publications);
            publicationService.findAll();
            verify(publicationDAO, times(1)).findAll(con);
        }
    }

    @Test
    void findAllByTopicId() throws DBException {
        try (MockedStatic<DBConnection> utilities = Mockito.mockStatic(DBConnection.class)) {
            utilities.when(DBConnection::getConnection).thenReturn(con);
            when(publicationDAO.findAllByTopicId(con, sorting, id)).thenReturn(publications);
            publicationService.findAllByTopicId(sorting, id);
            verify(publicationDAO, times(1)).findAllByTopicId(con, sorting, id);
        }
    }

    @Test
    void findAllByUserId() throws DBException {
        try (MockedStatic<DBConnection> utilities = Mockito.mockStatic(DBConnection.class)) {
            utilities.when(DBConnection::getConnection).thenReturn(con);
            when(publicationDAO.findAllByPersonId(con, sorting, id)).thenReturn(publications);
            publicationService.findAllByUserId(sorting, id);
            verify(publicationDAO, times(1)).findAllByPersonId(con, sorting, id);
        }
    }

    @Test
    void findAllByTitle() throws DBException {
        try (MockedStatic<DBConnection> utilities = Mockito.mockStatic(DBConnection.class)) {
            utilities.when(DBConnection::getConnection).thenReturn(con);
            when(publicationDAO.findAllByTitle(con, sorting, name)).thenReturn(publications);
            publicationService.findAllByTitle(sorting, name);
            verify(publicationDAO, times(1)).findAllByTitle(con, sorting, name);
        }
    }

    @Test
    void countAllByTopicId() throws DBException {
        try (MockedStatic<DBConnection> utilities = Mockito.mockStatic(DBConnection.class)) {
            utilities.when(DBConnection::getConnection).thenReturn(con);
            when(publicationDAO.countAllByTopicId(con, id)).thenReturn(count);
            publicationService.countAllByTopicId(id);
            verify(publicationDAO, times(1)).countAllByTopicId(con, id);
        }
    }

    @Test
    void countAllByUserId() throws DBException {
        try (MockedStatic<DBConnection> utilities = Mockito.mockStatic(DBConnection.class)) {
            utilities.when(DBConnection::getConnection).thenReturn(con);
            when(publicationDAO.countAllByUserId(con, id)).thenReturn(count);
            publicationService.countAllByUserId(id);
            verify(publicationDAO, times(1)).countAllByUserId(con, id);
        }
    }

    @Test
    void countAllByTitle() throws DBException {
        try (MockedStatic<DBConnection> utilities = Mockito.mockStatic(DBConnection.class)) {
            utilities.when(DBConnection::getConnection).thenReturn(con);
            when(publicationDAO.countAllByTitle(con, name)).thenReturn(count);
            publicationService.countAllByTitle(name);
            verify(publicationDAO, times(1)).countAllByTitle(con, name);
        }
    }

    @Test
    void updateCover() throws DBException {
        try (MockedStatic<DBConnection> utilities = Mockito.mockStatic(DBConnection.class)) {
            utilities.when(DBConnection::getConnection).thenReturn(con);
            doNothing().when(publicationDAO).updateCover(con, id, coverId);
            publicationService.updateCover(id, coverId);
            verify(publicationDAO, times(1)).updateCover(con, id, coverId);
        }
    }
}