package org.openset.automator.appium;

import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.service.local.flags.GeneralServerFlag;
import io.qameta.allure.Step;
import org.openset.automator.app.mobile.MobileApp;
import org.openset.automator.log.Log;
import org.openset.automator.log.LogFactory;

import java.net.URL;

/**
 * Appium server abstraction.
 */
public class AppiumServer {
    private static final Log LOGGER = LogFactory.getLogger(MobileApp.class.getName());
    private AppiumDriverLocalService service;

    /**
     * Init Appium server.
     */
    public AppiumServer() {
    }

    /**
     * Start Appium server.
     */
    @Step("Start Appium server.")
    public void start() {
        // Construct AppiumServiceBuilder
        AppiumServiceBuilder serviceBuilder = new AppiumServiceBuilder()
                .usingAnyFreePort()
                .withArgument(GeneralServerFlag.LOG_LEVEL, "warn");

        // Allow RELAXED_SECURITY for local execution
        serviceBuilder.withArgument(GeneralServerFlag.RELAXED_SECURITY);

        // Start Appium Server
        service = AppiumDriverLocalService.buildService(serviceBuilder);
        service.start();
        LOGGER.info("Start Appium server.");
    }

    /**
     * Get url of appium server.
     *
     * @return server URL.
     */
    public URL getUrl() {
        return service.getUrl();
    }

    /**
     * Stop appium server.
     */
    @Step("Stop Appium server.")
    public void stop() {
        if (service != null) {
            service.stop();
            LOGGER.info("Stop Appium server.");
        }
    }
}
