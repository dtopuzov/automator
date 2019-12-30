package org.openset.automator.settings.web;

import org.openset.automator.settings.base.BaseSettings;
import org.openset.automator.settings.cloud.SauceConfig;

/**
 * Settings in context of org.automator.web testing.
 */
public class WebSettings {
    public BaseSettings base;
    public WebConfig web;
    public SauceConfig sauce;

    public WebSettings() {
        base = new BaseSettings();
        web = new WebConfig(base);
        sauce = new SauceConfig(base);
    }
}
