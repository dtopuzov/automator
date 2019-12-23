package org.openset.automator.settings.electron;

import org.openset.automator.os.FileUtils;
import org.openset.automator.settings.BaseSettings;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

/**
 * Electron specific org.automator.core.settings.
 */
public class ElectronConfig {
    public String appName;
    public String appPath;
    public String chromeDriverVersion;

    /**
     * Init electron specific settings.
     */
    public ElectronConfig(BaseSettings settings) {
        appName = settings.properties.getProperty("appName");
        appPath = resolveAppPath(settings);
        chromeDriverVersion = settings.properties.getProperty("chromeDriverVersion", "2.40");
    }

    private String resolveAppPath(BaseSettings settings) throws SettingsAppPathException {
        String appPathProperty = settings.properties.getProperty("appPath");
        try {
            String appPath = "";

            // If path is Url -> download it
            if (isURL(appPathProperty)) {
                String fileName = appPathProperty.substring(appPathProperty.lastIndexOf('/') + 1);
                appPath = settings.testAppFolder + File.separator + fileName;
                InputStream inputStream = new URL(appPathProperty).openStream();
                Files.copy(inputStream,
                        Paths.get(appPath),
                        StandardCopyOption.REPLACE_EXISTING);
            } else if (!new File(appPathProperty).exists()) {
                // Handle paths relative to default est app folder.
                appPath = settings.testAppFolder + File.separator + appPathProperty;
            }

            // If app is zipped -> extract it
            if (new File(appPath).exists()) {
                if (appPath.toLowerCase().contains(".zip")) {
                    FileUtils.extractZip(appPath, settings.testAppFolder);
                    appPath = FileUtils.find(settings.testAppFolder, appName + ".exe");
                }
                return appPath;
            } else {
                throw new SettingsAppPathException(String.format("Failed to find app at %s", appPathProperty));
            }
        } catch (IOException e) {
            throw new SettingsAppPathException(String.format("Failed to find app at %s", appPathProperty), e);
        }
    }

    public static boolean isURL(String url) {
        try {
            new URL(url);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
