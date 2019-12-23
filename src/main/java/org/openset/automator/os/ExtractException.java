package org.openset.automator.os;

import java.io.IOException;

public class ExtractException extends RuntimeException {
    public ExtractException(String message, IOException exception) {
        super(message, exception);
    }
}
