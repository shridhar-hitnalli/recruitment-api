package com.heavenhr.recruitment.exception;


import com.heavenhr.recruitment.dto.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by shridhar on 02/02/19.
 */
@ControllerAdvice
public class ExceptionTranslator {

    @ExceptionHandler(ApplicationNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleApplicationNotFoundException(ApplicationNotFoundException e, HttpServletRequest r){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).
                body(ErrorResponse.builder()
                        .httpStatusCode(HttpStatus.NOT_FOUND.value())
                        .errorMessage(e.getMessage())
                        .build()
                );
    }

    @ExceptionHandler(OfferNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleOfferNotFoundException(OfferNotFoundException e, HttpServletRequest r){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).
                body(ErrorResponse.builder()
                        .httpStatusCode(HttpStatus.NOT_FOUND.value())
                        .errorMessage(e.getMessage())
                        .build()
                );
    }

    @ExceptionHandler(OfferAlreadyExistsException.class)
    public ResponseEntity<ErrorResponse> handleOfferAlreadyExistsException(OfferAlreadyExistsException e, HttpServletRequest r){
        return ResponseEntity.status(HttpStatus.CONFLICT).
                body(ErrorResponse.builder()
                        .httpStatusCode(HttpStatus.CONFLICT.value())
                        .errorMessage(e.getMessage())
                        .build()
                );
    }

    @ExceptionHandler(ApplicationDataConflictException.class)
    public ResponseEntity<ErrorResponse> handleApplicationDataConflictException(ApplicationDataConflictException e, HttpServletRequest r){
        return ResponseEntity.status(HttpStatus.CONFLICT).
                body(ErrorResponse.builder()
                        .httpStatusCode(HttpStatus.CONFLICT.value())
                        .errorMessage(e.getMessage())
                        .build()
                );
    }

    @ExceptionHandler(IncorrectStatusException.class)
    public ResponseEntity<ErrorResponse> handleWrongStatusProgressException(IncorrectStatusException e, HttpServletRequest r){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).
                body(ErrorResponse.builder()
                        .httpStatusCode(HttpStatus.BAD_REQUEST.value())
                        .errorMessage(e.getMessage())
                        .build()
                );
    }
}
