package org.openset.automator.settings.mobile;

import org.openset.automator.settings.base.BaseSettings;
import org.openset.automator.settings.base.EnvironmentType;
import org.openset.automator.settings.cloud.SauceConfig;

/**
 * Settings in context of mobile testing.
 */
public class MobileSettings {
    public BaseSettings base;
    public MobileConfig mobile;
    public SauceConfig sauce;

    /**
     * Init mobile settings.
     */
    public MobileSettings() {
        base = new BaseSettings();
        mobile = new MobileConfig(base);
        if (base.environmentType == EnvironmentType.SAUCELABS) {
            sauce = new SauceConfig(base);
        }
    }
}
