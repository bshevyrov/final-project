package ua.com.company.utils;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PasswordUtilTest {

    @Test
    public void emptyRawPasswordDoesNotMatchPassword() {
        String result = PasswordUtil.encryptPassword("password");
        assertFalse(PasswordUtil.validatePassword("", result));
    }

    @Test
    public void passwordMatches() {
        String result = PasswordUtil.encryptPassword("password");
        assertNotEquals("password",result);
        assertTrue(PasswordUtil.validatePassword("password", result));
    }

    @Test
    public void passwordNotMatches() {
        String result = PasswordUtil.encryptPassword("password");
        assertFalse(PasswordUtil.validatePassword("username", result));
    }
}