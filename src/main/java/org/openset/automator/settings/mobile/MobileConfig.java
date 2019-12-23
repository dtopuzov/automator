package org.openset.automator.settings.mobile;

import org.openset.automator.settings.BaseSettings;
import org.openqa.selenium.Platform;

/**
 * Mobile specific org.automator.core.settings.
 */
public class MobileConfig {
    public Platform platform;
    public Double platformVersion;

    public MobileConfig(BaseSettings settings) {
        platform = Platform.fromString(settings.properties.getProperty("platform"));
        platformVersion = Double.parseDouble(settings.properties.getProperty("platformVersion"));
    }
}
