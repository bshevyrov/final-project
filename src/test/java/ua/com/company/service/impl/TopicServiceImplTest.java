package ua.com.company.service.impl;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.*;
import ua.com.company.dao.TopicDAO;
import ua.com.company.entity.Topic;
import ua.com.company.exception.DBException;
import ua.com.company.utils.DBConnection;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class TopicServiceImplTest {
    @Mock
    TopicDAO topicDAO;

    @Mock
    Connection con;

    @InjectMocks
    TopicServiceImpl topicService;

    private List<Topic> topics;
    private Topic topic;
    private int id;
    private String name;

    @BeforeAll
    void init(){
        MockitoAnnotations.openMocks(this);
        topics = new ArrayList<>();
        id = 1;
        name="name";
        topic = new Topic();
    }

    @Test
    void create() throws DBException {
        try(MockedStatic<DBConnection> utilities = Mockito.mockStatic(DBConnection.class)){
            utilities.when(DBConnection::getConnection).thenReturn(con);
            doNothing().when(topicDAO).create(con,topic);
            topicService.create(topic);
            verify(topicDAO,times(1)).create(con,topic);
        }
    }

    @Test
    void update() throws DBException {
        try(MockedStatic<DBConnection> utilities = Mockito.mockStatic(DBConnection.class)){
            utilities.when(DBConnection::getConnection).thenReturn(con);
            doNothing().when(topicDAO).update(con,topic);
            topicService.update(topic);
            verify(topicDAO,times(1)).update(con,topic);
        }
    }

    @Test
    void delete() throws DBException {
        try(MockedStatic<DBConnection> utilities = Mockito.mockStatic(DBConnection.class)){
            utilities.when(DBConnection::getConnection).thenReturn(con);
            doNothing().when(topicDAO).delete(con,id);
            topicService.delete(id);
            verify(topicDAO,times(1)).delete(con,id);
        }
    }

    @Test
    void findById() throws DBException {
        try(MockedStatic<DBConnection> utilities = Mockito.mockStatic(DBConnection.class)){
            utilities.when(DBConnection::getConnection).thenReturn(con);
            when(topicDAO.findById(con,id)).thenReturn(Optional.ofNullable(topic));
            topicService.findById(id);
            verify(topicDAO,times(1)).findById(con,id);
        }
    }

    @Test
    void findAll() throws DBException {
        try(MockedStatic<DBConnection> utilities = Mockito.mockStatic(DBConnection.class)){
            utilities.when(DBConnection::getConnection).thenReturn(con);
            when(topicDAO.findAll(con)).thenReturn(topics);
            topicService.findAll();
            verify(topicDAO,times(1)).findAll(con);
        }
    }

    @Test
    void findAllByPublicationId() throws DBException {
        try(MockedStatic<DBConnection> utilities = Mockito.mockStatic(DBConnection.class)){
            utilities.when(DBConnection::getConnection).thenReturn(con);
            when(topicDAO.findAllByPublicationId(con,id)).thenReturn(topics);
            topicService.findAllByPublicationId(id);
            verify(topicDAO,times(1)).findAllByPublicationId(con,id);
        }
    }

    @Test
    void findByTitle() throws DBException {
        try(MockedStatic<DBConnection> utilities = Mockito.mockStatic(DBConnection.class)){
            utilities.when(DBConnection::getConnection).thenReturn(con);
            when(topicDAO.findByTitle(con,name)).thenReturn(Optional.ofNullable(topic));
            topicService.findByTitle(name);
            verify(topicDAO,times(1)).findByTitle(con,name);
        }
    }
}