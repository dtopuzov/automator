package org.openset.automator.test.web;

import org.openqa.selenium.WebDriver;
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
        PageFactory.initElements(driver, this);
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
     * Get WebDriverWait.
     *
     * @param duration duration.
     * @return WebDriverWait object.
     */
    public WebDriverWait getWait(Duration duration) {
        return new WebDriverWait(driver, duration.getSeconds());
    }
}
