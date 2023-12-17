package ua.kpi.eco.exception;

public class ObjectAlreadyExists extends RuntimeException{

    public ObjectAlreadyExists(String message) {
        super(String.format("Object with %s already exists in the database", message));
    }

    public ObjectAlreadyExists(String message, Throwable cause) {
        super(String.format("Object with %s already exists in the database", message), cause);
    }
}
