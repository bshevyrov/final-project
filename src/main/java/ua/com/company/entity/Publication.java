package ua.com.company.entity;

import java.util.List;
import java.util.Objects;

public class Publication extends BaseEntity {
    private String title;
    private String description;
    private double price;
    private Image cover;
    private List<Topic> topics;

    public List<Topic> getTopics() {
        return topics;
    }

    public void setTopics(List<Topic> topics) {
        this.topics = topics;
    }

    public Image getCover() {
        return cover;
    }

    public void setCover(Image cover) {
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
        return "Publication{" +
                "title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", cover=" + cover +
                ", topics=" + topics +
                "} " + super.toString();
    }

    public Publication() {
    }

    public Publication(String title) {
        this.title = title;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Publication that = (Publication) o;
        return Double.compare(that.price, price) == 0 && Objects.equals(title, that.title) && Objects.equals(description, that.description) && Objects.equals(cover, that.cover) && Objects.equals(topics, that.topics);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, description, price, cover, topics);
    }
}
