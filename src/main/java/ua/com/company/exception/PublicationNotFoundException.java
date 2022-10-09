package ua.com.company.exception;

public class PublicationNotFoundException extends Exception {
    public PublicationNotFoundException(String s) {
        super("Publication with credential " + s + " not found");
    }
}
