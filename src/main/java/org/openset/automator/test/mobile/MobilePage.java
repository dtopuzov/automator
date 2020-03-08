package org.openset.automator.test.mobile;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openset.automator.test.web.WebContext;

import java.time.Duration;

/**
 * Base mobile abstraction.
 */
public abstract class MobilePage {

    private AppiumDriver driver;
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
    public AppiumDriver getDriver() {
        return driver;
    }

    /**
     * Get WebDriverWait.
     *
     * @param duration duration.
     * @return WebDriverWait object.
     */
    public WebDriverWait getWait(Duration duration) {
        return new WebDriverWait(driver, duration.getSeconds());
    }

    /**
     * Get WebDriverWait.
     *
     * @return WebDriverWait object.
     */
    public WebDriverWait getWait() {
        return getWait(Duration.ofSeconds(mobileContext.settings.base.defaultWait));
    }

    /**
     * Check if element is visible.
     *
     * @param locator of the element.
     * @return true if visible.
     */
    public boolean isElementVisible(By locator) {
        try {
            getWait().until(ExpectedConditions.visibilityOfElementLocated(locator));
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
