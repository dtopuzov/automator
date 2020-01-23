package org.openset.automator.settings.desktop;

import org.openset.automator.settings.base.AppConfig;
import org.openset.automator.settings.base.BaseSettings;

/**
 * Desktop specific settings.
 */
public class DesktopConfig extends AppConfig {
    public String appName;
    public String appPath;

    /**
     * Init desktop specific settings.
     */
    public DesktopConfig(BaseSettings settings) {
        super();
        appName = settings.properties.getProperty("appName");
        appPath = resolveAppPath(settings);
    }
}
