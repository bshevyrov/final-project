package ua.com.company.entity;

import ua.com.company.type.RoleType;
import ua.com.company.type.StatusType;

import java.util.List;
import java.util.Objects;

public class Person extends BaseEntity {

    private String email;
    private String password;
    private StatusType status;
    private RoleType role;
    private String username;
    private double funds;
    private Image avatar;

    private List<Publication> publicationList;
    private PersonAddress personAddress;

    public Image getAvatar() {
        return avatar;
    }

    public void setAvatar(Image avatar) {
        this.avatar = avatar;
    }


    public List<Publication> getPublications() {
        return publicationList;
    }

    public void setPublications(List<Publication> publicationList) {
        this.publicationList = publicationList;
    }

    public PersonAddress getPersonAddress() {
        return personAddress;
    }

    public void setPersonAddress(PersonAddress personAddress) {
        this.personAddress = personAddress;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public StatusType getStatus() {
        return status;
    }

    public void setStatus(StatusType status) {
        this.status = status;
    }

    public RoleType getRole() {
        return role;
    }

    public void setRole(RoleType role) {
        this.role = role;
    }

    public double getFunds() {
        return funds;
    }

    public void setFunds(double funds) {
        this.funds = funds;
    }


    public Person(String email, String password, RoleType role) {
        this.email = email;
        this.password = password;
        this.role = role;
    }

    public Person() {
    }

    @Override
    public String toString() {
        return "Person{" +
                "email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", status=" + status +
                ", role=" + role +
                ", username='" + username + '\'' +
                ", funds=" + funds +
                ", avatar=" + avatar +
                ", publicationList=" + publicationList +
                ", personAddress=" + personAddress +
                "} " + super.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return Double.compare(person.funds, funds) == 0 && Objects.equals(email, person.email) && Objects.equals(password, person.password) && status == person.status && role == person.role && Objects.equals(username, person.username) && Objects.equals(avatar, person.avatar) && Objects.equals(publicationList, person.publicationList) && Objects.equals(personAddress, person.personAddress);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email, password, status, role, username, funds, avatar, publicationList, personAddress);
    }
}
