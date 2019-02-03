package com.heavenhr.recruitment.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by shridhar on 02/02/19.
 */
@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class IncorrectStatusException extends RuntimeException {


    private static final long serialVersionUID = -8946734606004575931L;

    public IncorrectStatusException() {
        super();
    }

    public IncorrectStatusException(String message) {
        super(message);
    }

    public IncorrectStatusException(String message, Throwable t) {
        super(message, t);
    }

    public IncorrectStatusException(Throwable t) {
        super(t);
    }

}
