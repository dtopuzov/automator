package org.openset.automator.test.web;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openset.automator.settings.web.WebSettings;

import java.util.concurrent.TimeUnit;

/**
 * Browser abstraction.
 */
@SuppressWarnings({"WeakerAccess", "unused"})
public class Browser {
    private WebDriver driver;
    private WebSettings settings;

    /**
     * Initialize browser.
     *
     * @param webSettings instance of WebSettings.
     * @throws Exception when try to init unsupported browser.
     */
    public Browser(WebSettings webSettings) throws Exception {
        this.settings = webSettings;
        switch (settings.web.browserType) {
            case BrowserType.CHROME:
                WebDriverManager.chromedriver().setup();
                break;
            case BrowserType.FIREFOX:
                WebDriverManager.firefoxdriver().setup();
                break;
            default:
                String message = String.format("Unsupported browser type: %s", settings.web.browserType);
                throw new Exception(message);
        }
    }

    /**
     * Start browser.
     *
     * @throws Exception when try to start unsupported browser.
     */
    public void start() throws Exception {
        switch (settings.web.browserType) {
            case BrowserType.CHROME:
                ChromeOptions options = new ChromeOptions();
                if (this.settings.web.headless) {
                    options.addArguments("headless");
                }
                if (this.settings.web.browserSize != null) {
                    options.addArguments(String.format("window-size=%sx%s",
                            this.settings.web.browserSize.getWidth(),
                            this.settings.web.browserSize.getWidth()));
                }
                this.driver = new ChromeDriver(options);
                break;
            case BrowserType.FIREFOX:
                this.driver = new FirefoxDriver();
                break;
            default:
                String message = String.format("Unsupported browser type: %s", settings.web.browserType);
                throw new Exception(message);
        }
        if (settings.base.wait != null) {
            this.driver.manage().timeouts().implicitlyWait(settings.base.wait, TimeUnit.SECONDS);
        }
    }

    /**
     * Stop browser.
     */
    public void stop() {
        if (this.driver != null) {
            this.driver.quit();
        }
    }

    /**
     * Navigate to url.
     *
     * @param url as String.
     */
    public void navigateTo(String url) {
        if (!this.driver.getCurrentUrl().equals(url)) {
            this.driver.navigate().to(url);
        }
    }

    /**
     * Get current Url.
     *
     * @return url as String.
     */
    public String getUrl() {
        return this.driver.getCurrentUrl();
    }

    /**
     * Maximize browser.
     */
    public void maximize() {
        this.driver.manage().window().maximize();
    }

    /**
     * Set browser size.
     *
     * @param dimension object of type org.openqa.selenium.Dimension.
     */
    public void setSize(Dimension dimension) {
        this.driver.manage().window().setSize(dimension);
    }

    /**
     * Set cookie.
     *
     * @param cookie object of type Cookie.
     */
    public void setCookie(Cookie cookie) {
        this.driver.manage().addCookie(cookie);
    }

    /**
     * Get browser this.driver.
     *
     * @return instance of Webthis.driver.
     */
    public WebDriver getDriver() {
        return this.driver;
    }

    public void refresh() {
        this.driver.navigate().refresh();
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
        return this.find(locator, settings.base.wait);
    }

    public void click(By locator, int timeout) {
        this.find(locator, timeout).click();
    }

    public void click(By locator) {
        this.click(locator, settings.base.wait);
    }
}
