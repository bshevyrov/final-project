package ua.com.company.entity;

import java.sql.Timestamp;

public class Topic extends BaseEntity{
    private String title;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Topic() {
        super();
        this.setCreateDate(new Timestamp(System.currentTimeMillis()));
        this.setUpdateDate(new Timestamp(System.currentTimeMillis()));
    }
}
