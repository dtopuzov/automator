package org.openset.automator.settings.cloud;

import org.openset.automator.os.OS;
import org.openset.automator.settings.SettingsLoadException;
import org.openset.automator.settings.base.BaseSettings;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;

/**
 * Settings related to SauceLabs cloud testing.
 */
public class SauceConfig {
    public URL url;
    public String platformName;
    public String browserVersion;
    public String appiumVersion;
    public String userName;
    public String accessKey;
    public String name;
    public String tags;
    public String build;

    /**
     * Init SauceLabs config.
     *
     * @param settings instance of BaseSettings.
     */
    public SauceConfig(BaseSettings settings) {
        Properties properties = settings.properties;
        platformName = properties.getProperty("platformName");
        browserVersion = properties.getProperty("browserVersion");
        appiumVersion = properties.getProperty("appiumVersion");

        userName = properties.getProperty("sauceUserName",
                OS.getEnvironmentVariable("SAUCE_USER_NAME", null));
        accessKey = properties.getProperty("sauceAccessKey",
                OS.getEnvironmentVariable("SAUCE_ACCESS_KEY", null));

        name = OS.getEnvironmentVariable("name", null);
        tags = OS.getEnvironmentVariable("tags", null);
        build = OS.getEnvironmentVariable("build", null);

        url = getUrl();
    }

    private URL getUrl() {
        if ((userName == null) || (accessKey == null)) {
            throw new SettingsLoadException("Can not start BrowserStack session with out credentials.");
        } else {
            String url = String.format("http://%s:%s@ondemand.saucelabs.com:80/wd/hub", userName, accessKey);
            try {
                return new URL(url);
            } catch (MalformedURLException e) {
                return null;
            }
        }
    }
}
