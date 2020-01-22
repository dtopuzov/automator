package unit;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
import org.junit.jupiter.api.condition.EnabledOnOs;
import org.openset.automator.os.Process;
import org.openset.automator.os.*;
import org.openset.automator.settings.base.BaseSettings;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import static java.util.concurrent.TimeUnit.SECONDS;
import static org.junit.jupiter.api.Assertions.assertTrue;


class UtilsTests {

    @Test
    void testEnvUtils() {
        String javaHome = OS.getEnvironmentVariable("JAVA_HOME", null);
        Assertions.assertNotNull(javaHome, "JAVA_HOME environment variable is not set!");
    }

    @Test
    void testFileUtils() throws IOException {
        BaseSettings settings = new BaseSettings();
        System.out.println(settings.testRunHome);
        String filePath = File.find(settings.testRunHome, "File.java");
        assertTrue(filePath.contains("src"));
    }

    @Test
    void testNetUtils() {
        int port = Network.nextFreePort(1000, 9000);
        assertTrue(port >= 1000 && port <= 9000, "Failed to find free port.");
    }

    @Test
    void testProcessCollectOutput() throws InterruptedException, TimeoutException, IOException {
        String[] command = {"java", "--version"};
        String output = Process.start(command).getOutput().toLowerCase();
        assertTrue(output.contains("java") || output.contains("openjdk"), "Failed to collect command output.");
    }

    @Test
    void testProcessIsRunningByExecutableName() {
        assertTrue(Process.isRunning(getProcessName("java")), "Java process should be running.");
    }

    @Test
    void testProcessIsRunningByCommandline() {
        assertTrue(Process.isRunning(getProcessName("java"), "java"), "Java process should be running.");
    }

    @Test
    @EnabledOnOs({org.junit.jupiter.api.condition.OS.LINUX, org.junit.jupiter.api.condition.OS.MAC})
    void testProcessWithExpectedTimeout() {
        Assertions.assertThrows(TimeoutException.class, () -> {
            String[] command = {"tail", "-f", getJavacPath()};
            ProcessInfo result = Process.start(command, 2);
            assertTrue(result.getOutput().length() > 5, "Process output is not collected.");
        });
    }

    @Test
    @Timeout(value = 3, unit = SECONDS)
    void startProcessWithWaitFalseShouldNotBlockTests() throws Exception {
        String[] command = {"tail", "-f", getJavacPath()};
        Process.start(command, 1, false);
    }

    @SuppressWarnings("SameParameterValue")
    private String getProcessName(String processName) {
        if (OS.getOSType() == OSType.WINDOWS) {
            return processName + ".exe";
        } else {
            return processName;
        }
    }

    private String getJavacPath() {
        return OS.getEnvironmentVariable("JAVA_HOME", "") + "/bin/javac";
    }
}
