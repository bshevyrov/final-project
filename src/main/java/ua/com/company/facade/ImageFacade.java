package ua.com.company.facade;

import ua.com.company.view.dto.ImageDTO;

public interface ImageFacade extends BaseFacade<ImageDTO>{
    ImageDTO findByPath(String avatarPath);
}
