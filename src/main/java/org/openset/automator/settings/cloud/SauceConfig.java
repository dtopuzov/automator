package org.openset.automator.settings.cloud;

import org.openset.automator.os.EnvUtils;
import org.openset.automator.settings.BaseSettings;

import java.util.Properties;

/**
 * Settings related to SauceLabs org.automator.cloud testing.
 */
public class SauceConfig {
    public String url;
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
        this.url = properties.getProperty("", "https://ondemand.eu-central-1.saucelabs.com/wd/hub");
        this.appiumVersion = properties.getProperty("appiumVersion");

        this.userName = properties.getProperty("sauceUserName",
                EnvUtils.getEnvironmentVariable("SAUCE_USER_NAME", null));
        this.accessKey = properties.getProperty("sauceAccessKey",
                EnvUtils.getEnvironmentVariable("SAUCE_ACCESS_KEY", null));

        this.name = EnvUtils.getEnvironmentVariable("name", null);
        this.tags = EnvUtils.getEnvironmentVariable("tags", null);
        this.build = EnvUtils.getEnvironmentVariable("build", null);
    }

}
