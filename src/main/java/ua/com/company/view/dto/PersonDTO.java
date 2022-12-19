package ua.com.company.view.dto;

import ua.com.company.type.RoleType;
import ua.com.company.type.StatusType;

import java.util.List;
import java.util.Objects;

public class PersonDTO extends BaseDTO {


    private String email;
    private String password;
    private StatusType status;
    private RoleType role;

    private String username;
    private double funds;
    private ImageDTO avatar;

    private List<PublicationDTO> publications;
    private PersonAddressDTO personAddressDTO;

    public ImageDTO getAvatar() {
        return avatar;
    }

    public void setAvatar(ImageDTO avatar) {
        this.avatar = avatar;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPublications(List<PublicationDTO> publications) {
        this.publications = publications;
    }

    public PersonAddressDTO getPersonAddressDTO() {
        return personAddressDTO;
    }

    public List<PublicationDTO> getPublications() {
        return publications;
    }

    public void setPersonAddressDTO(PersonAddressDTO personAddressDTO) {
        this.personAddressDTO = personAddressDTO;
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

//    public String getFirstName() {
//        return firstName;
//    }
//
//    public void setFirstName(String firstName) {
//        this.firstName = firstName;
//    }
//
//    public String getLastName() {
//        return lastName;
//    }
//
//    public void setLastName(String lastName) {
//        this.lastName = lastName;
//    }


    public PersonDTO(String email, String password, RoleType role) {
        this.email = email;
        this.password = password;
        this.role = role;
    }

    public PersonDTO() {
    }

    @Override
    public String toString() {
        return "PersonDTO{" +
                "email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", status=" + status +
                ", role=" + role +
                ", username='" + username + '\'' +
                ", funds=" + funds +
                ", avatar=" + avatar +
                ", publications=" + publications +
                ", personAddressDTO=" + personAddressDTO +
                "} " + super.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PersonDTO personDTO = (PersonDTO) o;
        return Objects.equals(email, personDTO.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email);
    }
}
