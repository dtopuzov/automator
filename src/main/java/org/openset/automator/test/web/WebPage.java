package org.openset.automator.test.web;

import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

/**
 * Base org.automator.web page abstraction.
 */
public abstract class WebPage {

    private WebDriver driver;
    private WebContext webContext;

    /**
     * Init base org.automator.web page.
     *
     * @param webContext instance of WebContext.
     */
    public WebPage(WebContext webContext) {
        this.webContext = webContext;
        this.driver = webContext.browser.getDriver();
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    /**
     * Get page title.
     *
     * @return page title as String.
     */
    public WebDriver getDriver() {
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
        return getWait(Duration.ofSeconds(webContext.settings.base.defaultWait));
    }

    /**
     * Get page title.
     *
     * @return page title as String.
     */
    public String getTitle() {
        return driver.getTitle();
    }

    /**
     * Get page url.
     *
     * @return page url as String.
     */
    public String getUrl() {
        return driver.getCurrentUrl();
    }

    /**
     * Get url of link by text.
     *
     * @param text partial text of the link.
     * @return url of the link as String.
     */
    public String getLinkByText(String text) {
        WebElement link = this.driver.findElement(By.partialLinkText(text));
        return link.getAttribute("href");
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
