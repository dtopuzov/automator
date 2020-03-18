package org.openset.automator.test.mobile;

import org.openset.automator.app.mobile.MobileApp;
import org.openset.automator.settings.mobile.MobileSettings;

public class MobileContextFactory {

    private MobileContextFactory() {
        // Do not allow to initialize this class from outside.
    }

    private static MobileContextFactory instance = new MobileContextFactory();

    public static MobileContextFactory getInstance() {
        return instance;
    }

    ThreadLocal<MobileContext> mobileContext = ThreadLocal.withInitial(() -> {
        MobileSettings settings = new MobileSettings();
        MobileContext context = new MobileContext(settings);
        context.settings = settings;
        context.app = new MobileApp(context.settings);
        return context;
    });

    public MobileContext getMobileContext() {
        return mobileContext.get();
    }
}
