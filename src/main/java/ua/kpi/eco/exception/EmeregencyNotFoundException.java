package ua.kpi.eco.exception;

public class EmeregencyNotFoundException extends RuntimeException{
    public EmeregencyNotFoundException(String message) {
        super(String.format("Emergency with %s doesn't exist in the database", message));
    }
}
