package org.openset.automator.test.common.extensions;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestWatcher;
import org.openset.automator.log.Log;
import org.openset.automator.log.LogFactory;

import java.util.Optional;

public class Watcher implements TestWatcher {
    private static final Log LOGGER = LogFactory.getLogger(Watcher.class.getName());

    @Override
    public void testAborted(ExtensionContext extensionContext, Throwable throwable) {
        LOGGER.info(String.format("%s -> Aborted.", extensionContext.getDisplayName()));
    }

    @Override
    public void testDisabled(ExtensionContext extensionContext, Optional<String> optional) {
        LOGGER.info(String.format("%s -> Disabled.", extensionContext.getDisplayName()));
    }

    @Override
    public void testFailed(ExtensionContext extensionContext, Throwable throwable) {
        LOGGER.info(String.format("%s -> Failed.", extensionContext.getDisplayName()));
    }

    @Override
    public void testSuccessful(ExtensionContext extensionContext) {
        LOGGER.info(String.format("%s -> Passed.", extensionContext.getDisplayName()));
    }
}
