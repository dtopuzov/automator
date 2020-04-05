package tests.unit;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.condition.EnabledOnOs;
import org.junit.jupiter.api.condition.OS;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.remote.BrowserType;
import org.openset.automator.settings.SettingsLoadException;
import org.openset.automator.settings.base.BaseSettings;
import org.openset.automator.settings.base.EnvironmentType;
import org.openset.automator.settings.desktop.DesktopSettings;
import org.openset.automator.settings.web.WebSettings;
import org.openset.automator.test.common.enums.RestartType;

import java.io.File;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@DisplayName("Test for test settings")
class SettingsTests {

    @AfterEach
    void afterEach() {
        System.clearProperty("config");
    }

    @Nested
    @DisplayName("BaseSettings tests")
    class BaseSettingsTests {
        @Test
        @DisplayName("Init default base settings")
        void testBaseSettingsDefaults() {
            BaseSettings settings = new BaseSettings();
            assertNotNull(settings.properties, "Properties should not be null.");
            assertEquals(30, settings.defaultWait, "Default wait is not correct.");
            assertEquals(EnvironmentType.LOCAL, settings.environmentType, "Default environmentType is not correct");
            assertEquals(RestartType.NONE, settings.restartType, "Default restartType is not correct.");
        }

        @Test
        @DisplayName("Init base settings from config file")
        void testBaseSettingsWithNotExistingConfigFile() {
            assertThrows(SettingsLoadException.class, () -> {
                System.setProperty("config", "none.properties");
                new BaseSettings();
            });
        }
    }

    @Nested
    @DisplayName("DesktopSettings tests")
    class DesktopSettingsTests {
        @Test
        void testDesktopSettingsWithConfigFile() {
            System.setProperty("config", "morse.windows");
            DesktopSettings settings = new DesktopSettings();
            assertEquals(10, settings.base.defaultWait, "defaultWait is not correct.");
            assertEquals("morsecodetranslator", settings.desktop.appName, "appName not set correctly.");
            assertEquals(settings.base.testAppFolder + File.separator + "morsecodetranslator.jar",
                    settings.desktop.appPath, "appPath not set correctly.");
        }

        @Test
        @EnabledOnOs({OS.WINDOWS})
        void testDesktopSettingsWithConfigFileWithStandartWindowsApplication() {
            System.setProperty("config", "notepad");
            DesktopSettings settings = new DesktopSettings();
            assertEquals(15, settings.base.defaultWait, "defaultWait is not correct.");
            assertEquals("notepad", settings.desktop.appName, "appName not set correctly.");
            assertEquals("C:\\Windows\\notepad.exe", settings.desktop.appPath, "appPath not set correctly.");
        }

        @Test
        void testDesktopSettingsWithWrongConfigFile() {
            assertThrows(SettingsLoadException.class, () -> {
                System.setProperty("config", "none");
                new DesktopSettings();
            });
        }

        @Test
        void testDesktopSettingsWithOutConfigFile() {
            assertThrows(SettingsLoadException.class, DesktopSettings::new);
        }
    }

    @Nested
    @DisplayName("WebSettings tests")
    class WebSettingsTests {
        @Test
        void testWebSettingsWithConfigFile() {
            System.setProperty("config", "github.local.chrome");
            WebSettings settings = new WebSettings();
            assertEquals(BrowserType.CHROME, settings.web.browserType);
            assertEquals(new Dimension(1280, 1024), settings.web.browserSize);
            assertEquals("https://github.com/", settings.web.baseUrl);
            assertEquals(true, settings.web.headless);
            assertEquals(10, settings.base.defaultWait);
        }
    }
}
