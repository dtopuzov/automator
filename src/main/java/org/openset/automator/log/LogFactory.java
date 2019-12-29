package org.openset.automator.log;

/**
 * Log factory.
 * Used to generate Log instances.
 */
public class LogFactory {

    public static Log getLogger(String className) {
        return new Log(className);
    }
}
