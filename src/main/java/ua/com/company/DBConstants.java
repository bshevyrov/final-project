package ua.com.company;

public abstract class DBConstants {


    public static final String CREATE_PERSON = "INSERT INTO person (username,email,password) VALUES (?,?,?)";
    public static final String CREATE_PERSON_ADMIN = "INSERT INTO person (email,password,role_id) VALUES (?,?,(SELECT id FROM role WHERE name = ?))";
    public static final String FIND_PERSON_BY_EMAIL = "SELECT p.*,r.name,ps.description, GROUP_CONCAT(p2.id) as publications_id FROM person p LEFT JOIN person_has_publication php on p.id = php.person_id  LEFT JOIN publication p2 on php.publication_id = p2.id INNER JOIN role r on p.role_id = r.id INNER JOIN  person_status ps  on p.status_id = ps.id WHERE (php.publication_id IS NOT NULL OR  php.publication_id IS NULL) AND p.email=? GROUP BY p.id";
    public static final String FIND_PERSON_BY_ID = "SELECT p.*,r.name,ps.description, GROUP_CONCAT(p2.id) as publications_id FROM person p LEFT JOIN person_has_publication php on p.id = php.person_id LEFT JOIN publication p2 on php.publication_id = p2.id INNER JOIN role r on p.role_id = r.id INNER JOIN  person_status ps  on p.status_id = ps.id  WHERE (php.publication_id IS NOT NULL OR php.publication_id IS NULL) AND p.id=? GROUP BY p.id";
    //    public static final String FIND_PERSON_BY_ID = "SELECT p.*,r.name,ps.description FROM person p  INNER JOIN role r on p.role_id = r.id INNER JOIN  person_status ps  on p.status_id = ps.id  AND p.id=?";
    public static final String FIND_ALL_PERSONS = "SELECT p.*,r.name,ps.description, GROUP_CONCAT(p2.id)as publications_id FROM person p LEFT JOIN person_has_publication php on p.id = php.person_id  LEFT JOIN publication p2 on php.publication_id = p2.id  INNER JOIN role r on p.role_id = r.id INNER JOIN  person_status ps  on p.status_id = ps.id   WHERE (php.publication_id IS NULL OR php.publication_id IS NOT NULL) AND r.name='ROLE_CUSTOMER' GROUP BY p.id";
    public static final String DELETE_PERSON = "DELETE FROM person WHERE id=?";
    public static final String COUNT_PERSON_BY_USERNAME = "SELECT COUNT(id) AS count FROM person WHERE username=?";
    public static final String COUNT_PERSON_BY_EMAIL = "SELECT COUNT(id) AS count FROM person WHERE email=?";
    public static final String COUNT_PERSON_BY_ID = "SELECT COUNT(id) AS count FROM person WHERE id=?";
    public static final String FIND_PERSON_BY_USERNAME = "SELECT p.*,r.name,ps.description, GROUP_CONCAT(p2.id) as publications_id FROM person p INNER JOIN person_has_publication php on p.id = php.person_id  INNER JOIN publication p2 on php.publication_id = p2.id INNER JOIN role r on p.role_id = r.id INNER JOIN  person_status ps  on p.status_id = ps.id  WHERE p.username=? GROUP BY p.id";
    public static final String DELETE_ORPHAN_TOPIC = "DELETE topic FROM topic  LEFT JOIN publication_has_topic pht ON topic.id = pht.topic_id WHERE pht.topic_id IS NULL";
    public static final String UPDATE_PERSON = "UPDATE person SET email = ?,role_id = (SELECT id FROM role WHERE name=?),status_id = (SELECT id FROM person_status WHERE description=?)WHERE id = ?";


    public static final String FIND_PUBLICATION_BY_ID = "SELECT p.id,p.title,p.description,p.price , i.name, i.path  FROM publication p  LEFT JOIN image i on i.id = p.image_id WHERE p.id=?";

