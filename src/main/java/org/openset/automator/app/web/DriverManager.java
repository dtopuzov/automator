package org.openset.automator.app.web;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.safari.SafariOptions;
import org.openset.automator.app.StartApplicationException;
import org.openset.automator.settings.SettingsLoadException;
import org.openset.automator.settings.base.EnvironmentType;
import org.openset.automator.settings.web.WebSettings;

public class DriverManager {

    private final WebSettings settings;

    public DriverManager(WebSettings settings) {
        this.settings = settings;
    }

    /**
     * Download and setup driver for desired browser.
     */
    public void setupDriver() {
        if (settings.base.environmentType == EnvironmentType.LOCAL) {
            switch (settings.web.browserType) {
                case BrowserType.CHROME:
                    WebDriverManager.chromedriver().setup();
                    break;
                case BrowserType.FIREFOX:
                    WebDriverManager.firefoxdriver().setup();
                    break;
                case BrowserType.EDGE:
                    WebDriverManager.edgedriver().setup();
                    break;
                case BrowserType.SAFARI:
                    break;
                default:
                    String message = String.format("Unsupported browser type: %s", settings.web.browserType);
                    throw new StartApplicationException(message);
            }
        }
    }

    /**
     * Start browser and retun associated WebDriver instance.
     *
     * @return instance of WebDriver.
     */
    public WebDriver getDriver() {
        if (settings.base.environmentType == EnvironmentType.LOCAL) {
            return getLocalBrowser();
        } else if (settings.base.environmentType == EnvironmentType.SAUCELABS) {
            return getSauceLabsBrowser();
        } else if (settings.base.environmentType == EnvironmentType.BROWSERSTACK) {
            return getBrowserStackBrowser();
        } else {
            String message = String.format("Unsupported environmetn type: %s", settings.base.environmentType);
            throw new SettingsLoadException(message);
        }
    }

    /**
     * Start local browser instance.
     */
    private WebDriver getLocalBrowser() {
        MutableCapabilities capabilities = new Capabilities(settings).getCapabilities();
        switch (settings.web.browserType) {
            case BrowserType.CHROME:
                return new ChromeDriver((ChromeOptions) capabilities);
            case BrowserType.FIREFOX:
                return new FirefoxDriver((FirefoxOptions) capabilities);
            case BrowserType.EDGE:
                return new EdgeDriver((EdgeOptions) capabilities);
            case BrowserType.SAFARI:
                return new SafariDriver((SafariOptions) capabilities);
            default:
                String message = String.format("Unsupported local browser type: %s", settings.web.browserType);
                throw new StartApplicationException(message);
        }
    }

    /**
     * Start browser instance on BrowserStack.
     */
    private WebDriver getBrowserStackBrowser() {
        MutableCapabilities capabilities = new Capabilities(settings).getCapabilities();
        return new RemoteWebDriver(settings.browserStack.getUrl(), capabilities);
    }

    /**
     * Start browser instance on SauceLabs.
     */
    private WebDriver getSauceLabsBrowser() {
        MutableCapabilities capabilities = new Capabilities(settings).getCapabilities();
        return new RemoteWebDriver(settings.sauce.url, capabilities);
    }
}
