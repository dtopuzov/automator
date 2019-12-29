package org.openset.automator.settings.base;

import org.openset.automator.log.Log;
import org.openset.automator.log.LogFactory;
import org.openset.automator.os.OS;
import org.openset.automator.os.OSType;
import org.openset.automator.settings.SettingsLoadException;
import org.openset.automator.test.common.RestartType;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.management.ManagementFactory;
import java.util.Properties;

public class BaseSettings {

    private static final Log LOGGER = LogFactory.getLogger(BaseSettings.class.getName());

    public Properties properties;
    public Boolean isDebug;
    public Boolean isDeveloperMode;
    public Integer defaultWait;
    public String testRunHome;
    public String testAppFolder;
    public OSType os;
    public RestartType restartType;

    public BaseSettings() {
        properties = readProperties();
        initSettings();
        logSettings();
    }

    private void initSettings() {
        isDebug = isDebug();
        isDeveloperMode = propertyToBoolean("developerMode", isDebug);
        defaultWait = propertyToInt("defaultWait", 30);
        testRunHome = System.getProperty("user.dir");
        testAppFolder = testRunHome + File.separator + "testapp";
        os = OS.getOSType();
        restartType = getRestartType();
    }

    private void logSettings() {
        LOGGER.separator("BASE SETTINGS");
        LOGGER.info("Debug Mode: " + isDebug);
        LOGGER.info("Developer Mode: " + isDeveloperMode);
        LOGGER.info("Default Wait Timeout: " + defaultWait);
        LOGGER.info("Test Run Home: " + testRunHome);
        LOGGER.info("Test App Folder: " + testAppFolder);
        LOGGER.info("OS Type: " + os);
        LOGGER.info("Restart Type: " + restartType);
    }

    /**
     * Helper method that converts property to int.
     *
     * @param property     as String.
     * @param defaultValue of property as int.
     * @return Value of property as int.
     */
    public int propertyToInt(String property, int defaultValue) {
        String propertyString = this.properties.getProperty(property);
        if (propertyString != null) {
            return Integer.parseInt(propertyString);
        } else {
            return defaultValue;
        }
    }

    /**
     * Helper method that converts property to Boolean.
     *
     * @param property as String.
     * @return Value of property as Boolean.
     */
    public Boolean propertyToBoolean(String property, boolean defaultValue) {
        String value = this.properties.getProperty(property);
        if (value == null) {
            return defaultValue;
        }
        if (value.equalsIgnoreCase("true")) {
            return true;
        } else if (value.equalsIgnoreCase("false")) {
            return false;
        } else {
            return null;
        }
    }

    private RestartType getRestartType() {
        return RestartType.NONE;
    }

    /**
     * Detect if debugger is attached.
     *
     * @return True if debugger is attached.
     */
    private boolean isDebug() {
        return ManagementFactory.getRuntimeMXBean().getInputArguments().toString().contains("jdwp");
    }

    private Properties readProperties() {
        String configFileProperty = System.getProperty("config", "") + ".properties";
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream(configFileProperty);
        if (inputStream != null) {
            try {
                Properties prop = new Properties();
                prop.load(inputStream);
                return prop;
            } catch (IOException exception) {
                String message = String.format("Failed to read settings from %s", configFileProperty);
                throw new SettingsLoadException(message, exception);
            }
        } else {
            LOGGER.warn("Failed to find properties file! Init default settings!");
            return new Properties();
        }
    }
}
