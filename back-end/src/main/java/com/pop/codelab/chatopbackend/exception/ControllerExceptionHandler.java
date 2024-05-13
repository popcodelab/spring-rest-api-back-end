package com.pop.codelab.chatopbackend.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;

/**
 * This class is responsible for handling exceptions thrown by controllers.
 *
 * @author Pignon Pierre-Olivier
 * @version 1.0
 */
@RestControllerAdvice
public class ControllerExceptionHandler {

    /**
     * Handle the ResourceNotFoundException and return an appropriate ErrorMessage object.
     *
     * @param ex      The ResourceNotFoundException that occurred
     * @param request The WebRequest object representing the current request
     * @return An ErrorMessage object containing the error details
     */
    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public ErrorMessage resourceNotFoundException(final ResourceNotFoundException ex, final WebRequest request) {
        ErrorMessage message = new ErrorMessage(
                HttpStatus.NOT_FOUND.value(),
                new Date(),
                ex.getMessage(),
                request.getDescription(false));

        return message;
    }

    /**
     * Handles exceptions thrown by controllers and returns an appropriate ErrorMessage object.
     *
     * @param ex      The Exception that occurred
     * @param request The WebRequest object representing the current request
     * @return An ErrorMessage object containing the error details
     */
    @ExceptionHandler(Exception.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorMessage globalExceptionHandler(final Exception ex, final WebRequest request) {
        ErrorMessage message = new ErrorMessage(
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                new Date(),
                ex.getMessage(),
                request.getDescription(false));

        return message;
    }
}
