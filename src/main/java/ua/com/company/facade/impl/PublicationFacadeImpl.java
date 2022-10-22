package ua.com.company.facade.impl;

import ua.com.company.entity.Sorting;
import ua.com.company.facade.PublicationFacade;
import ua.com.company.service.PublicationService;
import ua.com.company.service.impl.PublicationServiceImpl;
import ua.com.company.utils.ClassConverter;
import ua.com.company.view.dto.PublicationDTO;

import java.util.List;
import java.util.stream.Collectors;

public class PublicationFacadeImpl implements PublicationFacade {
    private final PublicationService publicationService = PublicationServiceImpl.getInstance();

    @Override
    public int create(PublicationDTO publicationDTO) {
        return publicationService.create(ClassConverter.publicationDTOToPublication(publicationDTO));

    }

    @Override
    public void update(PublicationDTO publicationDTO) {
        publicationService.update(ClassConverter.publicationDTOToPublication(publicationDTO));
    }

    @Override
    public void delete(int id) {
        publicationService.delete(id);
    }

    @Override
    public PublicationDTO findById(int id) {
        return ClassConverter.publicationToPublicationDTO(publicationService.findById(id));
    }

    @Override
    public List<PublicationDTO> findAll() {
        return publicationService.findAll().stream()
                .map(ClassConverter::publicationToPublicationDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<PublicationDTO> findAllByTopicId(Sorting obj, int topicId) {
        return publicationService.findAllByTopicId(obj,topicId).stream()
                .map(ClassConverter::publicationToPublicationDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<PublicationDTO> findAllByUserId(int userId) {
        return publicationService.findAllByUserId(userId).stream()
                .map(ClassConverter::publicationToPublicationDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<PublicationDTO> findAllByTitle(String searchReq) {
        return publicationService.findAllByTitle(searchReq).stream()
                .map(ClassConverter::publicationToPublicationDTO)
                .collect(Collectors.toList());    }
}
