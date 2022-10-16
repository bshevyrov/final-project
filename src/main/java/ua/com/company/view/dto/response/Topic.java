package ua.com.company.view.dto.response;

public class Topic extends BaseDTO {
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
}
