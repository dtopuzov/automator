package org.openset.automator.test.mobile;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

/**
 * Base mobile abstraction.
 */
@SuppressWarnings("unused")
public abstract class MobilePage {

    private AppiumDriver<?> driver;
    private MobileContext mobileContext;

    /**
     * Init base mobile page.
     *
     * @param mobileContext instance of WebContext.
     */
    public MobilePage(MobileContext mobileContext) {
        this.mobileContext = mobileContext;
        this.driver = mobileContext.app.getDriver();
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    /**
     * Get page title.
     *
     * @return page title as String.
     */
    public AppiumDriver<?> getDriver() {
        return driver;
    }

    /**
     * Get WebDriverWait with duration.
     *
     * @param duration duration.
     * @return WebDriverWait object.
     */
    public WebDriverWait getWait(Duration duration) {
        return new WebDriverWait(driver, duration.getSeconds());
    }

    /**
     * Get WebDriverWait with default wait timeout (from settings).
     *
     * @return WebDriverWait object.
     */
    public WebDriverWait getWait() {
        return getWait(Duration.ofSeconds(mobileContext.settings.base.defaultWait));
    }

    public MobileElement findElement(By locator, Duration timeout) {
        try {
            return (MobileElement) getWait(timeout).until(ExpectedConditions.visibilityOfElementLocated(locator));
        } catch (Exception e) {
            return null;
        }
    }

    public MobileElement findElement(By locator) {
        return findElement(locator, Duration.ofSeconds(mobileContext.settings.base.defaultWait));
    }

    public MobileElement findByText(String text, Duration timeout, boolean exactMatch) {
        By locator;
        if (exactMatch) {
            locator = MobileBy.AndroidUIAutomator("new UiSelector().text(\"" + text + "\")");
        } else {
            locator = MobileBy.AndroidUIAutomator("new UiSelector().textContains(\"" + text + "\")");
        }
        return findElement(locator, timeout);
    }

    public MobileElement findByText(String text, Duration timeout) {
        return findByText(text, timeout, true);
    }

    public MobileElement findByText(String text) {
        return findByText(text, Duration.ofSeconds(mobileContext.settings.base.defaultWait), true);
    }
}
