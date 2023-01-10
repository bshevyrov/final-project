package ua.com.company.exception;

public class PersonAddressNotFoundException  extends Exception{
    public PersonAddressNotFoundException(String s) {
        super("Person address by " + s + " not found");
    }

}
