package org.openset.automator.test.common;

import org.junit.jupiter.api.extension.*;
import org.openset.automator.log.Log;
import org.openset.automator.log.LogFactory;

import java.util.Optional;

public class BaseTestExtension implements ParameterResolver, TestWatcher {
    private static final Log LOGGER = LogFactory.getLogger(BaseTestExtension.class.getName());

    @Override
    public boolean supportsParameter(ParameterContext parameterContext,
                                     ExtensionContext extensionContext) throws ParameterResolutionException {
        return parameterContext.getParameter().getType() == ExtensionContext.class;
    }

    @Override
    public Object resolveParameter(ParameterContext parameterContext,
                                   ExtensionContext extensionContext) throws ParameterResolutionException {
        return extensionContext;
    }

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
