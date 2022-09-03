package ua.com.company.entity;

import ua.com.company.type.RoleType;
import ua.com.company.type.StatusType;

import java.util.List;

public class Person extends BaseEntity{


    private String email;
    private String password;
    private StatusType status;
    private RoleType role;
    private double funds;
    private  String firstName;
    private  String lastName;
    private List<Integer> publications;

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

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public List<Integer> getPublications() {
        return publications;
    }

    public void setPublications(List<Integer> publications) {
        this.publications = publications;
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
                ", funds=" + funds +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", publications=" + publications +
                '}';
    }
}
