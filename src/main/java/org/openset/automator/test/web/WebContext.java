package org.openset.automator.test.web;

import org.openset.automator.settings.web.WebSettings;

public class WebContext {
    public WebSettings settings;
    public Browser browser;

    public WebContext(WebSettings settings) {
        this.settings = settings;
    }
}
