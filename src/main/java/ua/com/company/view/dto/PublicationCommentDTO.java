package ua.com.company.view.dto;

import java.sql.Timestamp;

public class PublicationCommentDTO extends BaseDTO{

    private String userName;
    private String avatarPath;
    private String text;

    private int publicationId;

    private int personId;


    public int getPublicationId() {
        return publicationId;
    }

    public void setPublicationId(int publicationId) {
        this.publicationId = publicationId;
    }

    public int getPersonId() {
        return personId;
    }

    public void setPersonId(int personId) {
        this.personId = personId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getAvatarPath() {
        return avatarPath;
    }

    public void setAvatarPath(String avatarPath) {
        this.avatarPath = avatarPath;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public PublicationCommentDTO() {
    }

    @Override
    public String toString() {
        return "PublicationComment{" +
                "userName='" + userName + '\'' +
                ", avatarPath='" + avatarPath + '\'' +
                ", text='" + text + '\'' +
                ", publicationId=" + publicationId +
                ", personId=" + personId +
                '}';
    }
}


