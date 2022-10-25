package ua.com.company.facade;

import ua.com.company.entity.Sorting;
import ua.com.company.view.dto.PublicationDTO;

import java.util.List;

public interface PublicationFacade extends BaseFacade<PublicationDTO> {
    List<PublicationDTO> findAllByTopicId(Sorting obj, int topicId);

    List<PublicationDTO> findAllByUserId(int userId);

    List<PublicationDTO> findAllByTitle(String searchReq);

    int countAllByTopicId(int topicId);
}
