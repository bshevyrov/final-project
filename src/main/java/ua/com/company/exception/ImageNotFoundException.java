package ua.com.company.exception;

public class ImageNotFoundException extends Exception {
    public ImageNotFoundException(String s) {
        super("Image by credential " + s + " not found");
    }

    public ImageNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
