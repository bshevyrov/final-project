package ua.com.company.service;

import ua.com.company.entity.Publication;
import ua.com.company.entity.Sorting;
import ua.com.company.view.dto.PublicationDTO;

import java.util.List;

public interface PublicationService extends BaseService<Publication> {
    List<Publication> findAllByTopicId(Sorting obj, int topicId);

    List<Publication> findAllByUserId(Sorting obj, int userId);

    List<Publication> findAllByTitle(Sorting sorting, String searchReq);

    int countAllByTopicId(int topicId);

    int countAllByUserId(int userId);

    int countAllByTitle(String searchReq);

    void updateCover(int pubId, int coverId);

    List<Publication> findAllSorted(Sorting sorting);

    int countAll();
}
