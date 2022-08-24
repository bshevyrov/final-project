package ua.com.company.entity.user;

import ua.com.company.type.RoleType;

public class User extends UserEntity {
    private String firstName;
    private String lastName;
    private double funds;

    public User() {
        super();
        setEnabled(true);
        setRoleType(RoleType.ROLE_USER);
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

    public double getFunds() {
        return funds;
    }

    public void setFunds(double funds) {
        this.funds = funds;
    }
}
