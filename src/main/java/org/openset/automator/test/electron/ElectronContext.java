package org.openset.automator.test.electron;

import org.openset.automator.app.electron.ElectronApp;
import org.openset.automator.settings.electron.ElectronSettings;

public class ElectronContext {
    private final ElectronApp app;
    private final ElectronSettings settings;

    public ElectronContext(ElectronApp app, ElectronSettings settings) {
        this.app = app;
        this.settings = settings;
    }

    public ElectronApp getApp() {
        return app;
    }

    public ElectronSettings getSettings() {
        return settings;
    }
}
