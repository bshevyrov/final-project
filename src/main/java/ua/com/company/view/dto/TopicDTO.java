package ua.com.company.view.dto;

import java.util.Objects;

public class TopicDTO extends BaseDTO {
    private String title;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public TopicDTO() {
    }

    public TopicDTO(String title) {
        this.title = title;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TopicDTO topicDTO = (TopicDTO) o;
        return Objects.equals(title, topicDTO.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title);
    }

    @Override
    public String toString() {
        return "TopicDTO{" +
                "title='" + title + '\'' +
                '}';
    }
}
