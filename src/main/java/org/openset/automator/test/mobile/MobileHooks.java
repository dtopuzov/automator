package org.openset.automator.test.mobile;

import org.junit.jupiter.api.extension.BeforeAllCallback;
import org.junit.jupiter.api.extension.ExtensionContext;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.extension.ExtensionContext.Namespace.GLOBAL;

public class MobileHooks implements BeforeAllCallback, ExtensionContext.Store.CloseableResource {

    private static ThreadLocal<Boolean> systemReady = ThreadLocal.withInitial(() -> false);

    private static List<MobileContext> mobileContexts = new ArrayList<>();

    /**
     * Init MobileContext instance and start app under test.
     *
     * <p>Separate method with 'synchronized static' required for make sure procedure will be executed
     * only once across all simultaneously running threads
     */
    private static synchronized void systemSetup() {
        // 'if' is used to make sure procedure will be executed only once, not before every class
        Boolean isSystemReady = systemReady.get();
        if (isSystemReady != null) {
            if (!isSystemReady) {
                systemReady.set(true);
                MobileContext mobileContext = MobileContextFactory.getInstance().getMobileContext();
                mobileContext.app.start();
                mobileContexts.add(mobileContext);
            }
        }
    }

    /**
     * Initial setup of system.
     *
     * @param context junit context
     */
    @Override
    public void beforeAll(ExtensionContext context) {
        systemSetup();
        context.getRoot().getStore(GLOBAL).put(systemReady, this);
    }

    /**
     * Stop running mobile devices.
     *
     * <p>CloseableResource implementation, adding value into GLOBAL context is required to  registers a callback hook
     * With such steps close() method will be executed only once in the end of test execution
     */
    @Override
    public synchronized void close() {
        // clean data from system
        for (MobileContext mobileContext : mobileContexts) {
            if (mobileContext.app != null) {
                mobileContext.app.stop();
            }
        }
    }
}