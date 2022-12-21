package ua.com.company.entity;

import java.io.Serializable;
import java.util.Objects;
//todo seriazible
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Image image = (Image) o;
        return Objects.equals(name, image.name) && Objects.equals(path, image.path);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, path);
    }
}
