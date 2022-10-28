package ua.com.company.service;

import ua.com.company.entity.Publication;
import ua.com.company.entity.Sorting;

import java.util.Collection;
import java.util.List;

public interface PublicationService extends BaseService<Publication>{
    List<Publication> findAllByTopicId(Sorting obj, int topicId);
    List<Publication> findAllByUserId(Sorting obj,int userId);

    List<Publication> findAllByTitle(String searchReq);

    int countAllByTopicId(int topicId);

    int countAllByUserId(int userId);
}
