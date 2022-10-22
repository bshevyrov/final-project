package ua.com.company.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.com.company.dao.DAOFactory;
import ua.com.company.dao.ImageDAO;
import ua.com.company.entity.Image;
import ua.com.company.entity.Person;
import ua.com.company.exception.DBException;
import ua.com.company.exception.ImageNotFoundException;
import ua.com.company.exception.UserNotFoundException;
import ua.com.company.service.ImageService;

import java.util.List;

public class ImageServiceImpl implements ImageService {
    private final Logger log = LoggerFactory.getLogger(ImageServiceImpl.class);

    private final ImageDAO imageDAO = DAOFactory.getInstance().getImageDAO();
    private static ImageService instance;

    public static synchronized ImageService getInstance() {
        if (instance == null) {
            try {
                instance = new ImageServiceImpl();
            } catch (Exception e) {
                //TODO LOG
                e.printStackTrace();
            }
        }
        return instance;
    }

    private ImageServiceImpl() throws Exception {
    }

    @Override
    public int create(Image image) {
        int id = -1;
        try {
            id = imageDAO.create(image);
        } catch (DBException e) {
            log.error(String.valueOf(e));
            e.printStackTrace();
        }
        return id;
    }

    @Override
    public void update(Image image) {
        try {
            imageDAO.update(image);
        } catch (DBException e) {
            log.error(String.valueOf(e));
            e.printStackTrace();
        }
    }

    @Override
    public void delete(int id) {
        try {
            imageDAO.delete(id);
        } catch (DBException e) {
            log.error(String.valueOf(e));
            e.printStackTrace();
        }
    }

    @Override
    public Image findById(int id) {
        Image image = null;
        try {
            image = imageDAO.findById(id)
                    .orElseThrow(() -> new ImageNotFoundException("" + id));
        } catch (DBException e) {
            log.error(String.valueOf(e));
            e.printStackTrace();
        } catch (ImageNotFoundException e) {
            log.warn(String.valueOf(e));
            e.printStackTrace();
        }
        return image;
    }

    @Override
    public List<Image> findAll() {
        List<Image> imageList = null;
        try {
            imageList = imageDAO.findAll();
        } catch (DBException e) {
            log.error(String.valueOf(e));
            e.printStackTrace();
        }
        return imageList;
    }
}
