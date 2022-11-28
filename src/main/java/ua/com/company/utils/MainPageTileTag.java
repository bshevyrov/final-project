package ua.com.company.utils;

import jakarta.servlet.jsp.tagext.SimpleTagSupport;
import ua.com.company.facade.TopicFacade;
import ua.com.company.facade.impl.TopicFacadeImpl;
import ua.com.company.service.impl.TopicServiceImpl;

//https://www.tutorialspoint.com/jsp/jsp_custom_tags.htm
public class MainPageTileTag extends SimpleTagSupport {
    public void doTag(){
        //TODO remove these tag
//        TopicFacade topicFacade = new TopicFacadeImpl(topicService);
            getJspContext().setAttribute("headerTopics",new TopicFacadeImpl(TopicServiceImpl.getInstance()).findAll());
    }

}
