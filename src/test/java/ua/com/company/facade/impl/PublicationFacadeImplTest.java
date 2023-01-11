package ua.com.company.facade.impl;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import ua.com.company.entity.Image;
import ua.com.company.entity.Publication;
import ua.com.company.entity.Sorting;
import ua.com.company.entity.Topic;
import ua.com.company.service.PublicationService;
import ua.com.company.view.dto.ImageDTO;
import ua.com.company.view.dto.PublicationDTO;
import ua.com.company.view.dto.TopicDTO;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class PublicationFacadeImplTest {
    @Mock
    PublicationService publicationService;
    @InjectMocks
    PublicationFacadeImpl publicationFacade;

    private Publication publication;
    private PublicationDTO publicationDTO;
    private List<Publication> publications;
    private List<TopicDTO> topicDTOS;
    private List<Topic> topics;
    private ImageDTO imageDTO;
    private Image image;
    private Sorting sorting;
    private int id;
    private String title;

    @BeforeAll
    void init() {
        MockitoAnnotations.openMocks(this);
        publication = new Publication();
        publicationDTO = new PublicationDTO();
        publications = new ArrayList<>();
        topicDTOS = new ArrayList<>();
        publicationDTO.setTopics(topicDTOS);
        imageDTO = new ImageDTO();
        publicationDTO.setCover(imageDTO);
        image = new Image();
        topics = new ArrayList<>();
        publication.setTopics(topics);
        publication.setCover(image);
        sorting = new Sorting();
        id = 1;
        title = "title";
    }

    @Test
    void create() {
        doNothing().when(publicationService).create(publication);
        publicationFacade.create(publicationDTO);
        verify(publicationService, times(1)).create(publication);
    }

    @Test
    void update() {
        doNothing().when(publicationService).update(publication);
        publicationFacade.update(publicationDTO);
        verify(publicationService, times(1)).update(publication);
    }

    @Test
    void delete() {
        doNothing().when(publicationService).delete(anyInt());
        publicationFacade.delete(anyInt());
        verify(publicationService, times(1)).delete(anyInt());
    }

    @Test
    void findById() {
        when(publicationService.findById(anyInt())).thenReturn(publication);
        publicationFacade.findById(anyInt());
        verify(publicationService, times(1)).findById(anyInt());
    }

    @Test
    void findAll() {
        when(publicationService.findAll()).thenReturn(publications);
        publicationFacade.findAll();
        verify(publicationService, times(1)).findAll();
    }

    @Test
    void findAllByTopicId() {
        when(publicationService.findAllByTopicId(sorting, id)).thenReturn(publications);
        publicationFacade.findAllByTopicId(sorting, id);
        verify(publicationService, times(1)).findAllByTopicId(sorting, id);
    }

    @Test
    void findAllByUserId() {
        when(publicationService.findAllByUserId(sorting, id)).thenReturn(publications);
        publicationFacade.findAllByUserId(sorting, id);
        verify(publicationService, times(1)).findAllByUserId(sorting, id);
    }

    @Test
    void findAllByTitle() {
        when(publicationService.findAllByTitle(sorting, title)).thenReturn(publications);
        publicationFacade.findAllByTitle(sorting,title);
        verify(publicationService, times(1)).findAllByTitle(sorting, title);
    }

    @Test
    void countAllByTopicId() {
        when(publicationService.countAllByTopicId(id)).thenReturn(anyInt());
        publicationFacade.countAllByTopicId(id);
        verify(publicationService, times(1)).countAllByTopicId(id);
    }

    @Test
    void countAllByUserId() {
        when(publicationService.countAllByUserId(id)).thenReturn(anyInt());
        publicationFacade.countAllByUserId(id);
        verify(publicationService, times(1)).countAllByUserId(id);
    }

    @Test
    void countAllByTitle() {
        when(publicationService.countAllByTitle(title)).thenReturn(anyInt());
        publicationFacade.countAllByTitle(title);
        verify(publicationService, times(1)).countAllByTitle(title);
    }
}