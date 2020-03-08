package org.openset.automator.test.mobile;

import org.openset.automator.app.mobile.MobileApp;
import org.openset.automator.settings.mobile.MobileSettings;

public class MobileContext {
    public MobileSettings settings;
    public MobileApp app;

    public MobileContext(MobileSettings settings) {
        this.settings = settings;
    }
}
