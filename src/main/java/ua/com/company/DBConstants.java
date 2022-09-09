package ua.com.company;

public abstract class DBConstants {


    public static final String CREATE_PERSON = "INSERT INTO person (email,password) VALUES (?,?)";
    public static final String CREATE_PERSON_ADMIN = "INSERT INTO person (email,password,role_id) VALUES (?,?,(SELECT id FROM role WHERE name = ?))";
    public static final String FIND_PERSON_BY_ID = "SELECT * FROM person p INNER JOIN role r on p.role_id = r.id INNER JOIN  person_status ps  on p.status_id = ps.id WHERE p.id=?";
    public static final String FIND_ALL_PERSONS = "SELECT * FROM person p INNER JOIN role r on p.role_id = r.id INNER JOIN  person_status ps  on p.status_id = ps.id";
    public static final String DELETE_PERSON = "DELETE FROM person WHERE id=?";
    public static final String COUNT_PERSON_BY_EMAIL = "SELECT COUNT(id) AS count FROM person WHERE email=?";
    public static final String COUNT_PERSON_BY_ID = "SELECT COUNT(id) AS count FROM person WHERE id=?";
    public static final String FIND_PERSON_BY_EMAIL = "SELECT * FROM person p INNER JOIN role r on p.role_id = r.id INNER JOIN  person_status ps  on p.status_id = ps.id WHERE p.email=?";
    public static final String UPDATE_PERSON = "UPDATE person SET email = ?,role_id = (SELECT id FROM role WHERE name=?),status_id = (SELECT id FROM person_status WHERE description=?)WHERE id = ?";


    public static final String FIND_PUBLICATION_BY_ID ="SELECT * FROM publication p  WHERE p.id=?";

    public static final String FIND_ALL_PUBLICATIONS = "SELECT * FROM publication p ORDER BY p.create_date";
    public static final String CREATE_PUBLICATION = "INSERT INTO publication (title,sample,price) VALUES (?,?,?)";
    public static final String DELETE_PUBLICATION = "DELETE FROM publication WHERE id=?";
    public static final String FIND_ALL_PUBLICATIONS_BY_TITLE = "SELECT * FROM publication p WHERE p.title LIKE ? ORDER BY p.title";
    public static final String UPDATE_PUBLICATION = "UPDATE publication SET title=?, sample=?, price=? WHERE id=?";
    public static final String ADD_IMAGE_TO_PUBLICATION ="INSERT INTO image(publication_id,name,path) VALUES(?,?,?)";

    public static final String CREATE_TOPIC = "INSERT INTO topic (title) VALUES (?)";
    public static final String FIND_ALL_TOPICS = "SELECT * FROM topic";
    public static final String DELETE_TOPIC = "DELETE FROM topic WHERE id=?";
    public static final String UPDATE_TOPIC = "UPDATE topic SET title=? WHERE id =?";
    public static final String FIND_TOPIC_BY_ID = "SELECT * FROM topic WHERE id=?";
    public static final String ADD_PUBLICATION_TO_PERSON ="INSERT INTO person_has_publication (person_id,publication_id) VALUES (?,?)";

    private DBConstants() {
    }

    public static final String F_IMAGE_PUBLICATION_ID = "publication_id";
    public static final String F_IMAGE_NAME = "name";
    public static final String F_IMAGE_PATH = "path";

    public static final String F_PERSON_ID = "id";
    public static final String F_PERSON_EMAIL = "email";
    public static final String F_PERSON_CREATE_DATE = "create_date";
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
    public static final String F_PUBLICATION_SAMPLE = "sample";
    public static final String F_PUBLICATION_PRICE = "price";

}
