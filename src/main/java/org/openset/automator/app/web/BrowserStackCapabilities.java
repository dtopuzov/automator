package org.openset.automator.app.web;

import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openset.automator.settings.web.WebSettings;

import java.util.HashMap;
import java.util.Properties;

public class BrowserStackCapabilities {
    public static MutableCapabilities getCapabilities(WebSettings settings) {
        MutableCapabilities capabilities = new MutableCapabilities();
        Properties properties = settings.browserStack.getBrowserStackProperties();
        HashMap<String, Object> browserstackOptions = new HashMap<>();
        properties.forEach((key, val) -> {
            if (key.toString().toLowerCase().contains("browserstack")) {
                String capability = key.toString().replace("browserstack.", "");
                if (capability.equals("browserName")) {
                    capabilities.setCapability("browserName", val);
                    // Enrich capabilities with browser specific options
                    if (val.toString().toLowerCase().equals("chrome")) {
                        ChromeOptions chromeOptions = LocalCapabilities.getChromeOptions(settings.web);
                        capabilities.setCapability(ChromeOptions.CAPABILITY, chromeOptions);
                    } else if (val.toString().toLowerCase().equals("firefox")) {
                        FirefoxOptions firefoxOptions = LocalCapabilities.getFirefoxOptions(settings.web);
                        capabilities.setCapability(ChromeOptions.CAPABILITY, firefoxOptions);
                    }
                } else if (capability.equals("browserVersion")) {
                    capabilities.setCapability("browserVersion", val);
                } else {
                    browserstackOptions.put(key.toString().replace("browserstack.", ""), val);
                }
            }
        });
        capabilities.setCapability("bstack:options", browserstackOptions);
        return capabilities;
    }
}
