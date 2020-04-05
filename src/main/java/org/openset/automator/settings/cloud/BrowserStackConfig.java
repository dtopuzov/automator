package org.openset.automator.settings.cloud;

import org.openset.automator.os.OS;
import org.openset.automator.settings.SettingsLoadException;
import org.openset.automator.settings.base.BaseSettings;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;

/**
 * Settings related to Browser stack cloud testing.
 */
public class BrowserStackConfig {
    private Properties properties;

    /**
     * Init BrowserStack config.
     *
     * @param settings instance of BaseSettings.
     */
    public BrowserStackConfig(BaseSettings settings) {
        this.properties = settings.properties;
    }

    public URL getUrl() {
        String defaultUser = OS.getEnvironmentVariable("BROWSER_STACK_USER", null);
        String defaultKey = OS.getEnvironmentVariable("BROWSER_STACK_KEY", null);
        String userName = properties.getProperty("browserStackUser", defaultUser);
        String accessKey = properties.getProperty("browserStackKey", defaultKey);

        if ((userName == null) || (accessKey == null)) {
            throw new SettingsLoadException("Can not start BrowserStack session with out credentials.");
        } else {
            String url = String.format("http://%s:%s@hub-cloud.browserstack.com/wd/hub", userName, accessKey);
            try {
                return new URL(url);
            } catch (MalformedURLException e) {
                return null;
            }
        }
    }

    public Properties getBrowserStackProperties() {
        Properties browserStackProperties = properties;
        properties.forEach((key, val) -> {
            if (key.toString().toLowerCase().contains("browserstack")) {
                browserStackProperties.put(key, val);
            }
        });
        return browserStackProperties;
    }
}
