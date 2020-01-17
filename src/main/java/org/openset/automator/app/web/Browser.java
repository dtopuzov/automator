package org.openset.automator.app.web;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Step;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openset.automator.app.App;
import org.openset.automator.app.StartApplicationException;
import org.openset.automator.log.Log;
import org.openset.automator.log.LogFactory;
import org.openset.automator.os.OSType;
import org.openset.automator.settings.base.EnvironmentType;
import org.openset.automator.settings.web.WebSettings;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Date;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

@SuppressWarnings({"unused", "UnusedReturnValue"})
public class Browser implements App {
    private static final Log LOGGER = LogFactory.getLogger(Browser.class.getName());
    private WebSettings settings;
    private WebDriver driver;
    private WebDriverWait wait;

    /**
     * Init browser object.
     *
     * @param settings WebSetting object.
     */
    public Browser(WebSettings settings) {
        this.settings = settings;
        switch (settings.web.browserType) {
            case BrowserType.CHROME:
                WebDriverManager.chromedriver().setup();
                break;
            case BrowserType.FIREFOX:
                WebDriverManager.firefoxdriver().setup();
                break;
            default:
                String message = String.format("Unsupported browser type: %s", settings.web.browserType);
                throw new StartApplicationException(message);
        }
    }

    /**
     * Start browser instance.
     *
     * @return self reference.
     */
    @Step("Start browser")
    public Browser start() {
        System.out.println("Start browser");
        if (settings.base.environmentType == EnvironmentType.LOCAL) {
            startLocal();
        } else if (settings.base.environmentType == EnvironmentType.SAUCELABS) {
            startSauce();
        }
        wait = new WebDriverWait(driver, settings.base.defaultWait);
        return this;
    }

    /**
     * Start browser instance on SauceLabs.
     */
    private void startSauce() {
        if ((settings.sauce.userName != null) && (settings.sauce.accessKey != null)) {
            String url = "http://" + settings.sauce.userName + ":" + settings.sauce.accessKey + "@ondemand.saucelabs.com:80/wd/hub";

            DesiredCapabilities caps = DesiredCapabilities.chrome();
            caps.setCapability("platformName", settings.sauce.platformName);
            if (settings.sauce.browserVersion != null) {
                caps.setCapability("browserVersion", settings.sauce.browserVersion);
            }

            try {
                driver = new RemoteWebDriver(new URL(url), caps);
            } catch (MalformedURLException e) {
                throw new StartApplicationException("Malformed URL exception.", e);
            }

            if (settings.base.defaultWait != null) {
                driver.manage().timeouts().implicitlyWait(settings.base.defaultWait, TimeUnit.SECONDS);
                driver.manage().timeouts().pageLoadTimeout(settings.base.defaultWait, TimeUnit.SECONDS);
            }
            LOGGER.info("SauceLabs session started!");
        } else {
            String error = "Can not start SauceLabs session without user and password.";
            LOGGER.fatal(error);
            throw new StartApplicationException(error);
        }
    }

    /**
     * Start local browser instance.
     */
    private void startLocal() {
        switch (settings.web.browserType) {
            case BrowserType.CHROME:
                ChromeOptions options = new ChromeOptions();
                HashMap<String, String> prefs = new HashMap<>();
                prefs.put("intl.accept_languages", "en-US");
                options.setExperimentalOption("prefs", prefs);
                if (settings.web.headless) {
                    options.addArguments("headless");
                }
                if (settings.web.browserSize != null) {
                    options.addArguments(String.format("--window-size=%s,%s",
                            settings.web.browserSize.getWidth(),
                            settings.web.browserSize.getHeight()));
                }
                driver = new ChromeDriver(options);
                break;
            case BrowserType.FIREFOX:
                driver = new FirefoxDriver();
                break;
            default:
                String message = String.format("Unsupported browser type: %s", settings.web.browserType);
                throw new StartApplicationException(message);
        }
        if (settings.base.defaultWait != null) {
            driver.manage().timeouts().implicitlyWait(settings.base.defaultWait, TimeUnit.SECONDS);
            driver.manage().timeouts().pageLoadTimeout(settings.base.defaultWait, TimeUnit.SECONDS);
        }
        LOGGER.info(String.format("%s browser started.", settings.web.browserType));
    }

    /**
     * Stop browser instance.
     */
    @Step("Stop browser")
    public void stop() {
        if (driver != null) {
            driver.quit();
        }
        LOGGER.info(String.format("%s browser stopped.", settings.web.browserType));
    }

    /**
     * Get browser driver.
     *
     * @return instance of WebDriver.
     */
    public WebDriver getDriver() {
        return driver;
    }

    /**
     * Get browser console logs.
     *
     * @return logs as String.
     */
    public String getLogs() {
        LogEntries logEntries = driver.manage().logs().get(LogType.BROWSER);
        StringBuilder logs = new StringBuilder();
        for (LogEntry entry : logEntries) {
            logs
                    .append(new Date(entry.getTimestamp()))
                    .append(" ")
                    .append(entry.getLevel())
                    .append(" ")
                    .append(entry.getMessage());
            logs.append(System.lineSeparator());

        }
        return logs.toString();
    }

