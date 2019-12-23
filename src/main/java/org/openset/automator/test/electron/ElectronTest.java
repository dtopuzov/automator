package org.openset.automator.test.electron;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openset.automator.app.electron.ElectronApp;
import org.openset.automator.settings.electron.ElectronSettings;

/**
 * Base org.automator.electron test.
 */
public class ElectronTest {

    private static ElectronSettings settings;
    private static ElectronApp app;

    @BeforeAll
    public static void beforeAll() {
        settings = new ElectronSettings();
        app = new ElectronApp(settings);
        app.start();
    }

    @BeforeEach
    public void beforeEach() {

    }

    @AfterEach
    public void afterEach() {
    }

    /**
     * Logic executed after all tests:
     * - Stop running application.
     */
    @AfterAll
    public static void afterAll() {
        if (app != null) {
            app.stop();
        }
    }

    public ElectronSettings getSettings() {
        return settings;
    }

    protected ElectronApp getApp() {
        return app;
    }
}
