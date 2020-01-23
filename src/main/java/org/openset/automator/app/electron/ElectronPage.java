package org.openset.automator.app.electron;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openset.automator.app.Page;
import org.openset.automator.settings.electron.ElectronSettings;
import org.openset.automator.test.electron.ElectronContext;

import java.time.Duration;

/**
 * Base org.automator.web page abstraction.
 */
public abstract class ElectronPage implements Page {

    private WebDriver driver;

    public ElectronPage(ElectronContext context) {
        this(context.getApp(), context.getSettings());
    }

    /**
     * Init base org.automator.web page.
     */
    public ElectronPage(ElectronApp app, ElectronSettings settings) {
        this.driver = app.getDriver();
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
        return new WebDriverWait(driver, duration);
    }
}
