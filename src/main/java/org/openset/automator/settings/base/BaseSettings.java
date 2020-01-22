package org.openset.automator.settings.base;

import org.openset.automator.log.Log;
import org.openset.automator.log.LogFactory;
import org.openset.automator.os.OS;
import org.openset.automator.os.OSType;
import org.openset.automator.settings.SettingsLoadException;
import org.openset.automator.test.common.enums.RestartType;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.management.ManagementFactory;
import java.util.Properties;

@SuppressWarnings("WeakerAccess")
public class BaseSettings {

    private static final Log LOGGER = LogFactory.getLogger(BaseSettings.class.getName());

    public Properties properties;
    public Boolean isDebug;
    public Boolean isDeveloperMode;
    public Integer defaultWait;
    public String testRunHome;
    public String testAppFolder;
    public String testResultsFolder;
    public String testLogsFolder;
    public String testScreenshotsFolder;
    public OSType os;
    public EnvironmentType environmentType;
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
        testResultsFolder = testRunHome + File.separator + "build" + File.separator + "test-results";
        testLogsFolder = testResultsFolder + File.separator + "logs";
        testScreenshotsFolder = testResultsFolder + File.separator + "screenshots";
        os = OS.getOSType();
        environmentType = getEnvironmentType();
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
        RestartType restartType = RestartType.NONE;
        String restartTypeString = this.properties.getProperty("restartType", "");
        if (restartTypeString.toLowerCase().contains("fail")) {
            restartType = RestartType.ON_FAILED_TEST;
        } else if (restartTypeString.toLowerCase().contains("test")) {
            restartType = RestartType.ON_EACH_TEST;
        } else if (restartTypeString.toLowerCase().contains("class")) {
            restartType = RestartType.ON_EACH_CLASS;
        }
        return restartType;
    }

    /**
     * Detect if debugger is attached.
     *
     * @return True if debugger is attached.
     */
    private boolean isDebug() {
        return ManagementFactory.getRuntimeMXBean().getInputArguments().toString().contains("jdwp");
    }

    /**
     * Get type of test environment.
     *
     * @return EnvironmentType value (default is Local).
     */
    private EnvironmentType getEnvironmentType() {
        String type = this.properties.getProperty("environmentType", "LOCAL");
        return EnvironmentType.valueOf(type);
    }

    private Properties readProperties() {
        String configSystemPropertyValue = System.getProperty("config", "");
        if (configSystemPropertyValue.equalsIgnoreCase("")) {
            LOGGER.warn("Config file not specified! Init default settings!");
            return new Properties();
        } else {
            String configFileProperty = configSystemPropertyValue + ".properties";
            InputStream inputStream = getClass().getClassLoader().getResourceAsStream(configFileProperty);
            if (inputStream != null) {
                try {
                    Properties prop = new Properties();
                    prop.load(inputStream);
                    return prop;
                } catch (IOException exception) {
                    String message = String.format("Failed to read settings from %s.", configFileProperty);
                    LOGGER.fatal(message);
                    throw new SettingsLoadException(message, exception);
                }
            } else {
                String message = String.format("Failed find %s config file.", configFileProperty);
                LOGGER.fatal(message);
                throw new SettingsLoadException(message);
            }
        }
    }
}
