package ua.com.company.facade.impl;

import ua.com.company.entity.Sorting;
import ua.com.company.facade.PublicationCommentFacade;
import ua.com.company.service.PublicationCommentService;
import ua.com.company.utils.ClassConverter;
import ua.com.company.view.dto.PublicationCommentDTO;

import java.util.List;
import java.util.stream.Collectors;

public class PublicationCommentFacadeImpl implements PublicationCommentFacade {
    private final PublicationCommentService publicationCommentService;

    public PublicationCommentFacadeImpl(PublicationCommentService publicationCommentService) {
        this.publicationCommentService = publicationCommentService;
    }

    @Override
    public List<PublicationCommentDTO> findAllByPublicationId(Sorting sorting, int publicationId) {
        return publicationCommentService.findAllByPublicationId(sorting, publicationId).stream()
                .map(ClassConverter::publicationCommentToPublicationCommentDTO)
                .collect(Collectors.toList());
    }

    @Override
    public int countAllByPublicationId(int publicationId) {
        return publicationCommentService.countAllByPublicationId(publicationId);
    }

    @Override
    public void create(PublicationCommentDTO publicationCommentDTO) {
        publicationCommentService.create(ClassConverter.publicationCommentDTOToPublicationComment(publicationCommentDTO));
    }

    @Override
    public void update(PublicationCommentDTO publicationCommentDTO) {
        publicationCommentService.update(ClassConverter.publicationCommentDTOToPublicationComment(publicationCommentDTO));

    }

    @Override
    public void delete(int id) {
        publicationCommentService.delete(id);
    }

    @Override
    public PublicationCommentDTO findById(int id) {
        return ClassConverter.publicationCommentToPublicationCommentDTO(publicationCommentService.findById(id));
    }

    @Override
    public List<PublicationCommentDTO> findAll() {
        return publicationCommentService.findAll().stream()
                .map(ClassConverter::publicationCommentToPublicationCommentDTO)
                .collect(Collectors.toList());
    }
}
