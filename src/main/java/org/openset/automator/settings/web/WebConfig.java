package org.openset.automator.settings.web;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.remote.BrowserType;
import org.openset.automator.settings.base.BaseSettings;

public class WebConfig {
    public Boolean headless;
    public String browserType;
    public String baseUrl;
    public Dimension browserSize;

    /**
     * Init default org.automator.web org.automator.core.settings.
     */
    public WebConfig(BaseSettings settings) {
        this.headless = settings.propertyToBoolean("headless", false);
        this.browserType = settings.properties.getProperty("browserType", BrowserType.CHROME).toLowerCase();
        this.baseUrl = settings.properties.getProperty("baseUrl", null);
        this.browserSize = getDimension(settings);
    }

    private Dimension getDimension(BaseSettings settings) {
        String browserSize = settings.properties.getProperty("browserSize");
        if (browserSize == null) {
            return null;
        } else {
            return new Dimension(1280, 1024);
        }
    }
}
