package ua.com.company.facade.impl;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import ua.com.company.entity.Image;
import ua.com.company.service.impl.ImageServiceImpl;
import ua.com.company.view.dto.ImageDTO;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class ImageFacadeImplTest {
    @InjectMocks
    ImageFacadeImpl imageFacade;

    @Mock
    ImageServiceImpl imageService;

    ImageDTO imageDTO;
    Image image;
    List<Image> images;
    String path;

    @BeforeAll
    void init() {
        MockitoAnnotations.openMocks(this);
        imageDTO = new ImageDTO();
        image = new Image();
        images = new ArrayList<>();
        path = "path";
    }

    @Test
    void create() {
        doNothing().when(imageService).create(image);
        imageFacade.create(imageDTO);
        verify(imageService, times(1)).create(image);
    }

    @Test
    void update() {
        doNothing().when(imageService).update(image);
        imageFacade.update(imageDTO);
        verify(imageService, times(1)).update(image);
    }

    @Test
    void delete() {
        doNothing().when(imageService).delete(anyInt());
        imageFacade.delete(anyInt());
        verify(imageService, times(1)).delete(anyInt());
    }

    @Test
    void findById() {
        when(imageService.findById(anyInt())).thenReturn(image);
        imageFacade.findById(anyInt());
        verify(imageService, times(1)).findById(anyInt());
    }

    @Test
    void findAll() {
        when(imageService.findAll()).thenReturn(images);
        imageFacade.findAll();
        verify(imageService, times(1)).findAll();
    }

    @Test
    void findByPath() {
        when(imageService.findByPath(path)).thenReturn(image);
        imageFacade.findByPath(path);
        verify(imageService, times(1)).findByPath(path);
    }
}