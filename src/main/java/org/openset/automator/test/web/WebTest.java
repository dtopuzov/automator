package org.openset.automator.test.web;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openset.automator.settings.web.WebSettings;

/**
 * Base org.automator.web test.
 */
public class WebTest {
    protected static WebContext context;

    /**
     * Logic executed once before all tests:
     * - Init org.automator.core.settings.
     * - Start Browser.
     *
     * @throws Exception when fail to initialize org.automator.core.settings or start browser.
     */
    @BeforeAll
    public static void beforeAll() throws Exception {
        WebSettings settings = new WebSettings();
        context = new WebContext(settings);
        context.settings = settings;
        context.browser = new Browser(context.settings);
        context.browser.start();
        if (context.settings.web.browserSize == null) {
            context.browser.maximize();
        }
    }

    @BeforeEach
    public void beforeEach() {

    }

    @AfterEach
    public void afterEach() {

    }

    /**
     * Logic executed after all tests:
     * - Stop running browser.
     */
    @AfterAll
    public static void afterAll() {
        if (context.browser != null) {
            context.browser.stop();
        }
    }
}
