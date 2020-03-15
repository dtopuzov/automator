package org.openset.automator.app.mobile;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Rectangle;
import org.openqa.selenium.TakesScreenshot;
import org.openset.automator.app.App;
import org.openset.automator.appium.AppiumServer;
import org.openset.automator.settings.mobile.MobileSettings;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Map;

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

    public void restart() {
        driver.resetApp();
    }

    public Dimension getWindowSize() {
        return getDriver().manage().window().getSize();
    }

    @SuppressWarnings("unchecked")
    public Rectangle getViewPortRectangle() {
        Map<String, Object> caps = driver.getSessionDetails();
        Map<String, Object> viewportRect = (Map<String, Object>) caps.get("viewportRect");
        int x = Integer.parseInt(viewportRect.get("left").toString());
        int y = Integer.parseInt(viewportRect.get("top").toString());
        int width = Integer.parseInt(viewportRect.get("width").toString()) + x;
        int height = Integer.parseInt(viewportRect.get("height").toString()) + y;
        return new Rectangle(x, y, height, width);
    }

    /**
     * Get screenshot.
     *
     * @return BufferedImage object.
     */
    public BufferedImage getScreenshot() {
        try {
            File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            return ImageIO.read(screenshot);
        } catch (IOException e) {
            throw new RuntimeException("Failed to take screenshot.", e);
        }
    }
}
