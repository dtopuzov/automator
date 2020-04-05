package org.openset.automator.app.web;

import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openset.automator.settings.web.WebSettings;

import java.util.HashMap;
import java.util.Properties;

public class SauceLabsCapabilities {
    public static MutableCapabilities getCapabilities(WebSettings settings) {
        MutableCapabilities capabilities = new MutableCapabilities();
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.setExperimentalOption("w3c", true);
        chromeOptions.setCapability("platformName", settings.sauce.platformName);
        if (settings.sauce.browserVersion != null) {
            chromeOptions.setCapability("browserVersion", settings.sauce.browserVersion);
        }
        capabilities.setCapability(ChromeOptions.CAPABILITY, chromeOptions);
        return capabilities;
    }
}