    /**
     * Check if browser is running.
     *
     * @return True if browser is running.
     */
    public boolean isRunning() {
        return !driver.toString().contains("null");
    }

    /**
     * Get current Url.
     *
     * @return url as String.
     */
    public String getUrl() {
        return driver.getCurrentUrl();
    }

    /**
     * Navigate to URL.
     *
     * @param url   to be opened.
     * @param force if false it will first check current urls and will navigate only if urls deffer.
     * @return self reference.
     */
    @Step("Navigate to <url>")
    public Browser navigateTo(String url, boolean force) {
        if ((!force) && (driver.getCurrentUrl().equalsIgnoreCase(url))) {
            LOGGER.info(String.format("Current URL is already '%s'.", url));
        } else {
            driver.navigate().to(url);
            LOGGER.info(String.format("Navigate to '%s'.", url));
        }
        return this;
    }

    @Step("Navigate to <url>")
    public Browser navigateTo(String url) {
        return navigateTo(url, false);
    }

    /**
     * Maximize browser.
     *
     * @return self reference.
     */
    @Step("Maximize browser")
    public Browser maximize() {
        driver.manage().window().maximize();
        LOGGER.info("Maximize current browser");
        Dimension currentSize = driver.manage().window().getSize();
        String x = String.valueOf(currentSize.getHeight());
        String y = String.valueOf(currentSize.getHeight());
        LOGGER.info(String.format("Current resolution: %sx%s", x, y));
        return this;
    }

    /**
     * Set resolution of running browser.
     *
     * @param size Dimension object.
     * @return self reference.
     */
    @Step("Set browser size to {0}")
    public Browser setSize(Dimension size) {
        driver.manage().window().setSize(size);
        String x = String.valueOf(size.getHeight());
        String y = String.valueOf(size.getHeight());
        LOGGER.info(String.format("Resolution set to: %sx%s", x, y));
        return this;
    }

    /**
     * Get current resolution of running browser.
     *
     * @return org.openqa.selenium.Dimension object.
     */
    public Dimension getSize() {
        return driver.manage().window().getSize();
    }

    @Step("Refresh")
    public void refresh() {
        driver.navigate().refresh();
    }

    /**
     * Kill all instances of this browser type.
     * <p>
     * Call stop() and then kill all processes matching browser process name.
     *
     * @throws Exception When fail to kill a browser.
     */
    @Step("Force kill all browser processes")
    public void killAll() throws Exception {
        stop();
        org.openset.automator.os.Process.stop(getProcessName());
        LOGGER.info(String.format("All %s processes stopped.", settings.web.browserType));
    }

    /**
     * Get process name of current browser.
     *
     * @return Process name as String.
     * @throws Exception When process name is unknown.
     */
    public String getProcessName() throws Exception {
        if (settings.web.browserType.equalsIgnoreCase(BrowserType.CHROME)) {
            if (settings.base.os == OSType.MAC) {
                return "Google Chrome";
            } else if (settings.base.os == OSType.LINUX) {
                return "chrome";
            } else {
                return "chrome.exe";
            }
        } else if (settings.web.browserType.equalsIgnoreCase(BrowserType.SAFARI)) {
            return "Safari";
        } else if (settings.web.browserType.equalsIgnoreCase(BrowserType.FIREFOX)) {
            if (settings.base.os == OSType.MAC) {
                return "Firefox";
            } else if (settings.base.os == OSType.LINUX) {
                return "firefox";
            } else {
                return "firefox.exe";
            }
        } else {
            throw new Exception("Unknown browser type: " + settings.web.browserType);
        }
    }

    /**
     * Get screenshot.
     *
     * @return BufferedImage object.
     * @throws IOException When fail to take screenshot.
     */
    public BufferedImage getScreenshot() throws IOException {
        File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        return ImageIO.read(screenshot);
    }

    /**
     * Find element by locator.
     *
     * @param locator By locator.
     * @param timeout Timeout in seconds.
     * @return WebElement.
     */
    public WebElement find(By locator, int timeout) {
        new WebDriverWait(driver, timeout)
                .ignoring(StaleElementReferenceException.class)
                .until(ExpectedConditions.elementToBeClickable(locator));
        return driver.findElement(locator);
    }

    public WebElement find(By locator) {
        return find(locator, settings.base.defaultWait);
    }

    public void click(By locator, int timeout) {
        find(locator, timeout).click();
    }

    public void click(By locator) {
        click(locator, settings.base.defaultWait);
    }

    protected void waitForElementToAppear(By locator) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    protected void waitForElementToDisappear(By locator) {
        wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
    }

    protected void waitForTextToDisappear(By locator, String text) {
        wait.until(ExpectedConditions.not(ExpectedConditions.textToBe(locator, text)));
    }
}
