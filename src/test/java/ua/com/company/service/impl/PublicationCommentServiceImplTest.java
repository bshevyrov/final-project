package ua.com.company.service.impl;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.*;
import ua.com.company.dao.PublicationCommentDAO;
import ua.com.company.entity.PublicationComment;
import ua.com.company.entity.Sorting;
import ua.com.company.exception.DBException;
import ua.com.company.utils.DBConnection;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class PublicationCommentServiceImplTest {
    @Mock
    PublicationCommentDAO publicationCommentDAO;
    @Mock
    Connection con;

    @InjectMocks
    PublicationCommentServiceImpl publicationCommentService;
    private PublicationComment publicationComment;
    private int id;
    private int count;
    private List<PublicationComment> publicationComments;
    private Sorting sorting;

    @BeforeAll
    void init() {
        MockitoAnnotations.openMocks(this);
        publicationComment = new PublicationComment();
        id = 1;
        count = 1;
        publicationComments = new ArrayList<>();
        sorting = new Sorting();
    }

    @Test
    void create() throws DBException {
        try (MockedStatic<DBConnection> utilities = Mockito.mockStatic(DBConnection.class)) {
            utilities.when(DBConnection::getConnection).thenReturn(con);
            doNothing().when(publicationCommentDAO).create(any(), any());
            publicationCommentService.create(publicationComment);
            verify(publicationCommentDAO, times(1)).create(con, publicationComment);
        }
    }

    @Test
    void update() throws DBException {
        try (MockedStatic<DBConnection> utilities = Mockito.mockStatic(DBConnection.class)) {
            utilities.when(DBConnection::getConnection).thenReturn(con);
            doNothing().when(publicationCommentDAO).update(any(), any());
            publicationCommentService.update(publicationComment);
            verify(publicationCommentDAO, times(1)).update(con, publicationComment);
        }
    }

    @Test
    void delete() throws DBException {
        try (MockedStatic<DBConnection> utilities = Mockito.mockStatic(DBConnection.class)) {
            utilities.when(DBConnection::getConnection).thenReturn(con);
            doNothing().when(publicationCommentDAO).delete(any(), anyInt());
            publicationCommentService.delete(id);
            verify(publicationCommentDAO, times(1)).delete(con, id);
        }
    }

    @Test
    void findById() throws DBException {
        try (MockedStatic<DBConnection> utilities = Mockito.mockStatic(DBConnection.class)) {
            utilities.when(DBConnection::getConnection).thenReturn(con);
            when(publicationCommentDAO.findById(any(), anyInt())).thenReturn(Optional.of(new PublicationComment()));
            publicationCommentService.findById(id);
            verify(publicationCommentDAO, times(1)).findById(con, id);
        }
    }

    @Test
    void findAll() throws DBException {
        try (MockedStatic<DBConnection> utilities = Mockito.mockStatic(DBConnection.class)) {
            utilities.when(DBConnection::getConnection).thenReturn(con);
            when(publicationCommentDAO.findAll(con)).thenReturn(publicationComments);
            publicationCommentService.findAll();
            verify(publicationCommentDAO, times(1)).findAll(con);
        }
    }

    @Test
    void findAllByPublicationId() throws DBException {
        try (MockedStatic<DBConnection> utilities = Mockito.mockStatic(DBConnection.class)) {
            utilities.when(DBConnection::getConnection).thenReturn(con);
            when(publicationCommentDAO.findAllByPublicationId(con, sorting, id)).thenReturn(publicationComments);
            publicationCommentService.findAllByPublicationId(sorting, id);
            verify(publicationCommentDAO, times(1)).findAllByPublicationId(con, sorting, id);
        }
    }

    @Test
    void countAllByPublicationId() throws DBException {
        try (MockedStatic<DBConnection> utilities = Mockito.mockStatic(DBConnection.class)) {
            utilities.when(DBConnection::getConnection).thenReturn(con);
            when(publicationCommentDAO.countAllByPublicationId(con, id)).thenReturn(count);
            publicationCommentService.countAllByPublicationId(id);
            verify(publicationCommentDAO, times(1)).countAllByPublicationId(con,id);
        }
    }
}