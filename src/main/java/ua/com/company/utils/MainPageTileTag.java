package ua.com.company.utils;

import jakarta.servlet.jsp.tagext.SimpleTagSupport;
import ua.com.company.facade.impl.TopicFacadeImpl;
import ua.com.company.service.impl.TopicServiceImpl;

public class MainPageTileTag extends SimpleTagSupport {
    public void doTag() {
        getJspContext().setAttribute("headerTopics", new TopicFacadeImpl(TopicServiceImpl.getInstance()).findAll());
    }
}
