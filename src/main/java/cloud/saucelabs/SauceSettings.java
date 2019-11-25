package cloud.saucelabs;

import utils.OSUtils;

import java.util.Properties;

/**
 * Settings related to SauceLabs cloud testing.
 */
public class SauceSettings {
    public String url;
    public String appiumVersion;
    public String userName;
    public String accessKey;
    public String name;
    public String tags;
    public String build;

    /**
     * Init Sauce settings.
     *
     * @param properties instance of Properties.
     */
    public SauceSettings(Properties properties) {
        this.url = properties.getProperty("", "https://ondemand.eu-central-1.saucelabs.com/wd/hub");
        this.appiumVersion = properties.getProperty("appiumVersion");

        this.userName = properties.getProperty("sauceUserName",
                OSUtils.getEnvironmentVariable("SAUCE_USER_NAME", null));
        this.accessKey = properties.getProperty("sauceAccessKey",
                OSUtils.getEnvironmentVariable("SAUCE_ACCESS_KEY", null));

        this.name = OSUtils.getEnvironmentVariable("name", null);
        this.tags = OSUtils.getEnvironmentVariable("tags", null);
        this.build = OSUtils.getEnvironmentVariable("build", null);
    }

}
