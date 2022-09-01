package ua.com.company.dao;

import ua.com.company.entity.Publication;

import java.util.List;

public interface PublicationDao extends BaseDao<Publication> {


    List<Publication> findAllByTitle(String title);
}
