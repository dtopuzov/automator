package org.openset.automator.app.web;

import org.openqa.selenium.MutableCapabilities;
import org.openset.automator.settings.SettingsLoadException;
import org.openset.automator.settings.base.EnvironmentType;
import org.openset.automator.settings.web.WebSettings;

/**
 * Capabilities for web testing.
 */
public class Capabilities {
    private WebSettings settings;

    /**
     * Init.
     *
     * @param settings instance of WebSettings.
     */
    public Capabilities(WebSettings settings) {
        this.settings = settings;
    }

    /**
     * Get capabilites for web testing.
     *
     * @return MutableCapabilities.
     */
    public MutableCapabilities getCapabilities() {
        MutableCapabilities capabilities;
        if (settings.base.environmentType == EnvironmentType.LOCAL) {
            capabilities = LocalCapabilities.getCapabilities(settings.web);
        } else if (settings.base.environmentType == EnvironmentType.BROWSERSTACK) {
            capabilities = BrowserStackCapabilities.getCapabilities(settings);
        } else if (settings.base.environmentType == EnvironmentType.SAUCELABS) {
            capabilities = SauceLabsCapabilities.getCapabilities(settings);
        } else {
            String message = String.format("Unsupported environmetn type: %s", settings.base.environmentType);
            throw new SettingsLoadException(message);
        }
        return capabilities;
    }
}
