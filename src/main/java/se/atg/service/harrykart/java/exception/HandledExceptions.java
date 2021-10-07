package se.atg.service.harrykart.java.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

// Custom exception for the invalid xml check
// throws 400 instead of default 500
@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class HandledExceptions extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public HandledExceptions(String message) {
        super(message);
    }
}