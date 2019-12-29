package org.openset.automator.test.desktop;

import org.openset.automator.app.desktop.DesktopApp;
import org.openset.automator.settings.desktop.DesktopSettings;

public class DesktopContext {
    private final DesktopSettings settings;
    private final DesktopApp app;

    public DesktopContext(DesktopSettings settings, DesktopApp app) {
        this.settings = settings;
        this.app = app;
    }

    public DesktopApp getApp() {
        return app;
    }

    public DesktopSettings getSettings() {
        return settings;
    }
}
