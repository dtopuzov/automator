package settings;

import cloud.saucelabs.SauceSettings;

import java.lang.management.ManagementFactory;
import java.util.Properties;

public abstract class BaseSettings {
    protected Boolean isDebug;
    public Integer wait;
    public SauceSettings sauceSettings;

    public BaseSettings() {
        this.isDebug = ManagementFactory.getRuntimeMXBean().getInputArguments().toString().contains("jdwp");
        this.wait = 30;
    }

    public BaseSettings(Properties properties) {

    }
}
