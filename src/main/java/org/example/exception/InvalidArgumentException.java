package org.example.exception;

public class InvalidArgumentException extends Exception {

    /**
     * InvalidArgumentException is the default constructor with no arguments.
     */
    public InvalidArgumentException() {
    }

    /**
     * InvalidArgumentException is a constructor supplying a custom message to be returned.
     *
     * @param message the text describing the error that has occurred.
     */
    public InvalidArgumentException(String message) {
        super(message);
    }

    public InvalidArgumentException(Throwable cause) {
        super(cause);
    }

    public InvalidArgumentException(String message, Throwable cause) {
        super(message, cause);
    }
}