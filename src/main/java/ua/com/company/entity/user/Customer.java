package ua.com.company.entity.user;

import ua.com.company.type.RoleType;

public class Customer extends UserEntity {
    private String firstName;
    private String lastName;
    private double funds;

    public Customer() {
        super();
        setEnabled(true);
        setRoleType(RoleType.ROLE_CUSTOMER);
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
