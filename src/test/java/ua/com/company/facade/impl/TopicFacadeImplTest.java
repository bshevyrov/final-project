package ua.com.company.facade.impl;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import ua.com.company.entity.Topic;
import ua.com.company.facade.TopicFacade;
import ua.com.company.service.TopicService;
import ua.com.company.view.dto.TopicDTO;

import java.util.List;

import static org.mockito.Mockito.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class TopicFacadeImplTest {
    private TopicService topicService;
    private TopicFacade topicFacade;

    @BeforeAll
    void init() {
        topicService = mock(TopicService.class);
        topicFacade = new TopicFacadeImpl(topicService);
    }

    @Test
    void create() {
        topicFacade.create(new TopicDTO());
        verify(topicService, times(1)).create(any());
    }

    @Test
    void update() {
        topicFacade.update(new TopicDTO());
        verify(topicService, times(1)).update(any());
    }

    @Test
    void delete() {
        topicFacade.delete(isA(Integer.class));
        verify(topicService, times(1)).delete(isA(Integer.class));
    }

    @Test
    void findById() {
        when(topicService.findById(isA(Integer.class))).thenReturn(new Topic());
        topicFacade.findById(isA(Integer.class));
        verify(topicService, times(1)).findById(isA(Integer.class));
    }

    @Test
    void findByTitle() {
        when(topicService.findByTitle(isA(String.class))).thenReturn(new Topic());
        topicFacade.findByTitle("");
        verify(topicService, times(1)).findByTitle(isA(String.class));
    }

    @Test
    void findAll() {
        when(topicService.findAll()).thenReturn(List.of(new Topic()));
        topicFacade.findAll();
        verify(topicService, times(1)).findAll();
    }
}