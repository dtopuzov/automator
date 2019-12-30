package org.openset.automator.settings.cloud;

import org.openset.automator.os.OS;
import org.openset.automator.settings.base.BaseSettings;

import java.util.Properties;

/**
 * Settings related to SauceLabs org.automator.cloud testing.
 */
public class SauceConfig {
    public String url;
    public String platformName;
    public String browserVersion;
    public String appiumVersion;
    public String userName;
    public String accessKey;
    public String name;
    public String tags;
    public String build;

    /**
     * Init Sauce org.automator.core.settings.
     *
     * @param settings instance of BaseSettings.
     */
    public SauceConfig(BaseSettings settings) {
        Properties properties = settings.properties;
        url = properties.getProperty("", "https://ondemand.eu-central-1.saucelabs.com/wd/hub");
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
    }

}
