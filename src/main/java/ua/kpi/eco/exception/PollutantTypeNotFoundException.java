package ua.kpi.eco.exception;

public class PollutantTypeNotFoundException extends RuntimeException{
    public PollutantTypeNotFoundException(String message) {
        super(String.format("PollutantType with %s doesn't exist in the database", message));
    }
}
