package org.openset.automator.app.desktop;

import org.openset.automator.app.App;
import org.openset.automator.app.StartApplicationException;
import org.openset.automator.os.Process;
import org.openset.automator.settings.desktop.DesktopSettings;
import org.openset.automator.sikuli.Sikuli;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * Desktop application abstraction.
 */
public class DesktopApp implements App {
    protected DesktopSettings settings;
    protected Sikuli sikuli;

    /**
     * Initialize browser.
     *
     * @param settings instance of DesktopSettings.
     */
    public DesktopApp(DesktopSettings settings) {
        this.settings = settings;
        this.sikuli = new Sikuli(this.settings.sikuli);
    }

    /**
     * Start application.¶
     */
    public void start() {
        String appPath = settings.desktop.appPath;
        String[] command = new String[]{appPath};

        // Handle apps in Jar files.
        if (appPath.endsWith(".jar")) {
            command = new String[]{"java", "-jar", appPath};
        }

        // Start the app.
        try {
            Process.start(command, false);
        } catch (InterruptedException | IOException e) {
            throw new StartApplicationException(String.format("Failed to start \"%s\".", appPath), e);
        } catch (TimeoutException e) {
            throw new StartApplicationException(String.format("Failed to start \"%s\" in given time.", appPath), e);
        }
    }

    /**
     * Stop application.
     */
    public void stop() {
        if (settings.desktop.appPath.endsWith(".jar")) {
            Process.stop("java.exe", settings.desktop.appName);
        } else {
            Process.stop(settings.desktop.appName);
        }
    }
}
