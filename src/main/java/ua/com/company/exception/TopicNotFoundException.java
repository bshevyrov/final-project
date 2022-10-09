package ua.com.company.exception;

public class TopicNotFoundException extends Exception {
    public TopicNotFoundException(String s) {
        super("Topic by credential " + s + " not found");
    }
}
