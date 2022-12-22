package ua.com.company.utils;

import ua.com.company.entity.*;
import ua.com.company.view.dto.*;

import java.util.List;
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
        if (person.getAvatar() != null) {
            personDTO.setAvatar(imageToImageDTO(person.getAvatar()));
        }
        if (person.getPublications() != null) {
            List<PublicationDTO> publicationDTOList = person.getPublications().stream()
                    .map(ClassConverter::publicationToPublicationDTO)
                    .collect(Collectors.toList());
            personDTO.setPublications(publicationDTOList);
        }
        if (person.getPersonAddress() != null) {
            personDTO.setPersonAddressDTO(ClassConverter.personAddressToPersonAddressDTO(person.getPersonAddress()));
        }
        return personDTO;
    }

    public static PersonAddressDTO personAddressToPersonAddressDTO(PersonAddress personAddress) {
        PersonAddressDTO personAddressDTO = new PersonAddressDTO();
        personAddressDTO.setAddress(personAddress.getAddress());
        personAddressDTO.setPersonId(personAddress.getPersonId());
        personAddressDTO.setCity(personAddress.getCity());
        personAddressDTO.setCountry(personAddress.getCountry());
        personAddressDTO.setFirstName(personAddress.getFirstName());
        personAddressDTO.setLastName(personAddress.getLastName());
        personAddressDTO.setPhone(personAddress.getPhone());
        personAddressDTO.setPostalCode(personAddress.getPostalCode());
        personAddressDTO.setId(personAddress.getId());
        return personAddressDTO;
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
        if (publication.getTopics() != null) {
            publicationDTO.setTopics(publication.getTopics().stream()
                    .map(ClassConverter::topicToTopicDTO)
                    .collect(Collectors.toList()));
        }
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
            List<Publication> publicationList = personDTO.getPublications().stream()
                    .map(ClassConverter::publicationDTOToPublication)
                    .collect(Collectors.toList());

            person.setPublications(publicationList);
        }
        if (personDTO.getAvatar() != null) {
            person.setAvatar(imageDTOToImage(personDTO.getAvatar()));
        }
        if (personDTO.getPersonAddressDTO() != null) {
            person.setPersonAddress(ClassConverter.personAddressDTOToPersonAddress(personDTO.getPersonAddressDTO()));
        }


        return person;
    }

    public static PersonAddress personAddressDTOToPersonAddress(PersonAddressDTO personAddressDTO) {
        PersonAddress personAddress = new PersonAddress();
        personAddress.setAddress(personAddressDTO.getAddress());
        personAddress.setPersonId(personAddressDTO.getPersonId());
        personAddress.setCity(personAddressDTO.getCity());
        personAddress.setCountry(personAddressDTO.getCountry());
        personAddress.setFirstName(personAddressDTO.getFirstName());
        personAddress.setLastName(personAddressDTO.getLastName());
        personAddress.setPhone(personAddressDTO.getPhone());
        personAddress.setPostalCode(personAddressDTO.getPostalCode());
        personAddress.setId(personAddressDTO.getId());
        return personAddress;
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

    public static PublicationCommentDTO publicationCommentToPublicationCommentDTO(PublicationComment comment) {
        PublicationCommentDTO publicationCommentDTO = new PublicationCommentDTO();
        publicationCommentDTO.setAvatarPath(comment.getAvatarPath());
        publicationCommentDTO.setText(comment.getText());
        publicationCommentDTO.setUserName(comment.getUsername());
        publicationCommentDTO.setCreateDate(comment.getCreateDate());
        publicationCommentDTO.setUpdateDate(comment.getUpdateDate());
        publicationCommentDTO.setPublicationId(comment.getPublicationId());
        publicationCommentDTO.setPersonId(comment.getPersonId());
        return publicationCommentDTO;


    }

    public static PublicationComment publicationCommentDTOToPublicationComment(PublicationCommentDTO publicationCommentDTO) {
        PublicationComment publicationComment = new PublicationComment();
        publicationComment.setPublicationId(publicationCommentDTO.getId());
        publicationComment.setText(publicationCommentDTO.getText());
        publicationComment.setPersonId(publicationCommentDTO.getPersonId());
        publicationComment.setAvatarPath(publicationCommentDTO.getAvatarPath());
        publicationComment.setPublicationId(publicationCommentDTO.getPublicationId());
        publicationComment.setPersonId(publicationCommentDTO.getPersonId());
        return publicationComment;
    }
}
