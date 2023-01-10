package ua.com.company.utils.tag;

import jakarta.servlet.jsp.tagext.SimpleTagSupport;
import ua.com.company.dao.DAOFactory;
import ua.com.company.facade.PublicationFacade;
import ua.com.company.facade.impl.TopicFacadeImpl;
import ua.com.company.service.impl.TopicServiceImpl;

public class MainPageTileTag extends SimpleTagSupport {
    public void doTag() {
        getJspContext().setAttribute("headerTopics", new TopicFacadeImpl(new TopicServiceImpl(
                DAOFactory.getInstance().getTopicDAO())).findAll());
    }
}
