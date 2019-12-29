package org.openset.automator.app.electron;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openset.automator.app.App;
import org.openset.automator.settings.electron.ElectronSettings;

import java.net.MalformedURLException;
import java.net.URL;

import static java.util.concurrent.TimeUnit.SECONDS;
import static org.awaitility.Awaitility.await;

/**
 * Electron application abstraction.
 */
@SuppressWarnings({"WeakerAccess", "unused"})
public class ElectronApp implements App {
    private WebDriver driver;
    private ElectronSettings settings;

    /**
     * Initialize browser.
     *
     * @param settings instance of Settings.
     */
    public ElectronApp(ElectronSettings settings) {
        this.settings = settings;
    }

    /**
     * Start application.
     */
    public void start(boolean waitSplash) {

        ChromeOptions options = new ChromeOptions();
        options.setBinary(settings.electron.appPath);
        options.addArguments("--no-sandbox");
        options.setHeadless(true);
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--remote-debugging-port=9222");
        try {
            driver = new RemoteWebDriver(new URL("http://localhost:9515/"), options);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        if (settings.base.defaultWait != null) {
            this.driver.manage().timeouts().implicitlyWait(settings.base.defaultWait, SECONDS);
        }

        if (waitSplash) {
            // Some apps use splash screens.
            // In this case when splash disappear new window is opened.
            await().atMost(settings.base.defaultWait, SECONDS).until(() -> this.driver.getPageSource() == null);
            for (String handle : driver.getWindowHandles()) {
                driver.switchTo().window(handle);
            }
        }
    }

    public void start() {
        start(false);
    }

    /**
     * Stop application.
     */
    public void stop() {
        if (this.driver != null) {
            this.driver.quit();
        }
    }

    /**
     * Get instance of driver.
     *
     * @return instance of WebDriver.
     */
    public WebDriver getDriver() {
        return this.driver;
    }
}
