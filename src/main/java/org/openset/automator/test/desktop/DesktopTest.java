package org.openset.automator.test.desktop;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openset.automator.app.desktop.DesktopApp;
import org.openset.automator.settings.desktop.DesktopSettings;
import org.openset.automator.test.common.TestResultWatcher;

@ExtendWith(TestResultWatcher.class)
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
    public void afterEach() {

    }

    @AfterAll
    public static void afterAll() {
        if (context.getApp() != null) {
            context.getApp().stop();
        }
    }
}
