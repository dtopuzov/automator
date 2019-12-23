package org.openset.automator.os;

public class EnvUtils {
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
}
