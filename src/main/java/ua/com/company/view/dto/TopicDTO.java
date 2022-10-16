package ua.com.company.view.dto;

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
    public String toString() {
        return "Topic{" +
//                super.toString()+
                "title='" + title + '\'' +
                '}';
    }
}
