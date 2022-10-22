package ua.com.company.service;

import ua.com.company.entity.Image;

public interface ImageService extends BaseService<Image>{
    Image findByPublicationId(int id);
}
