package org.openset.automator.settings.mobile;

import org.openqa.selenium.remote.BrowserType;
import org.openset.automator.os.File;
import org.openset.automator.settings.SettingsLoadException;
import org.openset.automator.settings.base.BaseSettings;
import org.openqa.selenium.Platform;
import org.openset.automator.settings.electron.SettingsAppPathException;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

/**
 * Mobile specific org.automator.core.settings.
 */
public class MobileConfig {
    public Platform platform;
    public Double platformVersion;
    public String deviceName;
    public String deviceId;
    public String avdName;
    public String avdArgs;
    public String appPath;
    public String appPackage;
    public String appActivity;
    public String browserType;
    public String baseUrl;
    public String chromeDriverVersion;

    public MobileConfig(BaseSettings settings) {
        platform = Platform.fromString(settings.properties.getProperty("platform"));
        platformVersion = Double.parseDouble(settings.properties.getProperty("platformVersion"));
        deviceName = settings.properties.getProperty("deviceName");
        deviceId = settings.properties.getProperty("deviceId");
        avdName = settings.properties.getProperty("avdName");
        avdArgs = settings.properties.getProperty("avdArgs");
        appPath = resolveAppPath(settings);
        appPackage = settings.properties.getProperty("appPackage");
        appActivity = settings.properties.getProperty("appActivity");
        browserType = settings.properties.getProperty("browserType");
        baseUrl = settings.properties.getProperty("baseUrl");
        chromeDriverVersion = settings.properties.getProperty("chromeDriverVersion");
    }

    protected String resolveAppPath(BaseSettings settings) throws SettingsAppPathException {
        String appPathProperty = settings.properties.getProperty("appPath");
        String appPath = appPathProperty;

        try {
            // If path is Url -> download it
            if (isURL(appPathProperty)) {
                String fileName = appPathProperty.substring(appPathProperty.lastIndexOf('/') + 1);
                appPath = settings.testAppFolder + java.io.File.separator + fileName;
                InputStream inputStream = new URL(appPathProperty).openStream();
                Files.copy(inputStream,
                        Paths.get(appPath),
                        StandardCopyOption.REPLACE_EXISTING);
            }

            // Handle paths relative to default test app folder.
            if (!new java.io.File(appPath).exists()) {
                appPath = settings.testAppFolder + java.io.File.separator + appPathProperty;
            }
        } catch (IOException e) {
            throw new SettingsAppPathException(String.format("Failed to find app at %s", appPathProperty), e);
        }

        return appPath;
    }

    protected static boolean isURL(String url) {
        try {
            new URL(url);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
