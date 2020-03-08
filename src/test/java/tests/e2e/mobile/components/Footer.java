package tests.e2e.mobile.components;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import org.openset.automator.test.mobile.MobileContext;
import org.openset.automator.test.mobile.MobilePage;

public class Footer extends MobilePage {

    private AppiumDriver driver;

    public Footer(MobileContext context) {
        super(context);
        this.driver = getDriver();
    }

    public void navigateToHome() {
        driver.findElement(MobileBy.AccessibilityId("Home")).click();
    }

    public void navigateToWebView() {
        driver.findElement(MobileBy.AccessibilityId("WebView")).click();
    }

    public void navigateToLogin() {
        driver.findElement(MobileBy.AccessibilityId("Login")).click();
    }

    public void navigateToForms() {
        driver.findElement(MobileBy.AccessibilityId("Forms")).click();
    }

    public void navigateToSwipe() {
        driver.findElement(MobileBy.AccessibilityId("Swipe")).click();
    }
}
