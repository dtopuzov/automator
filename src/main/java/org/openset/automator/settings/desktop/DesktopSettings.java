package org.openset.automator.settings.desktop;

import org.openset.automator.settings.base.BaseSettings;
import org.openset.automator.settings.sikuli.SikuliConfig;

/**
 * Settings in context of testing desktop application.
 */
public class DesktopSettings {
    public BaseSettings base;
    public DesktopConfig desktop;
    public SikuliConfig sikuli;

    /**
     * Init desktop settings.
     */
    public DesktopSettings() {
        base = new BaseSettings();
        desktop = new DesktopConfig(base);
        sikuli = new SikuliConfig(base);
    }
}
