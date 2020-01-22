package org.openset.automator.test.web;

import org.openset.automator.app.web.Browser;
import org.openset.automator.settings.web.WebSettings;

public class WebContextFactory {

    private WebContextFactory() {
        // Do not allow to initialize this class from outside.
    }

    private static WebContextFactory instance = new WebContextFactory();

    public static WebContextFactory getInstance() {
        return instance;
    }

    ThreadLocal<WebContext> webContext = ThreadLocal.withInitial(() -> {
        WebSettings settings = new WebSettings();
        WebContext context = new WebContext(settings);
        context.settings = settings;
        context.browser = new Browser(context.settings);
        return context;
    });

    public WebContext getWebContext() {
        return webContext.get();
    }
}
