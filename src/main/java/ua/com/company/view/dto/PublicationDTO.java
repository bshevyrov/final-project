package ua.com.company.view.dto;

import java.util.List;
import java.util.Objects;

public class PublicationDTO extends BaseDTO {
    private String title;
    private String description;
    private double price;

    private ImageDTO cover;

    private List<TopicDTO> topics;

    public List<TopicDTO> getTopics() {
        return topics;
    }

    public void setTopics(List<TopicDTO> topics) {
        this.topics = topics;
    }

    public ImageDTO getCover() {
        return cover;
    }

    public void setCover(ImageDTO cover) {
        this.cover = cover;
    }



    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "PublicationDTO{" +
                "title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", cover=" + cover +
                ", topics=" + topics +
                '}';
    }

    public PublicationDTO() {
    }

    //TODO del
    public PublicationDTO(String title) {
        this.title = title;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PublicationDTO that = (PublicationDTO) o;
        return Double.compare(that.getId(), getId()) == 0 && Objects.equals(title, that.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, price);
    }
}
