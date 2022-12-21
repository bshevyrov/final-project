package ua.com.company.exception;

public class UserNotFoundException extends Exception {
    public UserNotFoundException(String credential) {
        super("User by "+ credential+" not found");
    }
}
