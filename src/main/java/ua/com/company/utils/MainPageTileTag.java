package ua.com.company.utils;

import jakarta.servlet.jsp.tagext.SimpleTagSupport;
import ua.com.company.dao.TopicDAO;
import ua.com.company.dao.mysql.impl.MysqlTopicDAOImpl;
import ua.com.company.exception.DBException;
import ua.com.company.facade.TopicFacade;
import ua.com.company.facade.impl.TopicFacadeImpl;

//https://www.tutorialspoint.com/jsp/jsp_custom_tags.htm
public class MainPageTileTag extends SimpleTagSupport {
    public void doTag(){
        TopicFacade topicFacade = new TopicFacadeImpl();
            getJspContext().setAttribute("headerTopics",new TopicFacadeImpl().findAll());
    }

}