    public static final String FIND_ALL_PUBLICATIONS = "SELECT p.* , i.name, i.path ,GROUP_CONCAT(t.title)as topics FROM publication p  INNER JOIN image i on p.image_id = i.id   INNER JOIN publication_has_topic pht  on p.id = pht.publication_id INNER JOIN topic t  on pht.topic_id = t.id   GROUP BY p.id";
    public static final String CREATE_PUBLICATION = "INSERT INTO publication (title,description,price,image_id) VALUES (?,?,?,?)";
    public static final String DELETE_PUBLICATION = "DELETE FROM publication WHERE id=?";
    public static final String FIND_ALL_PUBLICATIONS_BY_TITLE = "  SELECT  p.title,p.id,p.description,p.price,p.create_date,p.update_date,i.path,i.name FROM publication p LEFT JOIN image i on p.image_id = i.id WHERE p.title LIKE ? ORDER BY p.title";
    public static final String UPDATE_PUBLICATION = "UPDATE publication SET update_date = now(), title=?, description=?, price=? WHERE id=?";
    public static final String ADD_IMAGE_TO_PUBLICATION = "INSERT INTO image(publication_id,name,path) VALUES(?,?,?)";

    public static final String COUNT_TOPIC_BY_TITLE = "SELECT COUNT(id) AS count FROM topic WHERE title=?";
    public static final String CREATE_TOPIC = "INSERT INTO topic (title) VALUES (?)";
    public static final String FIND_ALL_TOPICS = "SELECT * FROM topic ORDER BY title";
    public static final String DELETE_TOPIC = "DELETE FROM topic WHERE id=?";
    public static final String UPDATE_TOPIC = "UPDATE topic SET title=? WHERE id =?";
    public static final String FIND_TOPIC_BY_ID = "SELECT * FROM topic WHERE id=?";
    public static final String ADD_PUBLICATION_TO_PERSON = "INSERT INTO person_has_publication (person_id,publication_id) VALUES (?,?)";
    public static final String FIND_PUBLICATION_BY_TITLE = "SELECT * FROM publication p  WHERE p.title=?";
    public static final String ADD_TOPIC_TO_PUBLICATION = "INSERT INTO publication_has_topic (publication_id,topic_id) VALUES (?,?)";
    public static final String FIND_TOPIC_BY_TITLE = "SELECT * FROM topic WHERE title=?";
    public static final String FIND_ALL_PUBLICATIONS_BY_TOPIC_ID_ASC = "(SELECT p.* , i.name, i.path ,GROUP_CONCAT(t.title)as topics FROM publication p  INNER JOIN image i on p.id = i.publication_id   INNER JOIN publication_has_topic pht  on p.id = pht.publication_id INNER JOIN topic t  on pht.topic_id = t.id   WHERE t.id=? GROUP BY ? ) ORDER by ?  LIMIT ?,?";
    public static final String FIND_ALL_PUBLICATIONS_BY_TOPIC = "SELECT p.id,p.description,p.title,p.price,p.create_date,i.name,i.path,t.title FROM publication p LEFT JOIN publication_has_topic pht on p.id = pht.publication_id INNER JOIN topic t on pht.topic_id = t.id INNER JOIN image i on p.image_id = i.id WHERE pht.topic_id = ? ORDER BY ? LIMIT ?,?";
    public static final String FIND_ALL_PUBLICATIONS_BY_TOPIC_ID_DESC = "SELECT p.id,p.description,p.title,p.price,p.create_date,i.name,i.path,t.title FROM publication p LEFT JOIN publication_has_topic pht on p.id = pht.publication_id INNER JOIN topic t on pht.topic_id = t.id INNER JOIN image i on p.image_id = i.id WHERE pht.topic_id = ? ORDER BY ? DESC LIMIT ?,?";
    public static final String FIND_ALL_PUBLICATIONS_BY_USER_ID = "SELECT p.id,p.description,p.title,p.price,p.create_date,p.update_date,i.name, i.path FROM publication p  LEFT JOIN image i on i.id = p.image_id left join person_has_publication php on p.id = php.publication_id WHERE php.person_id = ?";
    public static final String FIND_SIMPLE_PERSON_BY_EMAIL = "SELECT *  FROM person p INNER JOIN role r on p.role_id = r.id INNER JOIN  person_status ps  on p.status_id = ps.id  WHERE p.email=?";
    public static final String CHANGE_USER_STATUS_BY_ID = "UPDATE person SET status_id=? WHERE id=?";
    public static final String CHECK_USER_STATUS_BY_ID = "SELECT status_id FROM person WHERE id = ?";
    public static final String UPDATE_IMAGE_TO_PUBLICATION = "UPDATE image SET path =? WHERE publication_id=?";
    public static final String DELETE_PUBLICATION_HAS_TOPIC_BY_PUBLICATION_ID = "DELETE FROM publication_has_topic WHERE publication_id = ?";
    public static final String FIND_ALL_TOPIC_BY_PUBLICATION_ID = "SELECT t.id , t.title  FROM topic t LEFT JOIN publication_has_topic pht on t.id = pht.topic_id WHERE publication_id = ?";
    public static final String CREATE_IMAGE = "INSERT INTO image (name, path) VALUES (?,?)";
    public static final String UPDATE_IMAGE = "UPDATE image SET name = ?, path = ? WHERE id = ?";
    public static final String DELETE_IMAGE = "DELETE FROM image WHERE id =?";
    public static final String FIND_IMAGE_BY_ID = "SELECT * FROM image WHERE id =?";
    public static final String FIND_ALL_IMAGES = "SELECT name, path FROM image";
    public static final String FIND_IMAGE_BY_PUBLICATION_ID = "SELECT i.name, i.path FROM image i LEFT JOIN publication_has_image phi on i.id = phi.image_id WHERE phi.publication_id = ?";
    public static final String F_IMAGE_ID = "id";
    public static final String F_IMAGE_CREATE_DATE = "create_date";
    public static final String F_IMAGE_UPDATE_DATE = "update_date";
    public static final String COUNT_PUBLICATION_BY_TOPIC_ID = "SELECT COUNT(publication_id) AS count FROM publication_has_topic WHERE topic_id=?";
    public static final String DECREASE_FUNDS = "UPDATE person SET funds=? WHERE id=?";
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

