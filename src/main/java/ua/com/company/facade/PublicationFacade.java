package ua.com.company.facade;

import ua.com.company.view.dto.PublicationDTO;

import java.util.List;

public interface PublicationFacade extends BaseFacade<PublicationDTO> {
    List<PublicationDTO> findAllByTopicId(int topicId);

    List<PublicationDTO> findAllByUserId(int userId);
}