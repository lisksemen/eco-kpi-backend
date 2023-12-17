package ua.kpi.eco.exception;

public class ObjectNotFoundException extends RuntimeException{

    public ObjectNotFoundException(String message) {
        super(String.format("Object with %s doesn't exist in the database", message));
    }
}
