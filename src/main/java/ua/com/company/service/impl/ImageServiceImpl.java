package ua.com.company.service.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.com.company.dao.DAOFactory;
import ua.com.company.dao.ImageDAO;
import ua.com.company.entity.Image;
import ua.com.company.exception.DBException;
import ua.com.company.exception.ImageNotFoundException;
import ua.com.company.service.ImageService;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class ImageServiceImpl implements ImageService {
    private final Logger log = LogManager.getLogger(ImageServiceImpl.class);

    private final ImageDAO imageDAO = DAOFactory.getInstance().getImageDAO();
    private static ImageService instance;

    public static synchronized ImageService getInstance() {
        if (instance == null) {
                instance = new ImageServiceImpl();

        }
        return instance;
    }

    private ImageServiceImpl()  {
    }

    @Override
    public void create(Image image) {
        try (Connection con = getConnection()) {
             imageDAO.create(con, image);
        } catch (DBException | SQLException e) {
            log.error("Can`t create image ", e);
        }
    }

    @Override
    public void update(Image image) {
        try (Connection con = getConnection()) {
            imageDAO.update(con, image);
        } catch (DBException | SQLException e) {
            log.error("Can`t update image " + image, e);
            e.printStackTrace();
        }
    }

    @Override
    public void delete(int id) {
        try (Connection con = getConnection()) {
            imageDAO.delete(con, id);
        } catch (DBException | SQLException e) {
            log.error("Delete Error " + id, e);
            e.printStackTrace();
        }
    }

    @Override
    public Image findById(int id) {
        Image image = null;
        try (Connection con = getConnection()) {
            image = imageDAO.findById(con, id)
                    .orElseThrow(() -> new ImageNotFoundException("" + id));
        } catch (DBException | SQLException e) {
            log.error("Image not found " + id, e);
            e.printStackTrace();
        } catch (ImageNotFoundException e) {
            log.warn("Image not found " + id, e);
            e.printStackTrace();
        }
        return image;
    }

    @Override
    public List<Image> findAll() {
        List<Image> imageList = null;
        try (Connection con = getConnection()) {
            imageList = imageDAO.findAll(con);
        } catch (DBException | SQLException e) {
            log.error("findAll ex ", e);
            e.printStackTrace();
        }
        return imageList;
    }
    @Override
    public Image findByPublicationId(Connection con, int id) {
        Image image = null;
        //TODO return something
        try {
            image = imageDAO.findByPublicationId(con, id)
                    .orElseThrow(() -> new ImageNotFoundException("" + id));
        } catch (DBException e) {
            log.error("Image ByPublicationId not found " + id, e);
            e.printStackTrace();
        } catch (ImageNotFoundException e) {
            log.warn("Image ByPublicationId not found " + id, e);
            e.printStackTrace();
        }
        return image;
    }
}
