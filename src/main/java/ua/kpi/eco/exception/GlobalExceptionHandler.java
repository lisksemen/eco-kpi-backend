package ua.kpi.eco.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({ObjectNotFoundException.class,
            PollutantNotFoundException.class,
            PollutionNotFoundException.class,
            ObjectAlreadyExists.class,
            EmeregencyNotFoundException.class,
            PollutantAlreadyExists.class})
    public String handleEntitiesException(RuntimeException e) {
        log.error("Handling entity exception: " + e.getMessage());
        return e.getMessage();
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(DataIntegrityViolationException.class)
    public String handleEntitiesDataIntegrityException(RuntimeException e) {
        log.error("Handling entity exception: " + e.getMessage());
        return "Duplicate names";
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(IllegalArgumentException.class)
    public String handleIllegalArgumentException(IllegalArgumentException e) {
        log.error("Handling illegal argument exception " + e.getMessage());
        return e.getMessage();
    }
}
