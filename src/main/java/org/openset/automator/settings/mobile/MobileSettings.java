package org.openset.automator.settings.mobile;

import org.openset.automator.settings.BaseSettings;
import org.openset.automator.settings.SettingsLoadException;
import org.openset.automator.settings.cloud.SauceConfig;

/**
 * Settings in context of mobile testing.
 */
public class MobileSettings {
    public BaseSettings base;
    public MobileConfig mobile;
    public SauceConfig sauce;

    /**
     * @throws SettingsLoadException when couldn't settings
     */
    public MobileSettings() {
        base = new BaseSettings();
        mobile = new MobileConfig(base);
        sauce = new SauceConfig(base);
    }
}
