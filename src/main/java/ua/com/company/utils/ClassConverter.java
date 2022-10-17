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
        personDTO.setRole(person.getRole());
        personDTO.setStatus(person.getStatus());
        personDTO.setFunds(person.getFunds());
        personDTO.setCreateDate(person.getCreateDate());
        personDTO.setUpdateDate(person.getUpdateDate());
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
//        int[] publicationsId = new int[personDTO.getPublications().size()];
//        int index = 0;
        person.setPublicationsId(personDTO.getPublications().stream()
                //  .map(BaseDTO::getId)
                .mapToInt(BaseDTO::getId)
                .toArray());
//        for (PublicationDTO publication : personDTO.getPublications()) {
//            publicationsId[++index] = publication.getId();
//        }
//        person.setPublicationsId(publicationsId);
        return person;
    }

    public static Topic topicDTOTotopic(TopicDTO topicDTO) {
        Topic topic = new Topic();
        topic.setId(topicDTO.getId());
        topic.setTitle(topicDTO.getTitle());
        return topic;
    }
}
