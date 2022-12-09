package ua.com.company.utils;

import ua.com.company.entity.*;
import ua.com.company.view.dto.*;

import java.util.stream.Collectors;

public final class ClassConverter {
    private ClassConverter() {
    }

    public static PersonDTO personToPersonDTO(Person person) {
        PersonDTO personDTO = new PersonDTO();
        personDTO.setId(person.getId());
        personDTO.setEmail(person.getEmail());
        personDTO.setUsername(person.getUsername());
        personDTO.setPassword(person.getPassword());
        personDTO.setRole(person.getRole());
        personDTO.setStatus(person.getStatus());
        personDTO.setFunds(person.getFunds());
        personDTO.setCreateDate(person.getCreateDate());
        personDTO.setUpdateDate(person.getUpdateDate());
        if(person.getAvatar()!=null){
            personDTO.setAvatar(imageToImageDTO(person.getAvatar()));
        }
        return personDTO;
    }

    public static Publication publicationDTOToPublication(PublicationDTO publicationDTO) {
        Publication publication = new Publication();
        publication.setId(publicationDTO.getId());
        publication.setTitle(publicationDTO.getTitle());
        publication.setDescription(publicationDTO.getDescription());
        publication.setPrice(publicationDTO.getPrice());
        publication.setTopics(publicationDTO.getTopics().stream()
                .map(ClassConverter::topicDTOToTopic)
                .collect(Collectors.toList()));
        publication.setCover(ClassConverter.imageDTOToImage(publicationDTO.getCover()));
        return publication;
    }

    public static PublicationDTO publicationToPublicationDTO(Publication publication) {
        PublicationDTO publicationDTO = new PublicationDTO();
        publicationDTO.setId(publication.getId());
        publicationDTO.setTitle(publication.getTitle());
        publicationDTO.setDescription(publication.getDescription());
        publicationDTO.setCover(imageToImageDTO(publication.getCover()));
        publicationDTO.setPrice(publication.getPrice());
        publicationDTO.setCreateDate(publication.getCreateDate());
        publicationDTO.setUpdateDate(publication.getUpdateDate());
        publicationDTO.setTopics(publication.getTopics().stream()
                .map(ClassConverter::topicToTopicDTO)
                .collect(Collectors.toList()));
        return publicationDTO;
    }

    public static ImageDTO imageToImageDTO(Image image) {
        ImageDTO imageDTO = new ImageDTO();
        imageDTO.setId(image.getId());
        imageDTO.setName(image.getName());
        imageDTO.setPath(image.getPath());
        imageDTO.setCreateDate(image.getCreateDate());
        imageDTO.setUpdateDate(image.getUpdateDate());
        return imageDTO;
    }

    public static Topic topicDTOToTopic(TopicDTO topicDTO) {
        Topic topic = new Topic();
        topic.setId(topicDTO.getId());
        topic.setTitle(topicDTO.getTitle());
        return topic;
    }

    public static TopicDTO topicToTopicDTO(Topic topic) {
        TopicDTO topicDTO = new TopicDTO();
        topicDTO.setId(topic.getId());
        topicDTO.setTitle(topic.getTitle());
        return topicDTO;
    }

    public static Person personDTOToPerson(PersonDTO personDTO) {
        Person person = new Person();
        person.setId(personDTO.getId());
        person.setUsername(personDTO.getUsername());
        person.setEmail(personDTO.getEmail());
        person.setFunds(personDTO.getFunds());
        if (personDTO.getPassword() != null) {
            person.setPassword(personDTO.getPassword());
        }
        if (personDTO.getPublications() != null) {
            person.setPublicationsId(personDTO.getPublications().stream()
                    .mapToInt(BaseDTO::getId)
                    .toArray());
//        } else {
//            person.setPublicationsId(new int[0]);
            if(personDTO.getAvatar()!=null){
                person.setAvatar(imageDTOToImage(personDTO.getAvatar()));
            }
        }

        return person;
    }

    private static Image imageDTOToImage(ImageDTO cover) {
        Image image = new Image();
        image.setId(cover.getId());
        image.setName(cover.getName());
        image.setPath(cover.getPath());
        image.setCreateDate(cover.getCreateDate());
        image.setUpdateDate(cover.getUpdateDate());
        return image;
    }

    public static PublicationCommentDTO publicationCommentToPublicationCommentDTO(PublicationComment comment){
        PublicationCommentDTO publicationCommentDTO = new PublicationCommentDTO();
        publicationCommentDTO.setAvatarPath(comment.getAvatarPath());
        publicationCommentDTO.setText(comment.getText());
        publicationCommentDTO.setUserName(comment.getUserName());
        publicationCommentDTO.setUpdateDate(comment.getUpdateDate());
        return publicationCommentDTO;



    }
}
