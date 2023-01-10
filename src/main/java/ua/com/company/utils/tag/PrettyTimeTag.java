package ua.com.company.utils.tag;


import jakarta.servlet.jsp.JspWriter;
import jakarta.servlet.jsp.tagext.SimpleTagSupport;
import org.apache.taglibs.standard.tag.common.fmt.SetLocaleSupport;
import org.ocpsoft.prettytime.PrettyTime;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Locale;

/**
 * Custom tag to pretty print {@link java.util.Date} objects using <a href="http://ocpsoft.org/prettytime/">prettytime</a>.
 */
public class PrettyTimeTag extends SimpleTagSupport {

    /**
     * The pretty time object.
     */
    private PrettyTime prettyTime;

    /**
     * The date to pretty print.
     */
    private Date date;

    /**
     * The locale used to localize the message.
     */
    private String locale;

    public PrettyTimeTag() {
        prettyTime = new PrettyTime();
    }

    @Override
    public void doTag() throws IOException {
        if (locale != null) {
            Locale currentLocale = new Locale(locale);
            prettyTime.setLocale(currentLocale);
        }

        JspWriter out = getJspContext().getOut();
        out.print(prettyTime.format(date));
    }

    /*
     * setters for tag attributes
     */

    public void setDate(Timestamp ts) {
        this.date = new Date(ts.getTime());
    }

    public void setLocale(String locale) {
        //language to locale switch
        switch (locale){
            case "ua": locale = "uk";
            break;
            case "en": locale = "en";
            break;
        }
        this.locale = locale;
    }

}
