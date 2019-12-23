package org.openset.automator.test.mobile;

import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.service.local.flags.GeneralServerFlag;
import org.openset.automator.settings.mobile.MobileSettings;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * Appium server abstraction.
 */
public class Server {
    private MobileSettings settings;
    private AppiumDriverLocalService service;

    /**
     * Init Appium server.
     *
     * @param mobileSettings instance of MobileSettings.
     */
    public Server(MobileSettings mobileSettings) {
        this.settings = mobileSettings;
    }

    /**
     * Start Appium server.
     */
    public void start() {
        if (isLocal()) {
            // Construct AppiumServiceBuilder
            AppiumServiceBuilder serviceBuilder = new AppiumServiceBuilder()
                    .usingAnyFreePort()
                    .withArgument(GeneralServerFlag.LOG_LEVEL, "error");

            // Allow RELAXED_SECURITY for local execution
            serviceBuilder.withArgument(GeneralServerFlag.RELAXED_SECURITY);

            // Start Appium Server
            service = AppiumDriverLocalService.buildService(serviceBuilder);
            service.start();
        }
    }


    /**
     * Get url of appium server.
     *
     * @return server URL.
     * @throws MalformedURLException when fail to construct URL.
     */
    public URL getUrl() throws MalformedURLException {
        if (isLocal()) {
            return service.getUrl();
        } else {
            return new URL(settings.sauce.url);
        }
    }

    /**
     * Stop appium server.
     */
    public void stop() {
        if (isLocal()) {
            if (service != null) {
                service.stop();
            }
        }
    }

    private boolean isLocal() {
        return settings.sauce.appiumVersion == null;
    }
}
