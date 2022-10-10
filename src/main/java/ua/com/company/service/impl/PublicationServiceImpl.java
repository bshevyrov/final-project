package ua.com.company.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.com.company.dao.PublicationDAO;
import ua.com.company.entity.Publication;
import ua.com.company.exception.DBException;
import ua.com.company.exception.PublicationNotFoundException;
import ua.com.company.service.PublicationService;

import java.util.List;

public class PublicationServiceImpl implements PublicationService {
    private final Logger log = LoggerFactory.getLogger(PublicationServiceImpl.class);

    PublicationDAO publicationDAO;

    public PublicationServiceImpl(PublicationDAO publicationDAO) {
        this.publicationDAO = publicationDAO;
    }

    @Override
    public int create(Publication publication) {
        int id = -1;
        try {
            id = publicationDAO.create(publication);
        } catch (DBException e) {
            log.error(String.valueOf(e));
            e.printStackTrace();
        }
        return id;
    }

    @Override
    public void update(Publication publication) {
        try {
            publicationDAO.update(publication);
        } catch (DBException e) {
            log.error(String.valueOf(e));
            e.printStackTrace();
        }
    }

    @Override
    public void delete(int id) {
        try {
            publicationDAO.delete(id);
        } catch (DBException e) {
            log.error(String.valueOf(e));
            e.printStackTrace();
        }
    }

    @Override
    public Publication findById(int id) {
        Publication publication = null;
        try {
            publication = publicationDAO.findById(id)
                    .orElseThrow(() -> new PublicationNotFoundException("" + id));
        } catch (DBException e) {
            log.error(String.valueOf(e));
            e.printStackTrace();
        } catch (PublicationNotFoundException e) {
            log.warn(String.valueOf(e));
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
            log.error(String.valueOf(e));
            e.printStackTrace();
        }
        return publicationList;
    }
}
