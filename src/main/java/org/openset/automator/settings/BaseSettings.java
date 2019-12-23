package org.openset.automator.settings;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.management.ManagementFactory;
import java.util.Properties;

public class BaseSettings {
    public Properties properties;
    public Boolean isDebug;
    public Boolean isDeveloperMode;
    public Integer wait;
    public String testRunHome;
    public String testAppFolder;

    public BaseSettings() {
        properties = readProperties();
        initSettings(properties);
    }

    public BaseSettings(Properties properties) {
        initSettings(properties);
    }

    private void initSettings(Properties properties) {
        isDebug = ManagementFactory.getRuntimeMXBean().getInputArguments().toString().contains("jdwp");
        isDeveloperMode = isDebug;
        wait = Integer.valueOf(properties.getProperty("wait", "30"));
        testRunHome = System.getProperty("user.dir");
        testAppFolder = testRunHome + File.separator + "testapp";
    }

    private Properties readProperties() {
        String propFileName = "configs" + File.separator + "electron.windows.properties";
        try {
            Properties prop = new Properties();
            InputStream inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);
            prop.load(inputStream);
            return prop;
        } catch (IOException exception) {
            String message = String.format("Failed to read settings from %s", propFileName);
            throw new SettingsLoadException(message, exception);
        }
    }
}
