package org.openset.automator.test.mobile;

import com.google.common.collect.ImmutableList;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.qameta.allure.Step;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Pause;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openset.automator.app.mobile.Direction;
import org.openset.automator.image.Image;
import org.openset.automator.image.ImageComparisonResult;

import javax.annotation.Nullable;
import java.awt.image.BufferedImage;
import java.io.File;
import java.time.Duration;
import java.util.Set;

import static java.util.concurrent.TimeUnit.SECONDS;
import static org.awaitility.Awaitility.await;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Base mobile abstraction.
 */
@SuppressWarnings("unused")
public abstract class MobilePage {

    private AppiumDriver<?> driver;
    private MobileContext mobileContext;

    /**
     * Init base mobile page.
     *
     * @param mobileContext instance of WebContext.
     */
    public MobilePage(MobileContext mobileContext) {
        this.mobileContext = mobileContext;
        this.driver = mobileContext.app.getDriver();
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    /**
     * Get page title.
     *
     * @return page title as String.
     */
    public AppiumDriver<?> getDriver() {
        return driver;
    }

    /**
     * Get WebDriverWait with duration.
     *
     * @param duration duration.
     * @return WebDriverWait object.
     */
    public WebDriverWait getWait(Duration duration) {
        return new WebDriverWait(driver, duration.getSeconds());
    }

    /**
     * Get WebDriverWait with default wait timeout (from settings).
     *
     * @return WebDriverWait object.
     */
    public WebDriverWait getWait() {
        return getWait(Duration.ofSeconds(mobileContext.settings.base.defaultWait));
    }

    @Nullable
    private String getWebContext(AppiumDriver<?> driver) {
        await()
                .atMost(mobileContext.settings.base.defaultWait, SECONDS)
                .until(() -> driver.getContextHandles().size() > 1);

        Set<String> handles = driver.getContextHandles();
        for (Object context : handles) {
            if (!String.valueOf(context).equals("NATIVE_APP")) {
                return String.valueOf(context);
            }
        }
        return null;
    }

    public void switchToWebContext() {
        String webContext = this.getWebContext(driver);
        if (webContext != null) {
            driver.context(webContext);
        } else {
            throw new RuntimeException("Failed to switch to WebView context.");
        }
    }

    public void switchToNativeContext() {
        driver.context("NATIVE_APP");
    }

    public MobileElement findElement(By locator, Duration timeout) {
        try {
            driver.manage().timeouts().implicitlyWait(0, SECONDS);
            return (MobileElement) getWait(timeout).until(ExpectedConditions.visibilityOfElementLocated(locator));
        } catch (Exception e) {
            return null;
        } finally {
            driver.manage().timeouts().implicitlyWait(mobileContext.settings.base.defaultWait, SECONDS);
        }
    }

    public MobileElement findElement(By locator) {
        return findElement(locator, Duration.ofSeconds(mobileContext.settings.base.defaultWait));
    }

    public MobileElement findByText(String text, Duration timeout, boolean exactMatch) {
        By locator;
        if (mobileContext.settings.mobile.platform == Platform.ANDROID) {
            if (exactMatch) {
                locator = MobileBy.AndroidUIAutomator("new UiSelector().text(\"" + text + "\")");
            } else {
                locator = MobileBy.AndroidUIAutomator("new UiSelector().textContains(\"" + text + "\")");
            }
        } else if (mobileContext.settings.mobile.platform == Platform.IOS) {
            if (exactMatch) {
                String exactPredicate = "name == \"" + text + "\" OR label == \"" + text + "\"";
                locator = MobileBy.iOSNsPredicateString(exactPredicate);
            } else {
                String containsPredicate = "name contains '" + text + "' OR label contains '" + text + "'";
                locator = MobileBy.iOSNsPredicateString(containsPredicate);
            }
        } else {
            throw new RuntimeException("Unsupported mobile platform: " + mobileContext.settings.mobile.platform);
        }
        return findElement(locator, timeout);
    }

    public MobileElement findByText(String text, Duration timeout) {
        return findByText(text, timeout, true);
    }

    public MobileElement findByText(String text) {
        return findByText(text, Duration.ofSeconds(mobileContext.settings.base.defaultWait), true);
    }

    public void swipe(Point start, Point end, Duration duration) {
        // Resources: https://appiumpro.com/editions/107
        PointerInput input = new PointerInput(PointerInput.Kind.TOUCH, "finger1");
        Sequence swipe = new Sequence(input, 0);
        swipe.addAction(input.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), start.x, start.y));
        swipe.addAction(input.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
        if (mobileContext.settings.mobile.platform == Platform.ANDROID) {
            duration = duration.dividedBy(3);
        } else {
            swipe.addAction(new Pause(input, duration));
            duration = Duration.ZERO;
        }
        swipe.addAction(input.createPointerMove(duration, PointerInput.Origin.viewport(), end.x, end.y));
        swipe.addAction(input.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
        driver.perform(ImmutableList.of(swipe));
    }

    public void swipe(Direction direction, double distance, Duration duration) {
        if (distance < 0 || distance > 1) {
            throw new RuntimeException("Scroll distance must be between 0 and 1.");
        }

        Dimension size = mobileContext.app.getWindowSize();
        Point midPoint = new Point((int) (size.width * 0.5), (int) (size.height * 0.5));
        int top = midPoint.y - (int) ((size.height * distance) * 0.5);
        int bottom = midPoint.y + (int) ((size.height * distance) * 0.5);
        int left = midPoint.x - (int) ((size.width * distance) * 0.5);
        int right = midPoint.x + (int) ((size.width * distance) * 0.5);
        if (direction == Direction.UP) {
            swipe(new Point(midPoint.x, top), new Point(midPoint.x, bottom), duration);
        } else if (direction == Direction.DOWN) {
            swipe(new Point(midPoint.x, bottom), new Point(midPoint.x, top), duration);
        } else if (direction == Direction.LEFT) {
            swipe(new Point(left, midPoint.y), new Point(right, midPoint.y), duration);
        } else {
            swipe(new Point(right, midPoint.y), new Point(left, midPoint.y), duration);
        }
    }

    public void swipe(Direction direction, double distance) {
        swipe(direction, distance, Duration.ofMillis(1000));
    }

    public void swipe(Direction direction) {
        swipe(direction, 0.8, Duration.ofMillis(1000));
    }

    @Step("Hide keyboard")
    public void hideKeyboard() {
        driver.hideKeyboard();
    }

    public void match(String image) {
        match(image, 0.01);
    }

    public void match(String image, double tolerance) {
        match(image, tolerance, mobileContext.settings.base.defaultWait);
    }

    @Step("Compare current screen with '{0}' image")
    public void match(String image, double tolerance, int timeout) {
        String deviceString = mobileContext.settings.mobile.deviceName.toLowerCase().replace(" ", "");
        if (mobileContext.settings.mobile.platform == Platform.IOS) {
            deviceString = deviceString + "_ios" + mobileContext.settings.mobile.platformVersion.intValue();
        }

        String expectedImagePath = mobileContext.settings.base.baseImagePath + File.separator + deviceString
                + File.separator + String.format("%s.png", image);

        if (new File(expectedImagePath).exists()) {
            ImageComparisonResult result;

            boolean match = false;
            long startTime = System.currentTimeMillis();
            do {
                result = compareScreen(expectedImagePath);
                if (result.diffPercent <= tolerance) {
                    match = true;
                }
            } while (!match && System.currentTimeMillis() - startTime < timeout * 1000);

            // Save actual and diff image
            if (result.diffPercent > tolerance) {
                Image.save(result.actualImage, expectedImagePath.replace(".png", "_actual.png"));
                Image.save(result.diffImage, expectedImagePath.replace(".png", "_diff.png"));
            }

            // Verify result
            assertTrue(result.diffPercent <= tolerance,
                    String.format("Current screen does not match '%s' image.", image));
        } else {
            BufferedImage actualImage = mobileContext.app.getScreenshot();
            Image.save(actualImage, expectedImagePath);
        }
    }

    private ImageComparisonResult compareScreen(String expectedImagePath) {
        BufferedImage actualImage = mobileContext.app.getScreenshot();
        Rectangle viewPort = mobileContext.app.getViewPortRectangle();
        BufferedImage expectedImage = Image.fromFile(expectedImagePath);

        // On new iOS devices (iPhone 11, iPhone X*) viewport rectangle is way smaller than actual size
        if (actualImage.getWidth() > viewPort.getWidth()) {
            viewPort.setWidth(actualImage.getWidth());
        }
        if (actualImage.getHeight() > viewPort.getHeight()) {
            viewPort.setHeight(actualImage.getHeight());
        }

        return Image.compare(actualImage, expectedImage, viewPort, 10);
    }
}
