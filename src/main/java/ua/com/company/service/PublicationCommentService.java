package ua.com.company.service;

import ua.com.company.entity.PublicationComment;
import ua.com.company.entity.Sorting;

import java.util.List;

public interface PublicationCommentService extends BaseService<PublicationComment> {
    List<PublicationComment> findAllByPublicationId(Sorting sorting, int publicationId);

    int countAllByPublicationId(int publicationId);
}
