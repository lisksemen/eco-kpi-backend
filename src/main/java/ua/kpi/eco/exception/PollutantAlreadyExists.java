package ua.kpi.eco.exception;

public class PollutantAlreadyExists extends RuntimeException{

    public PollutantAlreadyExists(String message) {
        super(String.format("Pollutant with %s already exists in the database", message));
    }

    public PollutantAlreadyExists(String message, Throwable cause) {
        super(String.format("Pollutant with %s already exists in the database", message), cause);
    }
}
