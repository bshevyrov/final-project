package ua.com.company.facade;

import ua.com.company.view.dto.TopicDTO;

public interface TopicFacade extends BaseFacade<TopicDTO> {
    TopicDTO findByTitle(String title);
}
