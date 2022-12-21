package ua.com.company.exception;

public class ImageNotFoundException extends Exception {
    public ImageNotFoundException(String s) {
        super("Image by " + s + " not found");
    }

}
