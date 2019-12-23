package unit;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openset.automator.os.EnvUtils;
import org.openset.automator.os.FileUtils;
import org.openset.automator.os.NetUtils;
import org.openset.automator.settings.BaseSettings;

import java.io.IOException;
import java.util.Properties;


public class UtilsTests {
    @Test
    void testEnvUtils() {
        String javaHome = EnvUtils.getEnvironmentVariable("JAVA_HOME", null);
        Assertions.assertNotNull(javaHome, "JAVA_HOME environment variable is not set!");
    }

    @Test
    void testFileUtils() throws IOException {
        BaseSettings settings = new BaseSettings(new Properties());
        String filePath = FileUtils.find(settings.testRunHome, "FileUtils.java");
        Assertions.assertTrue(filePath.contains("src"));
    }

    @Test
    void testNetUtils() {
        int port = NetUtils.nextFreePort(1000, 9000);
        Assertions.assertTrue(port >= 1000 && port <= 9000, "Failed to find free port.");
    }
}
