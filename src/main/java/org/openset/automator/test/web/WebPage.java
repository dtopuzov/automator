package org.openset.automator.test.web;

import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

/**
 * Base org.automator.web page abstraction.
 */
public abstract class WebPage {

    private WebDriver driver;

    /**
     * Init base org.automator.web page.
     *
     * @param webContext instance of WebContext.
     */
    public WebPage(WebContext webContext) {
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
     * Get page title.
     *
     * @return page title as String.
     */
    public String getTitle() {
        return driver.getTitle();
    }

    /**
     * Get url of link by partial link text.
     *
     * @param text partial link text.
     * @return url of the link as String.
     */
    public String getLink(String text) {
        WebElement link = driver.findElement(By.partialLinkText(text));
        return link.getAttribute("href");
    }
}
