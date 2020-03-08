package org.openset.automator.test.mobile;

import io.qameta.allure.Allure;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.openset.automator.image.Image;
import org.openset.automator.test.common.exceptions.TakeScreenshotException;
import org.openset.automator.test.common.extensions.Resolver;
import org.openset.automator.test.common.extensions.Watcher;
import org.openset.automator.test.web.WebContext;
import org.openset.automator.test.web.WebHooks;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.lang.reflect.Method;

/**
 * Base org.automator.web test.
 */
@ExtendWith({Resolver.class, Watcher.class, MobileHooks.class})
public class MobileTest {
    protected MobileContext context = MobileContextFactory.getInstance().getMobileContext();

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
            BufferedImage image = context.app.getScreenshot();
            Image.save(image, basePath + File.separator + testName + ".png");
            Allure.addAttachment("Screenshot on test fail", "image/png", Image.bufferedImageToInputStream(image), ".png");
        } catch (IOException e) {
            throw new TakeScreenshotException("Failed to take screenshot of mobile app.", e);
        }

        // Get device logs
    }

}
