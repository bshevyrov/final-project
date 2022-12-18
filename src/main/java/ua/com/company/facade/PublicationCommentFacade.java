package ua.com.company.facade;

import ua.com.company.entity.Sorting;
import ua.com.company.view.dto.PublicationCommentDTO;

import java.util.List;

public interface PublicationCommentFacade extends BaseFacade<PublicationCommentDTO> {
    List<PublicationCommentDTO> findAllByPublicationId(Sorting sorting, int publicationId);
}
