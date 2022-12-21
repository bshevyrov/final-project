package ua.com.company.entity;

public class PublicationComment extends BaseEntity {
    private String username;
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    public PublicationComment() {
    }

    @Override
    public String toString() {
        return "PublicationComment{" +
                "username='" + username + '\'' +
                ", avatarPath='" + avatarPath + '\'' +
                ", text='" + text + '\'' +
                ", publicationId=" + publicationId +
                ", personId=" + personId +
                "} " + super.toString();
    }
}
