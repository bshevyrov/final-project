package ua.com.company.entity;

import ua.com.company.entity.BaseEntity;

public class PublicationTopic extends BaseEntity {
    private int publicationId;
    private int topicId;

    public int getPublicationId() {
        return publicationId;
    }

    public void setPublicationId(int publicationId) {
        this.publicationId = publicationId;
    }

    public int getTopicId() {
        return topicId;
    }

    public void setTopicId(int topicId) {
        this.topicId = topicId;
    }

    public PublicationTopic() {
    }
}
