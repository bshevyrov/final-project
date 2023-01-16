package ua.com.company.exception;

public class TopicNotFoundException extends RuntimeException {
    public TopicNotFoundException(String s) {
        super("Topic by " + s + " not found");
    }
}
