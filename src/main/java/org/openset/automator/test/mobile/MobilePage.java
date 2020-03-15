package org.openset.automator.test.mobile;

import com.google.common.collect.ImmutableList;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Platform;
import org.openqa.selenium.Point;
import org.openqa.selenium.interactions.Pause;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openset.automator.app.mobile.Direction;

import javax.annotation.Nullable;
import java.time.Duration;
import java.util.Set;
import java.util.concurrent.TimeUnit;

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
            driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
            return (MobileElement) getWait(timeout).until(ExpectedConditions.visibilityOfElementLocated(locator));
        } catch (Exception e) {
            return null;
        }
        finally {
            driver.manage().timeouts().implicitlyWait(mobileContext.settings.base.defaultWait, TimeUnit.SECONDS);
        }
    }

    public MobileElement findElement(By locator) {
        return findElement(locator, Duration.ofSeconds(mobileContext.settings.base.defaultWait));
    }

    public MobileElement findByText(String text, Duration timeout, boolean exactMatch) {
        By locator;
        if (exactMatch) {
            locator = MobileBy.AndroidUIAutomator("new UiSelector().text(\"" + text + "\")");
        } else {
            locator = MobileBy.AndroidUIAutomator("new UiSelector().textContains(\"" + text + "\")");
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
}
