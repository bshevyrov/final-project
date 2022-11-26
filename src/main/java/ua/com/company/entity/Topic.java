package ua.com.company.entity;

import java.util.Objects;

public class Topic extends BaseEntity {
    private String title;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Topic() {
    }

    public Topic(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "Topic{" +
//                super.toString()+
                "title='" + title + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Topic topic = (Topic) o;
        return Objects.equals(title, topic.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title);
    }
}
