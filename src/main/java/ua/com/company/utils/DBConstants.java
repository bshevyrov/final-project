package ua.com.company.utils;

public abstract class DBConstants {


    public static final String CREATE_PERSON = "INSERT INTO person (username,email,password) VALUES (?,?,?)";
    public static final String FIND_PERSON_BY_EMAIL = "SELECT p.*,r.name,ps.description, GROUP_CONCAT(p2.id) as publications_id FROM person p LEFT JOIN person_has_publication php on p.id = php.person_id  LEFT JOIN publication p2 on php.publication_id = p2.id INNER JOIN role r on p.role_id = r.id INNER JOIN  person_status ps  on p.status_id = ps.id WHERE (php.publication_id IS NOT NULL OR  php.publication_id IS NULL) AND p.email=? GROUP BY p.id";
    public static final String FIND_PERSON_BY_ID = "SELECT p.*,r.name,ps.description  FROM person p INNER JOIN role r on p.role_id = r.id INNER JOIN  person_status ps  on p.status_id = ps.id WHERE  p.id=?";
    public static final String FIND_ALL_PERSONS = "SELECT p.*,r.name,ps.description FROM person p  INNER JOIN role r on p.role_id = r.id INNER JOIN  person_status ps  on p.status_id = ps.id   WHERE  r.name='ROLE_CUSTOMER'";
    public static final String DELETE_PERSON = "DELETE FROM person WHERE id=?";
    public static final String COUNT_PERSON_BY_USERNAME = "SELECT COUNT(id) AS count FROM person WHERE username=?";
    public static final String COUNT_PERSON_BY_EMAIL = "SELECT COUNT(id) AS count FROM person WHERE email=?";
    public static final String COUNT_PERSON_BY_ID = "SELECT COUNT(id) AS count FROM person WHERE id=?";
    public static final String DELETE_ORPHAN_TOPIC = "DELETE topic FROM topic  LEFT JOIN publication_has_topic pht ON topic.id = pht.topic_id WHERE pht.topic_id IS NULL";
    public static final String UPDATE_PERSON = "UPDATE person SET email = ?,role_id = (SELECT id FROM role WHERE name=?),status_id = (SELECT id FROM person_status WHERE description=?), image_id =? WHERE id = ?";
    public static final String FIND_PUBLICATION_BY_ID = "SELECT * FROM publication WHERE id=?";
    public static final String FIND_ALL_PUBLICATIONS = "SELECT *  FROM publication p ";
    public static final String CREATE_PUBLICATION = "INSERT INTO publication (title,description,price,image_id) VALUES (?,?,?,?)";
    public static final String DELETE_PUBLICATION = "DELETE FROM publication WHERE id=?";
    public static final String UPDATE_PUBLICATION = "UPDATE publication SET update_date = now(), title=?, description=?, price=?, image_id=? WHERE id=?";
    public static final String CREATE_TOPIC = "INSERT INTO topic (title) VALUES (?)";
    public static final String FIND_ALL_TOPICS = "SELECT * FROM topic ORDER BY title";
    public static final String DELETE_TOPIC = "DELETE FROM topic WHERE id=?";
    public static final String UPDATE_TOPIC = "UPDATE topic SET title=? WHERE id =?";
    public static final String FIND_TOPIC_BY_ID = "SELECT * FROM topic WHERE id=?";
    public static final String FIND_PUBLICATION_BY_TITLE = "SELECT * FROM publication p  WHERE p.title=?";
    public static final String ADD_TOPIC_TO_PUBLICATION = "INSERT INTO publication_has_topic (publication_id,topic_id) VALUES (?,?)";
    public static final String FIND_TOPIC_BY_TITLE = "SELECT * FROM topic WHERE title=?";
    public static final String CHANGE_USER_STATUS_BY_ID = "UPDATE person SET status_id=? WHERE id=?";
    public static final String CHECK_USER_STATUS_BY_ID = "SELECT status_id FROM person WHERE id = ?";
    public static final String DELETE_PUBLICATION_HAS_TOPIC_BY_PUBLICATION_ID = "DELETE FROM publication_has_topic WHERE publication_id = ?";
    public static final String FIND_ALL_TOPIC_BY_PUBLICATION_ID = "SELECT t.id , t.title  FROM topic t LEFT JOIN publication_has_topic pht on t.id = pht.topic_id WHERE publication_id = ?";
    public static final String CREATE_IMAGE = "INSERT INTO image (name, path) VALUES (?,?)";
    public static final String UPDATE_IMAGE = "UPDATE image SET name = ?, path = ? WHERE id = ?";
    public static final String DELETE_IMAGE = "DELETE FROM image WHERE id =?";
    public static final String FIND_IMAGE_BY_ID = "SELECT * FROM image WHERE id =?";
    public static final String FIND_ALL_IMAGES = "SELECT id, name, path, create_date, update_date FROM image";
    public static final String FIND_IMAGE_BY_PUBLICATION_ID = "SELECT i.name, i.path FROM image i LEFT JOIN publication_has_image phi on i.id = phi.image_id WHERE phi.publication_id = ?";
    public static final String F_IMAGE_ID = "id";
    public static final String F_PUBLICATION_IMAGE_ID = "image_id";
    public static final String F_IMAGE_CREATE_DATE = "create_date";
    public static final String F_PERSON_CREATE_DATE = "create_date";
    public static final String F_IMAGE_UPDATE_DATE = "update_date";
    public static final String F_PERSON_UPDATE_DATE = "update_date";
    public static final String COUNT_PUBLICATION_BY_TOPIC_ID = "SELECT COUNT(publication_id) AS count FROM publication_has_topic WHERE topic_id=?";
    public static final String UPDATE_PERSON_FUNDS = "UPDATE person SET funds=? WHERE id=?";
    public static final String CREATE_PERSON_HAS_PUBLICATION = "INSERT INTO person_has_publication (person_id, publication_id) VALUES (?,?)";
    public static final String COUNT_PUBLICATION_BY_USER_ID = "SELECT COUNT(publication_id) AS count FROM person_has_publication WHERE person_id=?";
    public static final String COUNT_PUBLICATION_BY_TITLE = "SELECT COUNT(id) AS count FROM publication WHERE title=?";
    public static final String F_PUBLICATION_COMMENT_UPDATE_DATE = "update_date";
    public static final String F_PUBLICATION_COMMENT_TEXT = "text";
    public static final String CREATE_PUBLICATION_COMMENT = "INSERT INTO publication_comment (publication_id, person_id,text) VALUES (?,?,?)";
    public static final String F_PERSON_IMAGE_ID = "image_id";
    public static final String CREATE_PERSON_ADDRESS = "INSERT INTO person_address (first_name, last_name, address, city, country, phone, postal_code,person_id) VALUES (?,?,?,?,?,?,?,?)";
    public static final String UPDATE_PERSON_ADDRESS = "UPDATE person_address SET first_name=?, last_name=?, address=?, city=?, country=?, phone=?, postal_code=? WHERE id=?";
    public static final String F_PERSON_ADDRESS_ID = "id";
    public static final String F_PERSON_ADDRESS_FIRST_NAME = "first_name";
    public static final String F_PERSON_ADDRESS_LAST_NAME = "last_name";
    public static final String F_PERSON_ADDRESS_ADDRESS = "address";
    public static final String F_PERSON_ADDRESS_CITY = "city";
    public static final String F_PERSON_ADDRESS_COUNTRY = "country";
    public static final String F_PERSON_ADDRESS_PHONE = "phone";
    public static final String F_PERSON_ADDRESS_POSTAL_CODE = "postal_code";
    public static final String F_PERSON_ADDRESS_CREATE_DATE = "create_date";
    public static final String F_PERSON_ADDRESS_UPDATE_DATE = "update_date";
    public static final String F_PERSON_ADDRESS_PERSON_ID = "person_id";
    public static final String FIND_PERSON_ADDRESS_BY_PERSON_ID = "SELECT * FROM person_address WHERE person_id=?";
    public static final String FIND_PERSON_ADDRESS_BY_ID = "SELECT * FROM person_address WHERE id=?";
    public static final String F_PUBLICATION_COMMENT_CREATE_DATE = "create_date";
    public static final String COUNT_PUBLICATION_COMMENT_BY_PUBLICATION_ID = "SELECT COUNT(id) AS count FROM publication_comment WHERE publication_id=?";
    public static final String FIND_ALL_PUBLICATIONS_BY_PERSON_ID = "SELECT * FROM publication p LEFT JOIN person_has_publication php on p.id = php.publication_id INNER JOIN person pers on php.person_id = pers.id WHERE pers.id = ?";
    public static final String FIND_IMAGE_BY_PATH = "SELECT * FROM image WHERE path=?";
    public static final String UPDATE_PERSON_AVATAR = "UPDATE person SET image_id=? WHERE id=?";
    public static final String UPDATE_PUBLICATION_IMAGE = "UPDATE publication SET image_id=? WHERE  id=?";
    public static final String COUNT_ALL_PUBLICATIONS = "SELECT COUNT(id) AS count FROM publication";

    private DBConstants() {
    }


    public static final String F_PERSON_STATUS_ID = "status_id";
    public static final String F_IMAGE_NAME = "name";
    public static final String F_IMAGE_PATH = "path";
    public static final String F_PERSON_ID = "id";
    public static final String F_PERSON_EMAIL = "email";
    public static final String F_PERSON_USERNAME = "username";
    public static final String F_PERSON_PASSWORD = "password";
    public static final String F_PERSON_FUNDS = "funds";
    public static final String F_PERSON_ROLE = "name";
    public static final String F_PERSON_STATUS = "description";
    public static final String F_TOPIC_ID = "id";
    public static final String F_TOPIC_TITLE = "title";
    public static final String F_PUBLICATION_ID = "id";
    public static final String F_PUBLICATION_TITLE = "title";
    public static final String F_PUBLICATION_DESCRIPTION = "description";
    public static final String F_PUBLICATION_PRICE = "price";

}
