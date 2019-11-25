package web.settings;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.remote.BrowserType;
import settings.BaseSettings;

import java.util.Properties;

public class WebSettings extends BaseSettings {
    public Boolean headless;
    public String browserType;
    public String baseUrl;
    public Dimension browserSize;

    /**
     * Init default web settings.
     */
    public WebSettings() {
        super();
        this.headless = !this.isDebug;
        this.browserType = BrowserType.CHROME;
        this.baseUrl = "https://market.nativescript.org/";
        this.browserSize = new Dimension(1280, 1024);

    }

    public WebSettings(Properties properties) {

    }
}
