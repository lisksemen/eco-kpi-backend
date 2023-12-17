package ua.kpi.eco.exception;

public class PollutantNotFoundException extends RuntimeException {

    public PollutantNotFoundException(String message) {
        super(String.format("Pollutant with %s doesn't exist in the database", message));
    }
}
