package org.openset.automator.settings.electron;

import org.openset.automator.settings.base.BaseSettings;

/**
 * Settings in context of testing electron application.
 */
public class ElectronSettings {
    public BaseSettings base;
    public ElectronConfig electron;

    public ElectronSettings() {
        base = new BaseSettings();
        electron = new ElectronConfig(base);
    }
}
