package com.pop.codelab.chatopbackend.exception;

import java.util.Date;

/**
 * The ErrorMessage class represents an error message with status code, timestamp, message, and description.
 *
 * @author Pignon Pierre-Olivier
 * @version 1.0
 */
public class ErrorMessage {
    /**
     * The statusCode represents the status code of an error message.
     */
    private final int statusCode;
    /**
     * The timestamp represents the date and time when an error message occurred.
     */
    private final Date timestamp;
    /**
     * The message variable represents the message content of an error message.
     * <p>
     * It is a final variable that cannot be modified once initialized.
     * </p>
     * <p>
     * This variable is a string that holds the message content of an error message.
     * </p>
     *
     * @see ErrorMessage
     */
    private final String message;

    /**
     * The description variable represents the description content of an error message.
     * <p>
     * It is a final variable that cannot be modified once initialized.
     * </p>
     *
     * @see ErrorMessage
     */
    private final String description;

    /**
     * Creates an ErrorMessage object with the provided status code, timestamp, message, and description.
     *
     * @param statusCode  The status code of the error message
     * @param timestamp   The timestamp of the error message
     * @param message     The message content of the error message
     * @param description The description content of the error message
     */
    public ErrorMessage(final int statusCode,
                        final Date timestamp,
                        final String message,
                        final String description) {
        this.statusCode = statusCode;
        this.timestamp = timestamp;
        this.message = message;
        this.description = description;
    }

    /**
     * Returns the status code of an error message.
     *
     * @return the status code of an error message
     */
    public int getStatusCode() {
        return statusCode;
    }

    /**
     * Returns the timestamp of the error message.
     *
     * @return the timestamp of the error message
     */
    public Date getTimestamp() {
        return timestamp;
    }

    /**
     * Returns the message content of an error message.
     *
     * @return the message content of an error message
     */
    public String getMessage() {
        return message;
    }

    /**
     * Returns the description content of an error message.
     *
     * @return the description content of an error message
     */
    public String getDescription() {
        return description;
    }
}
