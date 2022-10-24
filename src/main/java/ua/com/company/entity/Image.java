package ua.com.company.entity;

import java.io.Serializable;

public class Image extends BaseEntity  {
    private String name;
    private String path;



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    @Override
    public String toString() {
        return "Image{" +
                "name='" + name + '\'' +
                ", path='" + path + '\'' +
                super.toString()+
                '}';
    }
}
