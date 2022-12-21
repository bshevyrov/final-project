package ua.com.company.exception;

public class PublicationNotFoundException extends Exception {
    public PublicationNotFoundException(String s) {
        super("Publication by " + s + " not found");
    }
}
