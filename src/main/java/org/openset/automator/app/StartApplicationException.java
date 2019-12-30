package org.openset.automator.app;

public class StartApplicationException extends RuntimeException {
    public StartApplicationException(String message, Exception exception) {
        super(message, exception);
    }

    public StartApplicationException(String message) {
        super(message);
    }
}
