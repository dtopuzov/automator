package web.base;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import web.browser.Browser;
import web.settings.WebSettings;

/**
 * Base web test.
 */
public class WebTest {
    protected static WebContext context;

    /**
     * Logic executed once before all tests:
     * - Init settings.
     * - Start Browser.
     *
     * @throws Exception when fail to initialize settings or start browser.
     */
    @BeforeAll
    public static void beforeAll() throws Exception {
        WebSettings settings = new WebSettings();
        context = new WebContext(settings);
        context.settings = settings;
        context.browser = new Browser(context.settings);
        context.browser.start();
        if (context.settings.browserSize == null) {
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
