package utils;


import java.io.*;
import java.net.ServerSocket;
import java.util.concurrent.ThreadLocalRandom;

@SuppressWarnings("ResultOfMethodCallIgnored")
public class OSUtils {
    /**
     * Get value of environment variable.
     *
     * @param variable     environment variable name.
     * @param defaultValue default value (if variable is not set).
     * @return Value of environment variable (or default value).
     */
    public static String getEnvironmentVariable(String variable, String defaultValue) {
        String finalValue = defaultValue;
        String env = System.getenv(variable);
        if (env != null) {
            finalValue = env;
        }
        return finalValue;
    }

    /**
     * Create folder (if it do not exists).
     *
     * @param folderPath path to folder.
     */
    public static void createFolder(String folderPath) {
        File directory = new File(folderPath);
        if (!directory.exists()) {
            directory.mkdirs();
        }
    }

    /**
     * Write string in file.
     *
     * @param text     text to be written in file.
     * @param filePath path to file.
     * @throws FileNotFoundException when fail to find file.
     */
    public static void writeStringToFile(String text, String filePath) throws FileNotFoundException {
        try (PrintStream out = new PrintStream(new FileOutputStream(filePath))) {
            out.print(text);
        }
    }

    /**
     * Find free port in range.
     *
     * @param from lower range.
     * @param to   upper range.
     * @return free port.
     */
    public static int nextFreePort(int from, int to) {
        int port = ThreadLocalRandom.current().nextInt(from, to);
        while (true) {
            if (isLocalPortFree(port)) {
                return port;
            } else {
                port = ThreadLocalRandom.current().nextInt(from, to);
            }
        }
    }

    private static boolean isLocalPortFree(int port) {
        try {
            new ServerSocket(port).close();
            return true;
        } catch (IOException e) {
            return false;
        }
    }
}
