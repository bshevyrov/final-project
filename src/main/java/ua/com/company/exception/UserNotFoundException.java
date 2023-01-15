package ua.com.company.exception;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(String credential) {
        super("User by "+ credential+" not found");
    }
}
