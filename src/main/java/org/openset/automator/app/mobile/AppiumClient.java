package org.openset.automator.app.mobile;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.AutomationName;
import io.appium.java_client.remote.MobileCapabilityType;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.Platform;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openset.automator.settings.mobile.MobileSettings;

import java.net.URL;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

/**
 * Appium Client.
 */
public class AppiumClient {
    private MobileSettings settings;
    private URL serverUrl;
    private AppiumDriver<?> driver;

    /**
     * Init Appium Client.
     *
     * @param serverUrl Appium Server url.
     * @param settings  MobileSettings.
     */
    public AppiumClient(URL serverUrl, MobileSettings settings) {
        this.serverUrl = serverUrl;
        this.settings = settings;
    }

    /**
     * Start Appium Client.
     */
    public void start() {
        if (settings.mobile.platform == Platform.IOS) {
            driver = new IOSDriver<IOSElement>(serverUrl, getCapabilities());
        } else if (settings.mobile.platform == Platform.ANDROID) {
            driver = new AndroidDriver<AndroidElement>(serverUrl, getCapabilities());
        } else {
            driver = new AppiumDriver<MobileElement>(serverUrl, getCapabilities());
        }
        driver.manage().timeouts().implicitlyWait(settings.base.defaultWait, TimeUnit.SECONDS);
    }

    /**
     * Stop Appium Client.
     */
    public void stop() {
        if (driver != null) {
            driver.quit();
        }
    }

    /**
     * Get app driver.
     *
     * @return instance of AppiumDriver.
     */
    public AppiumDriver<?> getDriver() {
        return driver;
    }

    private DesiredCapabilities getCapabilities() {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, settings.mobile.platform.toString());
        capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, settings.mobile.platformVersion.toString());
        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, settings.mobile.deviceName);

        // Set application under test
        if (settings.mobile.appPath != null) {
            capabilities.setCapability(MobileCapabilityType.APP, settings.mobile.appPath);
        }
        if (settings.mobile.appPackage != null) {
            capabilities.setCapability(AndroidMobileCapabilityType.APP_PACKAGE, settings.mobile.appPackage);
        }
        if (settings.mobile.appActivity != null) {
            capabilities.setCapability(AndroidMobileCapabilityType.APP_ACTIVITY, settings.mobile.appActivity);
        }

        // Set command timeout (in debug mode timeout is huge to allow normal debugging)
        if (settings.base.isDebug) {
            capabilities.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, 3600);
        } else {
            capabilities.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, 60);
        }

        // Set Android specific settings.
        if (settings.mobile.platform == Platform.ANDROID) {
            capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, AutomationName.ANDROID_UIAUTOMATOR2);
            String avd = settings.mobile.avdName;
            if (avd != null) {
                capabilities.setCapability(AndroidMobileCapabilityType.AVD, avd);
                capabilities.setCapability(AndroidMobileCapabilityType.AVD_LAUNCH_TIMEOUT,
                        Duration.ofSeconds(120).toMillis());
                capabilities.setCapability(AndroidMobileCapabilityType.AVD_READY_TIMEOUT,
                        Duration.ofSeconds(120).toMillis());
                String avdArgs = settings.mobile.avdArgs;
                if (avdArgs != null) {
                    capabilities.setCapability(AndroidMobileCapabilityType.AVD_ARGS, avdArgs);
                }
            }
        }

        // Set iOS specific settings.
        if (settings.mobile.platform == Platform.IOS) {
            capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, AutomationName.IOS_XCUI_TEST);
            if (settings.mobile.includeSafariInWebViews) {
                capabilities.setCapability("includeSafariInWebviews", true);
            }
        }

        // Set device id.
        String udid = settings.mobile.deviceId;
        if (udid != null) {
            capabilities.setCapability(MobileCapabilityType.UDID, udid);
        }

        // Set web capabilities
        String browser = settings.mobile.browserType;
        if (browser != null) {
            capabilities.setCapability(MobileCapabilityType.BROWSER_NAME, browser);
        }

        // Set WebView options
        String chromeDriverVersion = settings.mobile.chromeDriverVersion;
        if (chromeDriverVersion != null) {
            WebDriverManager.chromedriver().driverVersion(chromeDriverVersion).setup();
            String path = WebDriverManager.chromedriver().driverVersion(chromeDriverVersion).getBinaryPath();
            capabilities.setCapability(AndroidMobileCapabilityType.CHROMEDRIVER_EXECUTABLE, path);
        }

        return capabilities;
    }
}
