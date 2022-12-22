package ua.com.company.exception;

public class PublicationCommentNotFoundException extends Exception{
    public PublicationCommentNotFoundException(String s) {
        super("Publication comment by  " + s + " not found");
    }

}
