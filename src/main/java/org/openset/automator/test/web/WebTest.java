package org.openset.automator.test.web;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.openset.automator.image.Image;
import org.openset.automator.test.common.exceptions.TakeScreenshotException;
import org.openset.automator.test.common.extensions.Resolver;
import org.openset.automator.test.common.extensions.Watcher;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;

/**
 * Base org.automator.web test.
 */
@ExtendWith({Resolver.class, Watcher.class, WebHooks.class})
public class WebTest {
    protected WebContext context = WebContextFactory.getInstance().getWebContext();

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
