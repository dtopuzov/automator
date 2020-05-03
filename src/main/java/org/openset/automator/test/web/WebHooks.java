package org.openset.automator.test.web;

import org.junit.jupiter.api.extension.BeforeAllCallback;
import org.junit.jupiter.api.extension.ExtensionContext;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.extension.ExtensionContext.Namespace.GLOBAL;

public class WebHooks implements BeforeAllCallback, ExtensionContext.Store.CloseableResource {

    private static ThreadLocal<Boolean> systemReady = ThreadLocal.withInitial(() -> false);

    private static List<WebContext> webContexts = new ArrayList<>();

    /**
     * Init WebContext instance and start browser.
     *
     * <p>Separate method with 'synchronized static' required for make sure procedure will be executed
     * only once across all simultaneously running threads
     */
    private static synchronized void systemSetup() {
        // 'if' is used to make sure procedure will be executed only once, not before every class
        Boolean isSystemReady = systemReady.get();
        if (isSystemReady != null) {
            if (!isSystemReady) {
                systemReady.set(true);
                WebContext webContext = WebContextFactory.getInstance().getWebContext();
                webContext.browser.start();
                if (webContext.settings.web.browserSize == null)
                {
                    webContext.browser.maximize();
                }

                webContexts.add(webContext);
            }
        }
    }

    /**
     * Initial setup of system.
     *
     * @param context junit context
     */
    @Override
    public void beforeAll(ExtensionContext context) {
        systemSetup();
        context.getRoot().getStore(GLOBAL).put(systemReady, this);
    }

    /**
     * Stop running browsers.
     *
     * <p>CloseableResource implementation, adding value into GLOBAL context is required to  registers a callback hook
     * With such steps close() method will be executed only once in the end of test execution
     */
    @Override
    public synchronized void close() {
        // clean data from system
        for (WebContext webContext : webContexts) {
            if (webContext.browser != null) {
                webContext.browser.stop();
            }
        }
    }
}