package ua.com.company.facade.impl;

import ua.com.company.dao.DAOFactory;
import ua.com.company.facade.TopicFacade;
import ua.com.company.service.TopicService;
import ua.com.company.utils.ClassConverter;
import ua.com.company.view.dto.TopicDTO;

import java.util.List;
import java.util.stream.Collectors;

public class TopicFacadeImpl implements TopicFacade {
    private final TopicService topicService = DAOFactory.getInstance().getTopicDAO();

    @Override
    public void create(TopicDTO topicDTO) {
        topicService.create(ClassConverter.topicDTOTotopic(topicDTO));

    }

    @Override
    public void update(TopicDTO topicDTO) {
        topicService.update(ClassConverter.topicDTOTotopic(topicDTO));

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
}
