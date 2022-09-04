package ua.com.company.service.impl;

import ua.com.company.dao.PublicationDao;
import ua.com.company.dao.impl.PublicationDaoImpl;
import ua.com.company.entity.Publication;
import ua.com.company.service.PublicationService;

import java.util.List;

public class PublicationServiceImpl implements PublicationService {
    PublicationDao publicationDao = new PublicationDaoImpl();

    @Override
    public void create(Publication publication) {
        publicationDao.create(publication);
    }

    @Override
    public void update(Publication publication) {
        publicationDao.update(publication);
    }

    @Override
    public void delete(int id) {
        publicationDao.delete(id);
    }

    @Override
    public Publication findById(int id) {
        return publicationDao.findById(id);
    }

    @Override
    public List<Publication> findAll() {
        return publicationDao.findAll();
    }
}
