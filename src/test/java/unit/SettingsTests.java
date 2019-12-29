package unit;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.EnabledOnOs;
import org.junit.jupiter.api.condition.OS;
import org.openset.automator.settings.SettingsLoadException;
import org.openset.automator.settings.base.BaseSettings;
import org.openset.automator.settings.desktop.DesktopSettings;

import java.io.File;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Test for test settings")
public class SettingsTests {

    @BeforeEach
    void beforeEach() {
        System.clearProperty("config");
    }

    @Nested
    @DisplayName("BaseSettings tests")
    class BaseSettingsTests {
        @Test
        void testBaseSettingsDefaults() {
            BaseSettings settings = new BaseSettings();
            assertNotNull(settings.properties, "Properties should not be null.");
            assertEquals(30, settings.defaultWait, "Default wait is not correct.");
        }
    }

    @Nested
    @DisplayName("DesktopSettings tests")
    class DesktopSettingsTests {
        @Test
        void testDesktopSettingsWithConfigFile() {
            System.setProperty("config", "morse");
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
}
