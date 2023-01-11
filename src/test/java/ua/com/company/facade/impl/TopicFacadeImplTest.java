package ua.com.company.facade.impl;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import ua.com.company.entity.Topic;
import ua.com.company.service.impl.TopicServiceImpl;
import ua.com.company.view.dto.TopicDTO;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class TopicFacadeImplTest {
    @InjectMocks
    TopicFacadeImpl topicFacade;

    @Mock
    TopicServiceImpl topicService;

    TopicDTO topicDTO;
    Topic topic;
    List<Topic> topics;
    String title;

    @BeforeAll
    void init() {
        MockitoAnnotations.openMocks(this);
        topicDTO = new TopicDTO();
        topic = new Topic();
        topics = new ArrayList<>();
        title = "title";

    }
    @Test
    void create() {
        doNothing().when(topicService).create(topic);
        topicFacade.create(topicDTO);
        verify(topicService, times(1)).create(topic);
    }

    @Test
    void update() {
        doNothing().when(topicService).update(topic);
        topicFacade.update(topicDTO);
        verify(topicService, times(1)).update(topic);
    }

    @Test
    void delete() {
        doNothing().when(topicService).delete(anyInt());
        topicFacade.delete(anyInt());
        verify(topicService, times(1)).delete(anyInt());
    }

    @Test
    void findById() {
        when(topicService.findById(anyInt())).thenReturn(topic);
        topicFacade.findById(anyInt());
        verify(topicService, times(1)).findById(anyInt());
    }

    @Test
    void findAll() {
       when(topicService.findAll()).thenReturn(topics);
        topicFacade.findAll();
        verify(topicService, times(1)).findAll();
    }

    @Test
    void findByTitle() {
        when(topicService.findByTitle(anyString())).thenReturn(topic);
        topicFacade.findByTitle(title);
        verify(topicService, times(1)).findByTitle(title);
    }
}