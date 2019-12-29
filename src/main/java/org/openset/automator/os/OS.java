package org.openset.automator.os;

import org.openset.automator.log.Log;
import org.openset.automator.log.LogFactory;
import org.openset.automator.settings.UnknownHostException;

/**
 * Operating Systems Utils.
 */
public class OS {
    private static final Log LOGGER = LogFactory.getLogger(OS.class.getName());

    /**
     * Get type of host operating system.
     *
     * @return OSType value.
     */
    public static OSType getOSType() {
        String osTypeString = System.getProperty("os.name", "generic").toLowerCase();
        if ((osTypeString.contains("mac")) || (osTypeString.contains("darwin"))) {
            return OSType.MAC;
        } else if (osTypeString.contains("win")) {
            return OSType.WINDOWS;
        } else if (osTypeString.contains("nux")) {
            return OSType.LINUX;
        } else {
            String e = "Unknown host OS.";
            LOGGER.fatal(e);
            throw new UnknownHostException(e);
        }
    }

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
