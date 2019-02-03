package com.heavenhr.recruitment.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by shridhar on 02/02/19.
 */
@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class ApplicationNotFoundException extends RuntimeException {

    private static final long serialVersionUID = -5917493146993751540L;

    public ApplicationNotFoundException() {
        super();
    }

    public ApplicationNotFoundException(String message) {
        super(message);
    }

    public ApplicationNotFoundException(String message, Throwable t) {
        super(message, t);
    }

    public ApplicationNotFoundException(Throwable t) {
        super(t);
    }

}
