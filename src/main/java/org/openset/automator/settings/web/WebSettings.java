package org.openset.automator.settings.web;

import org.openset.automator.settings.base.BaseSettings;
import org.openset.automator.settings.cloud.SauceConfig;

/**
 * Settings in context of web testing.
 */
public class WebSettings {
    public BaseSettings base;
    public WebConfig web;
    public SauceConfig sauce;

    /**
     * Init web settings.
     */
    public WebSettings() {
        base = new BaseSettings();
        web = new WebConfig(base);
        sauce = new SauceConfig(base);
    }
}
