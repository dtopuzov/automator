package org.openset.automator.test.desktop;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.openset.automator.app.desktop.DesktopApp;
import org.openset.automator.image.Image;
import org.openset.automator.settings.desktop.DesktopSettings;
import org.openset.automator.test.common.extensions.Resolver;
import org.openset.automator.test.common.exceptions.TakeScreenshotException;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;

@ExtendWith(Resolver.class)
public abstract class DesktopTest {
    private static DesktopContext context;

    public DesktopContext getContext() {
        return context;
    }

    @BeforeAll
    public static void beforeAll() {
        DesktopSettings settings = new DesktopSettings();
        DesktopApp app = new DesktopApp(settings);
        context = new DesktopContext(settings, app);
        context.getApp().start();
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

    @AfterAll
    public static void afterAll() {
        if (context.getApp() != null) {
            context.getApp().stop();
        }
    }

    private void collectArtifacts(String testName) {
        // Get screenshot
        try {
            String basePath = context.getSettings().base.testScreenshotsFolder;
            Image.saveScreenshot(basePath + File.separator + testName + ".png");
        } catch (IOException e) {
            throw new TakeScreenshotException("Failed to take screenshot of host OS.", e);
        }

        // Get logs
    }
}
