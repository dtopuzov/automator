package org.openset.automator.app.desktop;

import org.openset.automator.app.App;
import org.openset.automator.app.StartApplicationException;
import org.openset.automator.os.OS;
import org.openset.automator.os.OSType;
import org.openset.automator.os.Process;
import org.openset.automator.settings.desktop.DesktopSettings;
import org.openset.automator.sikuli.Sikuli;
import org.openset.automator.sikuli.SikuliFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * Desktop application abstraction.
 */
public class DesktopApp implements App {
    protected DesktopSettings settings;
    protected Sikuli sikuli;

    /*
        Screen sikuli;
    App sikuliApp;

    @BeforeEach
    public void before() {
        sikuli = new Screen();
        ImagePath.add("src/test/resources/sikuli-images/sample-app.sikuli");
        Settings.MoveMouseDelay = 0;
        sikuliApp = App.open("java -jar " + System.getProperty("user.dir") + "/sample-app/BeeKeeper.jar");
    }

    @Test
    public void playTheGame() {
        AppPage appPage = new AppPage(sikuli);
        appPage.play();
        appPage.hasThePlayerWon();
    }

    @AfterEach
    public void after() {
        sikuliApp.close();
    }

     */

    /**
     * Initialize browser.
     *
     * @param settings instance of DesktopSettings.
     */
    public DesktopApp(DesktopSettings settings) {
        this.settings = settings;
        this.sikuli = new Sikuli(this.settings.sikuli);
        SikuliFactory.initElements(this);
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
            String javaProcessName = "java";
            if (OS.getOSType() == OSType.WINDOWS) {
                javaProcessName = "java.exe";
            }
            Process.stop(javaProcessName, settings.desktop.appName);
        } else {
            Process.stop("java", settings.desktop.appName);
            Process.stop(settings.desktop.appName);
        }
    }
}
