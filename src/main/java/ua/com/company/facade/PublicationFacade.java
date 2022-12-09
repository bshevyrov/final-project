package ua.com.company.facade;

import ua.com.company.entity.Sorting;
import ua.com.company.view.dto.PublicationCommentDTO;
import ua.com.company.view.dto.PublicationDTO;

import java.util.List;

public interface PublicationFacade extends BaseFacade<PublicationDTO> {
    List<PublicationDTO> findAllByTopicId(Sorting obj, int topicId);

    List<PublicationDTO> findAllByUserId(Sorting obj, int userId);

    List<PublicationDTO> findAllByTitle(Sorting sorting, String searchReq);

    int countAllByTopicId(int topicId);

    int countAllByUserId(int userId);

    int countAllByTitle(String searchReq);

    List<PublicationCommentDTO> findAllCommentsByPublicationId(Sorting sorting, int publicationId);

    void createComment(int pubId, int personId, String comment);
}
