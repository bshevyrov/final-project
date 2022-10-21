package ua.com.company.utils;

import ua.com.company.entity.Image;
import ua.com.company.entity.Person;
import ua.com.company.entity.Publication;
import ua.com.company.entity.Topic;
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
        imageDTO.setPublicationId(image.getPublicationId());
        imageDTO.setName(image.getName());
        imageDTO.setPath(image.getPath());
        return imageDTO;
    }

    public static TopicDTO topicToTopicDTO(Topic topic) {
        TopicDTO topicDTO = new TopicDTO();
        topicDTO.setId(topic.getId());
        topicDTO.setTitle(topic.getTitle());
        topicDTO.setCreateDate(topic.getCreateDate());
        topicDTO.setUpdateDate(topic.getUpdateDate());
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

    public static Topic topicDTOTotopic(TopicDTO topicDTO) {
        Topic topic = new Topic();
        topic.setId(topicDTO.getId());
        topic.setTitle(topicDTO.getTitle());
        return topic;
    }

    public static Publication publicationDTOToPublication(PublicationDTO publicationDTO) {
        Publication publication = new Publication();
        publication.setId(publicationDTO.getId());
        publication.setTitle(publicationDTO.getTitle());
        publication.setDescription(publicationDTO.getDescription());
        publication.setPrice(publicationDTO.getPrice());
        publication.setTopics(publicationDTO.getTopics().stream()
                .map(ClassConverter::topicDTOTotopic)
                .collect(Collectors.toList()));
        publication.setCover(ClassConverter.imageDTOToImage(publicationDTO.getCover()));
        return publication;
    }

    private static Image imageDTOToImage(ImageDTO cover) {
        Image image = new Image();
        image.setPublicationId(cover.getPublicationId());
        image.setName(cover.getPath());
        image.setPath(cover.getPath());
        return image;
    }
}
