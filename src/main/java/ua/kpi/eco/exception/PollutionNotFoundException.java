package ua.kpi.eco.exception;

public class PollutionNotFoundException extends RuntimeException{

    public PollutionNotFoundException(String message) {
        super(String.format("Pollution with %s doesn't exist in the database", message));
    }
}
