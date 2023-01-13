package ua.com.company.utils;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import ua.com.company.entity.*;
import ua.com.company.type.RoleType;
import ua.com.company.type.StatusType;
import ua.com.company.view.dto.*;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class ClassConverterTest {

    private Image image;
    private ImageDTO imageDTO;
    private PersonAddress personAddress;
    private PersonAddressDTO personAddressDTO;
    private Publication publication;
    private PublicationDTO publicationDTO;
    private Topic topic;
    private TopicDTO topicDTO;
    private Person person;
    private PersonDTO personDTO;

    private PublicationComment publicationComment;
    private PublicationCommentDTO publicationCommentDTO;

    private List<Topic> topics;
    private List<TopicDTO> topicDTOS;
    private List<Publication> publications;
    private List<PublicationDTO> publicationDTOS;
    private int imageId = 1;
    private int personAddressId = 2;
    private int personId = 3;
    private int topicId = 4;
    private int publicationId = 5;
    private int publicationCommentId = 6;
    private String publicationCommentText = "publicationCommentText";

    private String imagePath = "Path";
    private String imageName = "Name";
    private Timestamp createDate = Timestamp.from(Instant.now());
    private Timestamp updateDate = Timestamp.from(Instant.now().plusSeconds(5 * 60));
    private String address = "Address";
    private double price = 22.11;
    private String publicationTitle = "Title";
    private String description = "Description";
    private String city = "City";
    private String country = "Country";
    private String firstName = "First Name";
    private String lastName = "Last Name";
    private String phone = "12345678901";
    private String personEmail = "email";
    private String personUsername = "username";
    private double personFunds = 999.12;
    private String personOpenPassword = "OpenPass";
    private String publicationDescription = "Publication Description";
    private int postalCode = 12345;
    private RoleType personRole = RoleType.ROLE_CUSTOMER;
    private StatusType personStatus = StatusType.ENABLED;

    private String topicTitle = "Topic Title";
    private double publicationPrice = 33.2;


    @BeforeAll
    void init() {

        topics = new LinkedList<>();
        topicDTOS = new LinkedList<>();


        image = new Image();
        image.setId(imageId);
        image.setPath(imagePath);
        image.setName(imageName);
        image.setCreateDate(createDate);
        image.setUpdateDate(updateDate);

        imageDTO = new ImageDTO();
        imageDTO.setId(imageId);
        imageDTO.setPath(imagePath);
        imageDTO.setName(imageName);
        imageDTO.setCreateDate(createDate);
        imageDTO.setUpdateDate(updateDate);

        personAddress = new PersonAddress();
        personAddress.setId(personAddressId);
        personAddress.setAddress(address);
        personAddress.setPersonId(personId);
        personAddress.setCity(city);
        personAddress.setCountry(country);
        personAddress.setFirstName(firstName);
        personAddress.setLastName(lastName);
        personAddress.setPhone(phone);
        personAddress.setPostalCode(postalCode);

        personAddressDTO = new PersonAddressDTO();
        personAddressDTO.setId(personAddressId);
        personAddressDTO.setAddress(address);
        personAddressDTO.setPersonId(personId);
        personAddressDTO.setCity(city);
        personAddressDTO.setCountry(country);
        personAddressDTO.setFirstName(firstName);
        personAddressDTO.setLastName(lastName);
        personAddressDTO.setPhone(phone);
        personAddressDTO.setPostalCode(postalCode);

        publication = new Publication();
        publication.setId(publicationId);
        publication.setCover(image);
        publication.setTopics(topics);
        publication.setPrice(price);
        publication.setTitle(publicationTitle);
        publication.setDescription(description);
        publication.setCreateDate(createDate);
        publication.setUpdateDate(updateDate);
        publication.setDescription(publicationDescription);
        publication.setPrice(publicationPrice);

        publicationDTO = new PublicationDTO();
        publicationDTO.setId(publicationId);
        publicationDTO.setCover(imageDTO);
        publicationDTO.setTopics(topicDTOS);
        publicationDTO.setPrice(price);
        publicationDTO.setTitle(publicationTitle);
        publicationDTO.setDescription(description);
        publicationDTO.setCreateDate(createDate);
        publicationDTO.setUpdateDate(updateDate);
        publicationDTO.setDescription(publicationDescription);
        publicationDTO.setPrice(publicationPrice);


        topic = new Topic();
        topic.setId(topicId);
        topic.setTitle(topicTitle);
        topic.setCreateDate(createDate);
        topic.setUpdateDate(updateDate);

        topicDTO = new TopicDTO();
        topicDTO.setId(topicId);
        topicDTO.setTitle(topicTitle);
        topicDTO.setCreateDate(createDate);
        topicDTO.setUpdateDate(updateDate);

        person = new Person();
        person.setId(personId);
        person.setEmail(personEmail);
        person.setFunds(personFunds);
        person.setUsername(personUsername);
        person.setAvatar(image);
        person.setRole(personRole);
        person.setStatus(personStatus);
        person.setPassword(personOpenPassword);
        person.setPersonAddress(personAddress);
        person.setPublications(publications);
        person.setCreateDate(createDate);
        person.setUpdateDate(updateDate);

        personDTO = new PersonDTO();
        personDTO.setId(personId);
        personDTO.setEmail(personEmail);
        personDTO.setFunds(personFunds);
        personDTO.setUsername(personUsername);
        personDTO.setAvatar(imageDTO);
        personDTO.setRole(personRole);
        personDTO.setStatus(personStatus);
        personDTO.setPassword(personOpenPassword);
        personDTO.setPersonAddressDTO(personAddressDTO);
        personDTO.setPublications(publicationDTOS);
        personDTO.setCreateDate(createDate);
        personDTO.setUpdateDate(updateDate);


        publicationComment = new PublicationComment();
        publicationComment.setPublicationId(publicationId);
        publicationComment.setPersonId(personId);
        publicationComment.setId(publicationCommentId);
        publicationComment.setUsername(personUsername);
        publicationComment.setText(publicationCommentText);
        publicationComment.setAvatarPath(imagePath);
        publicationComment.setCreateDate(createDate);
        publicationComment.setUpdateDate(updateDate);

        publicationCommentDTO = new PublicationCommentDTO();
        publicationCommentDTO.setPublicationId(publicationId);
        publicationCommentDTO.setPersonId(personId);
        publicationCommentDTO.setId(publicationCommentId);
        publicationCommentDTO.setUsername(personUsername);
        publicationCommentDTO.setText(publicationCommentText);
        publicationCommentDTO.setAvatarPath(imagePath);
        publicationCommentDTO.setCreateDate(createDate);
        publicationCommentDTO.setUpdateDate(updateDate);

        topics.add(topic);
        topicDTOS.add(topicDTO);

    }


    @Test
    void personAddressDTOToPersonAddress() {
        PersonAddressDTO personAddressDTO = ClassConverter.personAddressToPersonAddressDTO(personAddress);
        assertNotEquals(null, personAddressDTO);
        assertEquals(personAddressId, personAddressDTO.getId());
        assertEquals(address, personAddressDTO.getAddress());
        assertEquals(personId, personAddressDTO.getPersonId());
        assertEquals(city, personAddressDTO.getCity());
        assertEquals(country, personAddressDTO.getCountry());
        assertEquals(firstName, personAddressDTO.getFirstName());
        assertEquals(lastName, personAddressDTO.getLastName());
        assertEquals(phone, personAddressDTO.getPhone());
        assertEquals(postalCode, personAddressDTO.getPostalCode());
    }

    @Test
    void personAddressToPersonAddressDTO() {
        PersonAddress personAddress = ClassConverter.personAddressDTOToPersonAddress(personAddressDTO);
        assertNotEquals(null, personAddress);
        assertEquals(personAddressId, personAddress.getId());
        assertEquals(address, personAddress.getAddress());
        assertEquals(personId, personAddress.getPersonId());
        assertEquals(city, personAddress.getCity());
        assertEquals(country, personAddress.getCountry());
        assertEquals(firstName, personAddress.getFirstName());
        assertEquals(lastName, personAddress.getLastName());
        assertEquals(phone, personAddress.getPhone());
        assertEquals(postalCode, personAddress.getPostalCode());
    }

    @Test
    void topicDTOToTopic() {
        Topic topic = ClassConverter.topicDTOToTopic(topicDTO);
        assertNotEquals(null, topic);
        assertEquals(topicId, topic.getId());
        assertEquals(topicTitle, topic.getTitle());

    }

    @Test
    void topicToTopicDTO() {
        TopicDTO topicDTO = ClassConverter.topicToTopicDTO(topic);
        assertNotEquals(null, topicDTO);
        assertEquals(topicId, topicDTO.getId());
        assertEquals(topicTitle, topicDTO.getTitle());
        assertEquals(createDate, topicDTO.getCreateDate());
        assertEquals(updateDate, topicDTO.getUpdateDate());
    }

    @Test
    void personDTOToPerson() {
        Person person = ClassConverter.personDTOToPerson(personDTO);
        assertNotEquals(null, person);
        assertEquals(personId, person.getId());
        assertEquals(personFunds, person.getFunds());
        assertEquals(personEmail, person.getEmail());
        assertEquals(personUsername, person.getUsername());
        assertEquals(personOpenPassword, person.getPassword());
        assertEquals(image, person.getAvatar());
        assertEquals(publications, person.getPublications());
        assertEquals(personRole, person.getRole());
        assertEquals(personStatus, person.getStatus());
        assertEquals(personAddress, person.getPersonAddress());
    }

    @Test
    void personToPersonDTO() {
        PersonDTO personDTO = ClassConverter.personToPersonDTO(person);
        assertNotEquals(null, personDTO);
        assertEquals(personId, personDTO.getId());
        assertEquals(personFunds, personDTO.getFunds());
        assertEquals(personEmail, personDTO.getEmail());
        assertEquals(personUsername, personDTO.getUsername());
        assertEquals(personOpenPassword, personDTO.getPassword());
        assertEquals(imageDTO, personDTO.getAvatar());
        assertEquals(publicationDTOS, personDTO.getPublications());
        assertEquals(personRole, personDTO.getRole());
        assertEquals(personStatus, personDTO.getStatus());
        assertEquals(createDate, personDTO.getCreateDate());
        assertEquals(updateDate, personDTO.getUpdateDate());
        assertEquals(personAddressDTO, personDTO.getPersonAddressDTO());

    }

    @Test
    void imageToImageDTO() {
        ImageDTO imageDTO = ClassConverter.imageToImageDTO(image);
        assertNotEquals(null, imageDTO);
        assertEquals(imageId, imageDTO.getId());
        assertEquals(imagePath, imageDTO.getPath());
        assertEquals(imageName, imageDTO.getName());
        assertEquals(createDate, imageDTO.getCreateDate());
        assertEquals(updateDate, imageDTO.getUpdateDate());
    }


    @Test
    void imageDTOToImage() {
        Image image = ClassConverter.imageDTOToImage(imageDTO);
        assertNotEquals(null, imageDTO);
        assertEquals(imageId, image.getId());
        assertEquals(imagePath, image.getPath());
        assertEquals(imageName, image.getName());

    }

    @Test
    void publicationCommentToPublicationCommentDTO() {
        PublicationCommentDTO publicationCommentDTO = ClassConverter.publicationCommentToPublicationCommentDTO(publicationComment);
        assertNotEquals(null, publicationCommentDTO);
        assertEquals(publicationId, publicationCommentDTO.getPublicationId());
        assertEquals(personId, publicationCommentDTO.getPersonId());
        assertEquals(publicationCommentId, publicationCommentDTO.getId());
        assertEquals(personUsername, publicationCommentDTO.getUsername());
        assertEquals(publicationCommentText, publicationCommentDTO.getText());
        assertEquals(imagePath, publicationCommentDTO.getAvatarPath());
        assertEquals(createDate, publicationCommentDTO.getCreateDate());
        assertEquals(updateDate, publicationCommentDTO.getUpdateDate());
    }

    @Test
    void publicationCommentDTOToPublicationComment() {
        PublicationComment publicationComment = ClassConverter.publicationCommentDTOToPublicationComment(publicationCommentDTO);
        assertNotEquals(null, publicationComment);
        assertEquals(publicationId, publicationComment.getPublicationId());
        assertEquals(personId, publicationComment.getPersonId());
        assertEquals(publicationCommentId, publicationComment.getId());
        assertEquals(personUsername, publicationComment.getUsername());
        assertEquals(publicationCommentText, publicationComment.getText());
        assertEquals(imagePath, publicationComment.getAvatarPath());

    }

    @Test
    void publicationDTOToPublication() {
        Publication publication = ClassConverter.publicationDTOToPublication(publicationDTO);

        assertNotEquals(null, publication);
        assertEquals(publicationId, publication.getId());
        assertEquals(publicationTitle, publication.getTitle());
        assertEquals(publicationDescription, publication.getDescription());
        assertEquals(image, publication.getCover());
        assertEquals(publicationPrice, publication.getPrice());
        assertEquals(topics, publication.getTopics());


    }

    @Test
    void publicationToPublicationDTO() {
        PublicationDTO publicationDTO = ClassConverter.publicationToPublicationDTO(publication);

        assertNotEquals(null, publicationDTO);
        assertEquals(publicationId, publicationDTO.getId());
        assertEquals(publicationTitle, publicationDTO.getTitle());
        assertEquals(publicationDescription, publicationDTO.getDescription());
        assertEquals(imageDTO, publicationDTO.getCover());
        assertEquals(publicationPrice, publicationDTO.getPrice());
        assertEquals(topicDTOS, publicationDTO.getTopics());
        assertEquals(createDate, publicationDTO.getCreateDate());
        assertEquals(updateDate, publicationDTO.getUpdateDate());
    }
}