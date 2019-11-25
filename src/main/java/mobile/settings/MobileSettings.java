package mobile.settings;

import settings.BaseSettings;
import org.openqa.selenium.Platform;

import java.util.Properties;

public class MobileSettings extends BaseSettings {
    public Platform platform;
    public Double platformVersion;

    public MobileSettings() {
        super();
    }

    public MobileSettings(Properties properties) {
        super(properties);
    }
}
