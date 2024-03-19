package com.smashtaps.assignment.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(InvalidJsonException.class)
    public ResponseEntity<ExceptionMessage> handleInvalidJsonException(InvalidJsonException ex, WebRequest request) {
        String errorPath = ((ServletWebRequest) request).getRequest().getServletPath();
        ExceptionMessage exceptionMessage = new ExceptionMessage();
        exceptionMessage.setMessage(ex.getMessage());
        exceptionMessage.setError(ex.getClass().getCanonicalName());
        exceptionMessage.setPath(errorPath);
        return new ResponseEntity<>(exceptionMessage, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ExceptionMessage> handleInvalidJsonException(BadRequestException ex, WebRequest request) {
        String errorPath = ((ServletWebRequest) request).getRequest().getServletPath();
        ExceptionMessage exceptionMessage = new ExceptionMessage();
        exceptionMessage.setMessage(ex.getMessage());
        exceptionMessage.setError(ex.getClass().getCanonicalName());
        exceptionMessage.setPath(errorPath);
        return new ResponseEntity<>(exceptionMessage, HttpStatus.BAD_REQUEST);
    }
}
