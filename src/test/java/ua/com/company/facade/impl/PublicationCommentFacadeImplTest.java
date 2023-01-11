package ua.com.company.facade.impl;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import ua.com.company.entity.PublicationComment;
import ua.com.company.entity.Sorting;
import ua.com.company.service.PublicationCommentService;
import ua.com.company.view.dto.PublicationCommentDTO;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class PublicationCommentFacadeImplTest {
    @Mock
    PublicationCommentService publicationCommentService;
    @InjectMocks
    PublicationCommentFacadeImpl publicationCommentFacade;

    private PublicationComment publicationComment;
    private PublicationCommentDTO publicationCommentDTO;
    private List<PublicationComment> publicationComments;
    private Sorting sorting;
    private int id;

    @BeforeAll
    void init() {
        MockitoAnnotations.openMocks(this);
        publicationComment = new PublicationComment();
        publicationCommentDTO = new PublicationCommentDTO();
        publicationComments = new ArrayList<>();
        sorting = new Sorting();
        id = 1;
    }

    @Test
    void findAllByPublicationId() {
        when(publicationCommentService.findAllByPublicationId(sorting, id)).thenReturn(publicationComments);
        publicationCommentFacade.findAllByPublicationId(sorting, id);
        verify(publicationCommentService, times(1)).findAllByPublicationId(sorting, id);
    }

    @Test
    void countAllByPublicationId() {
        when(publicationCommentService.countAllByPublicationId(id)).thenReturn(anyInt());
        publicationCommentFacade.countAllByPublicationId(id);
        verify(publicationCommentService, times(1)).countAllByPublicationId(id);
    }

    @Test
    void create() {
        doNothing().when(publicationCommentService).create(publicationComment);
        publicationCommentFacade.create(publicationCommentDTO);
        verify(publicationCommentService, times(1)).create(publicationComment);
    }

    @Test
    void update() {
        doNothing().when(publicationCommentService).update(publicationComment);
        publicationCommentFacade.update(publicationCommentDTO);
        verify(publicationCommentService, times(1)).update(publicationComment);
    }

    @Test
    void delete() {
        doNothing().when(publicationCommentService).delete(anyInt());
        publicationCommentFacade.delete(anyInt());
        verify(publicationCommentService, times(1)).delete(anyInt());
    }

    @Test
    void findById() {
        when(publicationCommentService.findById(anyInt())).thenReturn(publicationComment);
        publicationCommentFacade.findById(anyInt());
        verify(publicationCommentService, times(1)).findById(anyInt());
    }

    @Test
    void findAll() {
        when(publicationCommentService.findAll()).thenReturn(publicationComments);
        publicationCommentFacade.findAll();
        verify(publicationCommentService, times(1)).findAll();
    }
}