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

    private ImageServiceImpl() {
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
            log.error("Can`t update image ", e);
        }
    }

    @Override
    public void delete(int id) {
        try (Connection con = getConnection()) {
            imageDAO.delete(con, id);
        } catch (DBException | SQLException e) {
            log.error("Delete Error ", e);
        }
    }

    @Override
    public Image findById(int id) {
        Image image = null;
        try (Connection con = getConnection()) {
            image = imageDAO.findById(con, id)
                    .orElseThrow(() -> new ImageNotFoundException("" + id));
        } catch (DBException | SQLException e) {
            log.error("Find by id error ", e);
        } catch (ImageNotFoundException e) {
            log.warn("Image not found " + id, e);
        }
        return image;
    }

    @Override
    public List<Image> findAll() {
        List<Image> imageList = null;
        try (Connection con = getConnection()) {
            imageList = imageDAO.findAll(con);
        } catch (DBException | SQLException e) {
            log.error("findAll error ", e);
        }
        return imageList;
    }

    @Override
    public Image findByPath(String imagePath) {
        Image image = null;
        try (Connection con = getConnection()) {
            image = imageDAO.findByPath(con, imagePath)
                    .orElseThrow(() -> new ImageNotFoundException("" + imagePath));
        } catch (DBException | SQLException e) {
            log.error("Find by imagePath error ", e);
        } catch (ImageNotFoundException e) {
            log.warn("Image not found " + imagePath, e);
        }
        return image;
    }
}
