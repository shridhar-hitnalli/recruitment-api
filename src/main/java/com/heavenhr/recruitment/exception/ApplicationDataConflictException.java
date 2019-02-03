package com.heavenhr.recruitment.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by shridhar on 02/02/19.
 */
@ResponseStatus(code = HttpStatus.CONFLICT)
public class ApplicationDataConflictException extends RuntimeException {


    private static final long serialVersionUID = -5428535966905536994L;

    public ApplicationDataConflictException() {
        super();
    }

    public ApplicationDataConflictException(String message) {
        super(message);
    }

    public ApplicationDataConflictException(String message, Throwable t) {
        super(message, t);
    }

    public ApplicationDataConflictException(Throwable t) {
        super(t);
    }

}
