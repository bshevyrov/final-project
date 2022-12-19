package ua.com.company.utils;

import org.apache.logging.log4j.core.config.plugins.convert.TypeConverters;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ua.com.company.entity.Image;
import ua.com.company.entity.Person;
import ua.com.company.entity.Publication;
import ua.com.company.entity.Topic;
import ua.com.company.type.RoleType;
import ua.com.company.type.StatusType;
import ua.com.company.view.dto.*;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

class ClassConverterTest {

    @Test
    void personToPersonDTO() {
//        Person person = new Person();
//        person.setFunds(99);
//        person.setUsername("username");
//        person.setEmail("mail@mail.com");
//        person.setPublicationsId(new int[]{1,2});
//        person.setPassword("pass");
//        person.setStatus(StatusType.DISABLED);
//        person.setRole(RoleType.ROLE_CUSTOMER);
//        person.setCreateDate(Timestamp.from(Instant.now()));
//        person.setUpdateDate(Timestamp.from(Instant.now()));
//
//        PersonDTO personDTO = ClassConverter.personToPersonDTO(person);
//
//        Assertions.assertNotNull(personDTO);
//        Assertions.assertEquals(person.getFunds(),personDTO.getFunds());
//        Assertions.assertEquals(person.getUsername(),personDTO.getUsername());
//        Assertions.assertEquals(person.getEmail(),personDTO.getEmail());
//        Assertions.assertEquals(person.getPassword(),personDTO.getPassword());
//        Assertions.assertEquals(person.getStatus(),personDTO.getStatus());
//        Assertions.assertEquals(person.getRole(),personDTO.getRole());
//        Assertions.assertEquals(person.getCreateDate(),personDTO.getCreateDate());
//        Assertions.assertEquals(person.getUpdateDate(),personDTO.getUpdateDate());

    }

    @Test
    void personDTOToPerson() {
        PersonDTO personDTO = new PersonDTO();
        personDTO.setFunds(99);
        personDTO.setUsername("username");
        personDTO.setEmail("mail@mail.com");
        personDTO.setPassword("pass");
        PublicationDTO pubOne = new PublicationDTO();
        pubOne.setId(1);
        PublicationDTO pubTwo = new PublicationDTO();
        pubTwo.setId(2);
        personDTO.setPublications(Arrays.asList(pubOne,pubTwo));
        Person person = ClassConverter.personDTOToPerson(personDTO);
            int[] personDTOPubId = personDTO.getPublications().stream()
                .mapToInt(BaseDTO::getId)
                .toArray();

        Assertions.assertNotNull(person);
        Assertions.assertEquals(personDTO.getFunds(),person.getFunds());
        Assertions.assertEquals(personDTO.getUsername(),person.getUsername());
        Assertions.assertEquals(personDTO.getEmail(),person.getEmail());
        Assertions.assertEquals(personDTO.getPassword(),person.getPassword());
//        Assertions.assertArrayEquals(personDTOPubId,person.getPublicationsId());

    }

    @Test
    void publicationToPublicationDTO() {
        Publication publication = new Publication();
        publication.setId(1);
        publication.setTitle("title");
        publication.setDescription("description");
        Image cover = new Image();
        cover.setId(1);
        cover.setPath("path");
        cover.setName("name");
        publication.setCover(cover);
        publication.setPrice(99.99);
        publication.setCreateDate(Timestamp.from(Instant.now()));
        publication.setUpdateDate(Timestamp.from(Instant.now()));
        Topic topicOne = new Topic();
        topicOne.setId(1);
        topicOne.setTitle("title1");
        Topic topicTwo = new Topic();
        topicTwo.setId(2);
        topicTwo.setTitle("title2");
        publication.setTopics(Arrays.asList(topicOne,topicTwo));

        PublicationDTO publicationDTO = ClassConverter.publicationToPublicationDTO(publication);

        Assertions.assertNotNull(publication);
        Assertions.assertEquals(publication.getId(),publicationDTO.getId());
        Assertions.assertEquals(publication.getTitle(),publicationDTO.getTitle());
        Assertions.assertEquals(publication.getCreateDate(),publicationDTO.getCreateDate());
        Assertions.assertEquals(publication.getUpdateDate(),publicationDTO.getUpdateDate());
        Assertions.assertEquals(publication.getDescription(),publicationDTO.getDescription());
        Assertions.assertEquals(publication.getCover().hashCode(),publicationDTO.getCover().hashCode());
        Assertions.assertEquals(publication.getTopics().hashCode(),publicationDTO.getTopics().hashCode());
    }

    @Test
    void publicationDTOToPublication() {
        PublicationDTO publicationDTO = new PublicationDTO();
        publicationDTO.setId(1);
        publicationDTO.setTitle("title");
        publicationDTO.setDescription("description");
        ImageDTO cover = new ImageDTO();
        cover.setId(1);
        cover.setPath("path");
        cover.setName("name");
        publicationDTO.setCover(cover);
        publicationDTO.setPrice(99.99);
        TopicDTO topicDTOOne = new TopicDTO();
        topicDTOOne.setId(1);
        topicDTOOne.setTitle("title1");
        TopicDTO topicDTOTwo = new TopicDTO();
        topicDTOTwo.setId(2);
        topicDTOTwo.setTitle("title2");
        publicationDTO.setTopics(Arrays.asList(topicDTOOne,topicDTOTwo));

        Publication publication = ClassConverter.publicationDTOToPublication(publicationDTO);

        Assertions.assertNotNull(publication);
        Assertions.assertEquals(publicationDTO.getId(),publication.getId());
        Assertions.assertEquals(publicationDTO.getTitle(),publication.getTitle());
        Assertions.assertEquals(publicationDTO.getDescription(),publication.getDescription());
        Assertions.assertEquals(publicationDTO.getCover().hashCode(),publication.getCover().hashCode());
        Assertions.assertEquals(publicationDTO.getTopics().hashCode(),publication.getTopics().hashCode());
    }

    @Test
    void imageToImageDTO() {
        Image image = new Image();
        image.setId(1);
        image.setName("name");
        image.setPath("path");
        image.setCreateDate(Timestamp.from(Instant.now()));
        image.setUpdateDate(Timestamp.from(Instant.now()));
        ImageDTO imageDTO = ClassConverter.imageToImageDTO(image);

        Assertions.assertNotNull(imageDTO);
        Assertions.assertEquals(image.getId(),imageDTO.getId());
        Assertions.assertEquals(image.getName(),imageDTO.getName());
        Assertions.assertEquals(image.getPath(),imageDTO.getPath());
        Assertions.assertEquals(image.getCreateDate(),imageDTO.getCreateDate());
        Assertions.assertEquals(image.getUpdateDate(),imageDTO.getUpdateDate());

    }


    @Test
    void topicToTopicDTO() {
        Topic topic = new Topic();
        topic.setId(1);
        topic.setTitle("title");

        TopicDTO topicDTO = ClassConverter.topicToTopicDTO(topic);

        Assertions.assertNotNull(topicDTO);
        Assertions.assertEquals(topic.getId(),topicDTO.getId());
        Assertions.assertEquals(topic.getTitle(),topicDTO.getTitle());
    }

    @Test
    void topicDTOToTopic() {
        TopicDTO topicDTO = new TopicDTO();
        topicDTO.setId(1);
        topicDTO.setTitle("title");

        Topic topic = ClassConverter.topicDTOToTopic(topicDTO);

        Assertions.assertNotNull(topic);
        Assertions.assertEquals(topicDTO.getId(),topic.getId());
        Assertions.assertEquals(topicDTO.getTitle(),topic.getTitle());
    }
}