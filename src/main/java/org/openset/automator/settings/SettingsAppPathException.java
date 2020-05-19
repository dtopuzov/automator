package org.openset.automator.settings;

import java.io.IOException;

public class SettingsAppPathException extends RuntimeException {
    public SettingsAppPathException(String message, IOException exception) {
        super(message, exception);
    }

    public SettingsAppPathException(String message) {
        super(message);
    }
}
