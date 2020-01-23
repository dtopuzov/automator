package org.openset.automator.log;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Collections;

/**
 * Log object.
 */
public class Log {

    private Logger log;

    Log(String className) {
        this.log = LogManager.getLogger(className);
    }

    public void trace(String msg) {
        this.trace(msg);
    }

    public void debug(String msg) {
        this.log.debug(msg);
    }

    public void info(String msg) {
        this.log.info(msg);
    }

    public void warn(String msg) {
        this.log.warn(msg);
    }

    public void error(String msg) {
        this.log.error(msg);
    }

    public void fatal(String msg) {
        this.log.fatal(msg);
    }

    public void fatal(String msg, Exception e) throws Exception {
        this.fatal(msg);
        throw e;
    }

    public void separator() {
        this.separator("");
    }

    /**
     * Log some text separated from other lines with "=" symbols.
     *
     * @param title text.
     */
    public void separator(String title) {
        if (title.equalsIgnoreCase("")) {
            title = "===";
        } else {
            title = String.format(" %s ", title);
        }
        int titleLength = title.length();
        String sepEnding = String.join("", Collections.nCopies(40 - titleLength, "="));
        String msg = String.format("%1$s%2$s%3$s", "===", title, sepEnding);
        this.info(msg);
    }
}
