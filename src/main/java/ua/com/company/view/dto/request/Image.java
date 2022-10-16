package ua.com.company.view.dto.request;

import java.io.Serializable;

public class Image  implements Serializable {
    private String name;
    private String path;
    private int publicationId;

    public int getPublicationId() {
        return publicationId;
    }

    public void setPublicationId(int publicationId) {
        this.publicationId = publicationId;
    }

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
                ", publicationId=" + publicationId +
                '}';
    }
}
