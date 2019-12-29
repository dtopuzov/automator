package org.openset.automator.settings.electron;

import org.openset.automator.settings.base.AppConfig;
import org.openset.automator.settings.base.BaseSettings;

/**
 * Electron specific settings.
 */
public class ElectronConfig extends AppConfig {
    public String appName;
    public String appPath;
    public String chromeDriverVersion;

    /**
     * Init electron specific settings.
     */
    public ElectronConfig(BaseSettings settings) {
        super();
        appName = settings.properties.getProperty("appName");
        appPath = resolveAppPath(settings);
        chromeDriverVersion = settings.properties.getProperty("chromeDriverVersion", "2.40");
    }
}
