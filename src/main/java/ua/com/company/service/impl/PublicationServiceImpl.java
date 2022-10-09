package ua.com.company.service.impl;

import ua.com.company.dao.PublicationDAO;
import ua.com.company.entity.Publication;
import ua.com.company.exception.DBException;
import ua.com.company.exception.PublicationNotFoundException;
import ua.com.company.service.PublicationService;

import java.util.List;

public class PublicationServiceImpl implements PublicationService {
    //PublicationDAO publicationDAO = new MysqlPublicationDAOImpl();

    PublicationDAO publicationDAO;

    public PublicationServiceImpl(PublicationDAO publicationDAO) {
        this.publicationDAO = publicationDAO;
    }

    @Override
    public void create(Publication publication) {
        try {
            publicationDAO.create(publication);
        } catch (DBException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Publication publication) {
        try {
            publicationDAO.update(publication);
        } catch (DBException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(int id) {
        try {
            publicationDAO.delete(id);
        } catch (DBException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Publication findById(int id) throws PublicationNotFoundException {
        Publication publication = null;
        try {
            publication = publicationDAO.findById(id)
                    .orElseThrow(() -> new PublicationNotFoundException("" + id));
        } catch (DBException e) {
            e.printStackTrace();
        }
        return publication;
    }

    @Override
    public List<Publication> findAll() {
        List<Publication> publicationList = null;
        try {
            publicationList = publicationDAO.findAll();
        } catch (DBException e) {
            e.printStackTrace();
        }
        return publicationList;
    }
}
