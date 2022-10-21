package ua.com.company.service;

import ua.com.company.entity.Publication;

import java.util.Collection;
import java.util.List;

public interface PublicationService extends BaseService<Publication>{
    List<Publication> findAllByTopicId(int topicId);
    List<Publication> findAllByUserId(int userId);

    List<Publication> findAllByTitle(String searchReq);
}
