package org.openset.automator.app.web;

import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openset.automator.settings.web.WebSettings;

/**
 * SauceLabs capabilities.
 */
public class SauceLabsCapabilities {
    /**
     * Get capabilities for SauceLabs session.
     *
     * @param settings instance of WebSettings.
     * @return MutableCapabilities.
     */
    public static MutableCapabilities getCapabilities(WebSettings settings) {
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.setExperimentalOption("w3c", true);
        chromeOptions.setCapability("platformName", settings.sauce.platformName);
        if (settings.sauce.browserVersion != null) {
            chromeOptions.setCapability("browserVersion", settings.sauce.browserVersion);
        }
        MutableCapabilities capabilities = new MutableCapabilities();
        capabilities.setCapability(ChromeOptions.CAPABILITY, chromeOptions);
        return capabilities;
    }
}
