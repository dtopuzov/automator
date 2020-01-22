package org.openset.automator.test.common.exceptions;

import java.io.IOException;

public class TakeScreenshotException extends RuntimeException {
    public TakeScreenshotException(String message, IOException exception) {
        super(message, exception);
    }
}