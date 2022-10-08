package ua.com.company.utils;

import jakarta.servlet.jsp.tagext.SimpleTagSupport;
import ua.com.company.dao.TopicDAO;
import ua.com.company.dao.mysql.impl.MysqlTopicDAOImpl;
import ua.com.company.exception.DBException;

//https://www.tutorialspoint.com/jsp/jsp_custom_tags.htm
public class MainPageTileTag extends SimpleTagSupport {
    public void doTag(){
        TopicDAO topicDAO = new MysqlTopicDAOImpl();
        try {
            getJspContext().setAttribute("topics",topicDAO.findAll());
        } catch (DBException e) {
            e.printStackTrace();
        }
    }

}
