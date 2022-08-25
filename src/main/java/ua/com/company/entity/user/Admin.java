package ua.com.company.entity.user;

import ua.com.company.type.RoleType;

public class Admin extends UserEntity {

    public Admin() {
        super();
        setEnabled(true);
        setRoleType(RoleType.ROLE_ADMIN);
    }

    @Override
    public String toString() {
        return "Admin{" + super.toString()+
                '}';
    }
}
