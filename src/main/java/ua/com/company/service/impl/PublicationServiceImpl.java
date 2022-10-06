package ua.com.company.service.impl;

import ua.com.company.dao.PublicationDAO;
import ua.com.company.dao.mysql.impl.MysqlPublicationDAOImpl;
import ua.com.company.entity.Publication;
import ua.com.company.exception.DBException;
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
        publicationDAO.create(publication);
    }

    @Override
    public void update(Publication publication) throws DBException {
        publicationDAO.update(publication);
    }

    @Override
    public void delete(int id) {
        publicationDAO.delete(id);
    }

    @Override
    public Publication findById(int id) throws DBException {
        return publicationDAO.findById(id).get();
    }

    @Override
    public List<Publication> findAll() throws DBException {
        return publicationDAO.findAll();
    }
}
