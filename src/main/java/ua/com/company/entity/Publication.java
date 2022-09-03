package ua.com.company.entity;

import java.util.List;
import java.util.Map;

public class Publication extends BaseEntity {
    private String title;
    private String sample;
    private double price;



    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSample() {
        return sample;
    }

    public void setSample(String sample) {
        this.sample = sample;
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
                ", sample='" + sample + '\'' +
                ", price=" + price +
                '}';
    }
}
