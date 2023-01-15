package ua.com.company.exception;

public class ImageNotFoundException extends RuntimeException {
    public ImageNotFoundException(String s) {
        super("Image by " + s + " not found");
    }

}
