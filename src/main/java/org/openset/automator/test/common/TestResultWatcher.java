package org.openset.automator.test.common;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestWatcher;
import org.openset.automator.image.Image;
import org.openset.automator.log.Log;
import org.openset.automator.log.LogFactory;
import org.openset.automator.test.desktop.DesktopTest;

import java.io.File;
import java.io.IOException;
import java.util.Optional;

public class TestResultWatcher implements TestWatcher {

    private static final Log LOGGER = LogFactory.getLogger(TestResultWatcher.class.getName());

    @Override
    public void testAborted(ExtensionContext extensionContext, Throwable throwable) {
        LOGGER.info(String.format("%s -> Aborted.", extensionContext.getDisplayName()));
    }

    @Override
    public void testDisabled(ExtensionContext extensionContext, Optional<String> optional) {
        LOGGER.info(String.format("%s -> Disabled.", extensionContext.getDisplayName()));
    }

    @Override
    public void testFailed(ExtensionContext extensionContext, Throwable throwable) {
        LOGGER.info(String.format("%s -> Failed.", extensionContext.getDisplayName()));
        collectArtifacts(extensionContext);
    }

    @Override
    public void testSuccessful(ExtensionContext extensionContext) {
        LOGGER.info(String.format("%s -> Passed.", extensionContext.getDisplayName()));
    }

    private void collectArtifacts(ExtensionContext extensionContext) {
        Object test = extensionContext.getRequiredTestInstance();

        // Get Screenshots
        if (test instanceof DesktopTest) {
            DesktopTest desktopTest = (DesktopTest) test;
            String basePath = desktopTest.getContext().getSettings().base.testScreenshotsFolder;
            String imageName = extensionContext.getRequiredTestMethod().getName() + ".png";
            try {
                Image.saveScreenshot(basePath + File.separator + imageName);
            } catch (IOException e) {
                throw new TakeScreenshotException("Failed to take screenshot of host OS.", e);
            }
        }
    }
}