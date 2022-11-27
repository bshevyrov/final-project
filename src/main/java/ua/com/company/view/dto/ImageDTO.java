package ua.com.company.view.dto;

import java.io.Serializable;
import java.util.Objects;

public class ImageDTO extends BaseDTO {
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
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ImageDTO imageDTO = (ImageDTO) o;
        return Objects.equals(name, imageDTO.name) && Objects.equals(path, imageDTO.path);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, path);
    }
}
