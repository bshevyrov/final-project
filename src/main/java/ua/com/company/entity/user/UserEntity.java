package ua.com.company.entity.user;

import ua.com.company.entity.BaseEntity;
import ua.com.company.type.RoleType;

public abstract class UserEntity extends BaseEntity {
    RoleType roleType;
    String Email;
    String password;

    boolean enabled;

    public UserEntity() {
    }

    public RoleType getRoleType() {
        return roleType;
    }

    public void setRoleType(RoleType roleType) {
        this.roleType = roleType;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
}