    private DBConstants() {
    }


    public static final String F_PERSON_HAS_PUBLICATION_PUBLICATION = "publications_id";

    public static final String F_PERSON_STATUS_ID = "status_id";
    public static final String F_PUBLICATION_HAS_TOPIC_TOPICS = "topics";

    public static final String F_IMAGE_PUBLICATION_ID = "publication_id";
    public static final String F_IMAGE_NAME = "name";
    public static final String F_IMAGE_PATH = "path";

    public static final String F_PERSON_ID = "id";
    public static final String F_PERSON_EMAIL = "email";
    public static final String F_PERSON_CREATE_DATE = "create_date";
    public static final String F_PERSON_USERNAME = "username";
    public static final String F_PERSON_PASSWORD = "password";
    public static final String F_PERSON_UPDATE_DATE = "update_date";
    public static final String F_PERSON_FIRST_NAME = "first_name";
    public static final String F_PERSON_LAST_NAME = "last_name";
    public static final String F_PERSON_FUNDS = "funds";
    public static final String F_PERSON_ROLE = "name";
    public static final String F_PERSON_STATUS = "description";


    public static final String F_TOPIC_ID = "id";
    public static final String F_TOPIC_TITLE = "title";
    public static final String F_TOPIC_CREATE_DATE = "create_date";
    public static final String F_TOPIC_UPDATE_DATE = "update_date";

    //    public static final String F_ADMIN_ID = "id";
//    public static final String F_ADMIN_EMAIL = "email";
//    public static final String F_ADMIN_CREATE_DATE = "create_date";
//    public static final String F_ADMIN_UPDATE_DATE = "update_date";
//
    public static final String F_PUBLICATION_ID = "id";
    public static final String F_PUBLICATION_TITLE = "title";
    public static final String F_PUBLICATION_CREATE_DATE = "create_date";
    public static final String F_PUBLICATION_UPDATE_DATE = "update_date";
    public static final String F_PUBLICATION_DESCRIPTION = "description";
    public static final String F_PUBLICATION_PRICE = "price";

}
