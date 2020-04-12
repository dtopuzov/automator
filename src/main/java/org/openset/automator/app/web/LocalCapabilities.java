package org.openset.automator.app.web;

import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.safari.SafariOptions;
import org.openset.automator.app.StartApplicationException;
import org.openset.automator.settings.web.WebConfig;

import java.util.HashMap;

/**
 * Local capabilities for web testing.
 */
public class LocalCapabilities {
    /**
     * Get capabilities for local web testing.
     *
     * @param webConfig instance of WebConfig.
     * @return MutableCapabilities.
     */
    public static MutableCapabilities getCapabilities(WebConfig webConfig) {
        MutableCapabilities capabilities;
        switch (webConfig.browserType) {
            case BrowserType.CHROME:
                capabilities = getChromeOptions(webConfig);
                capabilities.setCapability(CapabilityType.BROWSER_NAME, BrowserType.CHROME);
                break;
            case BrowserType.FIREFOX:
                capabilities = getFirefoxOptions(webConfig);
                capabilities.setCapability(CapabilityType.BROWSER_NAME, BrowserType.FIREFOX);
                break;
            case BrowserType.EDGE:
                capabilities = getEdgeOptions();
                capabilities.setCapability(CapabilityType.BROWSER_NAME, BrowserType.EDGE);
                break;
            case BrowserType.SAFARI:
                capabilities = getSafariOptions();
                capabilities.setCapability(CapabilityType.BROWSER_NAME, BrowserType.SAFARI);
                break;
            default:
                String message = String.format("Unsupported browser type: %s", webConfig.browserType);
                throw new StartApplicationException(message);
        }
        return capabilities;
    }

    /**
     * Get ChromeOptions based on WebConfig settings.
     *
     * @param webConfig instance of WebConfig.
     * @return ChromeOptions.
     */
    public static ChromeOptions getChromeOptions(WebConfig webConfig) {
        ChromeOptions options = new ChromeOptions();
        HashMap<String, String> prefs = new HashMap<>();
        prefs.put("intl.accept_languages", "en-US");
        options.setExperimentalOption("prefs", prefs);
        if (webConfig.headless) {
            options.addArguments("headless");
        }
        if (webConfig.browserSize != null) {
            options.addArguments(String.format("--window-size=%s,%s",
                    webConfig.browserSize.getWidth(),
                    webConfig.browserSize.getHeight()));
        }
        return options;
    }

    /**
     * Get FirefoxOptions based on WebConfig settings.
     *
     * @param webConfig instance of WebConfig.
     * @return FirefoxOptions.
     */
    public static FirefoxOptions getFirefoxOptions(WebConfig webConfig) {
        FirefoxOptions options = new FirefoxOptions();
        options.setAcceptInsecureCerts(true);
        if (webConfig.headless) {
            options.addArguments("-headless");
        }
        if (webConfig.browserSize != null) {
            options.addArguments("-width=" + webConfig.browserSize.getWidth());
            options.addArguments("-height=" + webConfig.browserSize.getHeight());
        }
        return options;
    }

    public static EdgeOptions getEdgeOptions() {
        return new EdgeOptions();
    }

    public static SafariOptions getSafariOptions() {
        return new SafariOptions();
    }
}
