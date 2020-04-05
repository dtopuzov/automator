package org.openset.automator.settings.web;

import org.openset.automator.settings.base.BaseSettings;
import org.openset.automator.settings.base.EnvironmentType;
import org.openset.automator.settings.cloud.BrowserStackConfig;
import org.openset.automator.settings.cloud.SauceConfig;

/**
 * Settings in context of web testing.
 */
public class WebSettings {
    public BaseSettings base;
    public WebConfig web;
    public SauceConfig sauce;
    public BrowserStackConfig browserStack;

    /**
     * Init web settings.
     */
    public WebSettings() {
        base = new BaseSettings();
        web = new WebConfig(base);
        if (base.environmentType == EnvironmentType.SAUCELABS) {
            sauce = new SauceConfig(base);
        }
        if (base.environmentType == EnvironmentType.BROWSERSTACK) {
            browserStack = new BrowserStackConfig(base);
        }
    }

}
