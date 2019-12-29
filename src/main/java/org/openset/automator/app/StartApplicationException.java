package org.openset.automator.app;

import java.io.IOException;

public class StartApplicationException extends RuntimeException {
    public StartApplicationException(String message, Exception exception) {
        super(message, exception);
    }
}
