package com.heavenhr.recruitment.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by shridhar on 02/02/19.
 */
@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class OfferNotFoundException extends RuntimeException {


    private static final long serialVersionUID = 5224832912332196263L;

    public OfferNotFoundException() {
        super();
    }

    public OfferNotFoundException(String message) {
        super(message);
    }

    public OfferNotFoundException(String message, Throwable t) {
        super(message, t);
    }

    public OfferNotFoundException(Throwable t) {
        super(t);
    }

}
