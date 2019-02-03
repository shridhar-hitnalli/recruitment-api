package com.heavenhr.recruitment.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by shridhar on 02/02/19.
 */
@ResponseStatus(code = HttpStatus.CONFLICT)
public class OfferAlreadyExistsException extends RuntimeException {

    private static final long serialVersionUID = -5917493146993751540L;

    public OfferAlreadyExistsException() {
        super();
    }

    public OfferAlreadyExistsException(String message) {
        super(message);
    }

    public OfferAlreadyExistsException(String message, Throwable t) {
        super(message, t);
    }

    public OfferAlreadyExistsException(Throwable t) {
        super(t);
    }

}
