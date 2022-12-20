package ua.com.company.facade.impl;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import ua.com.company.entity.Image;
import ua.com.company.entity.Publication;
import ua.com.company.entity.Sorting;
import ua.com.company.entity.Topic;
import ua.com.company.facade.PublicationFacade;
import ua.com.company.service.PublicationService;
import ua.com.company.view.dto.ImageDTO;
import ua.com.company.view.dto.PublicationDTO;
import ua.com.company.view.dto.TopicDTO;

import java.util.List;

import static org.mockito.Mockito.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class _PublicationFacadeImplTest {

    private PublicationFacade publicationFacade;
    private PublicationService publicationService;
    private PublicationDTO publicationDTO;
    private Publication publication;

    @BeforeAll
    void init() {
        publicationService = mock(PublicationService.class);
        publicationFacade = new PublicationFacadeImpl(publicationService);
        publicationDTO = new PublicationDTO();
        publicationDTO.setTopics(List.of(new TopicDTO()));
        publicationDTO.setCover(new ImageDTO());
        publication= new Publication();
        publication.setCover(new Image());
        publication.setTopics(List.of(new Topic()));
    }


    @Test
    void create() {
        publicationFacade.create(publicationDTO);
        verify(publicationService, times(1)).create(any());
    }

    @Test
    void update() {
        publicationFacade.update(publicationDTO);
        verify(publicationService, times(1)).update(any());
    }

    @Test
    void delete() {
        publicationFacade.delete(isA(Integer.class));
        verify(publicationService, times(1)).delete(isA(Integer.class));
    }

    @Test
    void findById() {
        when(publicationService.findById(isA(Integer.class))).thenReturn(publication);
        publicationFacade.findById(isA(Integer.class));
        verify(publicationService, times(1)).findById(isA(Integer.class));
    }

    @Test
    void findAll() {
        publicationFacade.findAll();
        verify(publicationService, times(1)).findAll();
    }

    @Test
    void findAllByTopicId() {
        publicationFacade.findAllByTopicId(new Sorting(), eq(isA(Integer.class)));
        verify(publicationService, times(1)).findAllByTopicId(isA(Sorting.class), isA(Integer.class));
    }

    @Test
    void findAllByUserId() {
        publicationFacade.findAllByUserId(new Sorting(), eq(isA(Integer.class)));
        verify(publicationService, times(1)).findAllByUserId(isA(Sorting.class), isA(Integer.class));
    }

    @Test
    void findAllByTitle() {
        publicationFacade.findAllByTitle(new Sorting(), "");
        verify(publicationService, times(1)).findAllByTitle(isA(Sorting.class), isA(String.class));
    }

    @Test
    void countAllByTopicId() {
        publicationFacade.countAllByTopicId(isA(Integer.class));
        verify(publicationService, times(1)).countAllByTopicId(isA(Integer.class));
    }

    @Test
    void countAllByUserId() {
        publicationFacade.countAllByUserId(isA(Integer.class));
        verify(publicationService, times(1)).countAllByUserId(isA(Integer.class));
    }

    @Test
    void countAllByTitle() {
        publicationFacade.countAllByTitle("");
        verify(publicationService, times(1)).countAllByTitle(isA(String.class));
    }
}