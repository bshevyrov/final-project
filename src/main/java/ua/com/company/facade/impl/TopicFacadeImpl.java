package ua.com.company.facade.impl;

import ua.com.company.facade.TopicFacade;
import ua.com.company.service.TopicService;
import ua.com.company.utils.ClassConverter;
import ua.com.company.view.dto.TopicDTO;

import java.util.List;
import java.util.stream.Collectors;

public class TopicFacadeImpl implements TopicFacade {
    private final TopicService topicService /*= TopicServiceImpl.getInstance()*/;

    public TopicFacadeImpl(TopicService topicService) {
        this.topicService = topicService;
    }

    @Override
    public void create(TopicDTO topicDTO) {
         topicService.create(ClassConverter.topicDTOToTopic(topicDTO));
    }

    @Override
    public void update(TopicDTO topicDTO) {
        topicService.update(ClassConverter.topicDTOToTopic(topicDTO));
    }

    @Override
    public void delete(int id) {
        topicService.delete(id);
    }

    @Override
    public TopicDTO findById(int id) {
        return ClassConverter.topicToTopicDTO(topicService.findById(id));
    }

    @Override
    public List<TopicDTO> findAll() {
        return topicService.findAll().stream()
                .map(ClassConverter::topicToTopicDTO)
                .collect(Collectors.toList());
    }

    @Override
    public TopicDTO findByTitle(String title) {
         return ClassConverter.topicToTopicDTO(topicService.findByTitle(title));
    }
    }

