package ua.com.company.service;

import ua.com.company.entity.Image;

import java.sql.Connection;

public interface ImageService extends BaseService<Image>{
    Image findByPublicationId(Connection con,int id);
}
