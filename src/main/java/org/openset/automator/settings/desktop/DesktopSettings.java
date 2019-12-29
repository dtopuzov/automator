package org.openset.automator.settings.desktop;

import org.openset.automator.settings.base.BaseSettings;
import org.openset.automator.settings.sikuli.SikuliConfig;

/**
 * Settings in context of testing electron application.
 */
public class DesktopSettings {
    public BaseSettings base;
    public DesktopConfig desktop;
    public SikuliConfig sikuli;

    public DesktopSettings() {
        base = new BaseSettings();
        desktop = new DesktopConfig(base);
        sikuli = new SikuliConfig(base);
    }
}
