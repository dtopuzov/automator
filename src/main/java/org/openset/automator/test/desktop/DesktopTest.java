package org.openset.automator.test.desktop;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openset.automator.app.desktop.DesktopApp;
import org.openset.automator.settings.desktop.DesktopSettings;

public class DesktopTest {
    protected static DesktopContext context;

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
