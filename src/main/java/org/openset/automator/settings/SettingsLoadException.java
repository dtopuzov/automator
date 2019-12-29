package org.openset.automator.settings;

import java.io.IOException;

public class SettingsLoadException extends RuntimeException {
    public SettingsLoadException(String message, IOException exception) {
        super(message, exception);
    }

    public SettingsLoadException(String message) {
        super(message);
    }
}
