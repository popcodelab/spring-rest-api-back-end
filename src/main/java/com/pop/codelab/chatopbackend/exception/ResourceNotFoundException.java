package com.pop.codelab.chatopbackend.exception;

/**
 * Custom exception class indicating that a resource was not found.
 *
 * @author Pignon Pierre-Olivier
 * @version 1.0
 */
public class ResourceNotFoundException extends RuntimeException {

    /**
     * The serialVersionUID is a unique identifier for a Serializable class. It is used during the deserialization
     * process to verify that the sender and receiver of a serialized object have loaded classes that are compatible
     * with respect to serialization. If the classes do not match, then a InvalidClassException is thrown.
     * <p>
     * <p>
     * The serialVersionUID should be included for any class that implements the Serializable interface. It is used by
     * the serialization runtime to check that the classes of the serialized and deserialized objects match.
     * </p>
     * <p>
     * <p>
     * It is recommended to explicitly declare a serialVersionUID to ensure compatibility between different versions
     * of a class. If a serialVersionUID is not explicitly specified, the serialization runtime will generate one
     * based on various aspects of the class, such as its name, superclass chain, implemented interfaces, and other factors.
     * </p>
     * <p>
     * <p>
     * The serialVersionUID is a private static final field of type long, and its value should be unique across all
     * versions of the class.
     * </p>
     * <p>
     * <p>
     * The value of 1L is the default serialVersionUID value generated by the serialization runtime if it can't find
     * a serialVersionUID in the class.
     * </p>
     */
    private static final long serialVersionUID = 1L;

    /**
     * Custom exception class indicating that a resource was not found.
     */
    public ResourceNotFoundException(final String message) {
        super(message);
    }
}