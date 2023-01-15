package ua.com.company.exception;

public class PublicationNotFoundException extends RuntimeException {
    public PublicationNotFoundException(String s) {
        super("Publication by " + s + " not found");
    }
}
