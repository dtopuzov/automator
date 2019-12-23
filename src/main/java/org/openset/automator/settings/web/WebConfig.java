package org.openset.automator.settings.web;

import org.openset.automator.settings.BaseSettings;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.remote.BrowserType;

public class WebConfig {
    public Boolean headless;
    public String browserType;
    public String baseUrl;
    public Dimension browserSize;

    /**
     * Init default org.automator.web org.automator.core.settings.
     */
    public WebConfig(BaseSettings settings) {
        this.headless = !settings.isDebug;
        this.browserType = BrowserType.CHROME;
        this.baseUrl = "https://market.nativescript.org/";
        this.browserSize = new Dimension(1280, 1024);

    }
}
