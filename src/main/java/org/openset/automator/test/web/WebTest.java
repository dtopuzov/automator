package org.openset.automator.test.web;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.openset.automator.app.web.Browser;
import org.openset.automator.image.Image;
import org.openset.automator.settings.web.WebSettings;
import org.openset.automator.test.common.BaseTestExtension;
import org.openset.automator.test.common.TakeScreenshotException;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;

/**
 * Base org.automator.web test.
 */
@ExtendWith(BaseTestExtension.class)
public class WebTest {
    protected static WebContext context;

    /**
     * Logic executed once before all tests:
     * - Init org.automator.core.settings.
     * - Start Browser.
     */
    @BeforeAll
    public static void beforeAll() {
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
    public void afterEach(ExtensionContext extensionContext) {
        Method testMethod = extensionContext.getRequiredTestMethod();
        boolean testFailed = extensionContext.getExecutionException().isPresent();
        if (testFailed) {
            collectArtifacts(testMethod.getName());
        }
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

    private void collectArtifacts(String testName) {
        // Get screenshot
        try {
            String basePath = context.settings.base.testScreenshotsFolder;
            BufferedImage image = context.browser.getScreenshot();
            Image.save(image, basePath + File.separator + testName + ".png");
        } catch (IOException e) {
            throw new TakeScreenshotException("Failed to take screenshot of current browser.", e);
        }

        // Get logs
    }
}
