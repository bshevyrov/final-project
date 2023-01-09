package ua.com.company.facade.impl;

import ua.com.company.facade.ImageFacade;
import ua.com.company.service.ImageService;
import ua.com.company.utils.ClassConverter;
import ua.com.company.view.dto.ImageDTO;

import java.util.List;
import java.util.stream.Collectors;

public class ImageFacadeImpl implements ImageFacade {
    private final ImageService imageService;

    public ImageFacadeImpl(ImageService imageService) {
        this.imageService = imageService;
    }

    @Override
    public void create(ImageDTO imageDTO) {
        imageService.create(ClassConverter.imageDTOToImage(imageDTO));
    }

    @Override
    public void update(ImageDTO imageDTO) {
        imageService.update(ClassConverter.imageDTOToImage(imageDTO));

    }

    @Override
    public void delete(int id) {
        imageService.delete(id);
    }

    @Override
    public ImageDTO findById(int id) {
        return ClassConverter.imageToImageDTO(imageService.findById(id));
    }

    @Override
    public List<ImageDTO> findAll() {
        return imageService.findAll().stream()
                .map(ClassConverter::imageToImageDTO)
                .collect(Collectors.toList());
    }

    @Override
    public ImageDTO findByPath(String avatarPath) {
        return ClassConverter.imageToImageDTO(imageService.findByPath(avatarPath));
    }
}
