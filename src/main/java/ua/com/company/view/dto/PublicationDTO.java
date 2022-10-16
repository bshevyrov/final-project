package ua.com.company.view.dto;

import java.util.List;

public class PublicationDTO extends BaseDTO {
    private String title;
    private String description;
    private double price;

    private List<ImageDTO> images;
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

    public List<ImageDTO> getImages() {
        return images;
    }

    public void setImages(List<ImageDTO> images) {
        this.images = images;
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
        return "Publication{" +
                "title='" + title + '\'' +
                ", sample='" + description + '\'' +
                ", price=" + price +
                '}';
    }

    public PublicationDTO() {
    }

    public PublicationDTO(String title) {
        this.title = title;
    }
}
