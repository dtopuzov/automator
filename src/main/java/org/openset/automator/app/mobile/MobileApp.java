package org.openset.automator.app.mobile;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openset.automator.app.App;
import org.openset.automator.appium.AppiumServer;
import org.openset.automator.settings.mobile.MobileSettings;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class MobileApp implements App {
    private AppiumServer server;
    private AppiumClient client;
    private AppiumDriver<?> driver;
    private MobileSettings settings;

    public MobileApp(MobileSettings settings) {
        this.settings = settings;
        server = new AppiumServer();
    }

    /**
     * Get app driver.
     *
     * @return instance of AppiumDriver.
     */
    public AppiumDriver<?> getDriver() {
        return driver;
    }

    public void start() {
        server.start();
        client = new AppiumClient(server.getUrl(), settings);
        client.start();
        driver = client.getDriver();
    }

    public void stop() {
        if (client != null) {
            client.stop();
        }

        if (server != null) {
            server.stop();
        }
    }

    /**
     * Get screenshot.
     *
     * @return BufferedImage object.
     * @throws IOException When fail to take screenshot.
     */
    public BufferedImage getScreenshot() throws IOException {
        File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        return ImageIO.read(screenshot);
    }
}
