package org.openset.automator.settings.base;

import org.openset.automator.os.File;
import org.openset.automator.settings.SettingsLoadException;
import org.openset.automator.settings.electron.SettingsAppPathException;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class AppConfig {

    protected String resolveAppPath(BaseSettings settings) throws SettingsAppPathException {
        String appName = settings.properties.getProperty("appName");
        String appPathProperty = settings.properties.getProperty("appPath");
        String appPath = appPathProperty;

        // Handle missing appPath property
        if (appPath == null) {
            throw new SettingsLoadException("Please specify properties config file!");
        }

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

            // If app is zipped -> extract it
            if (new java.io.File(appPath).exists()) {
                if (appPathProperty.toLowerCase().contains(".zip")) {
                    File.extractZip(appPath, settings.testAppFolder);
                    appPath = File.find(settings.testAppFolder, appName + ".exe");
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