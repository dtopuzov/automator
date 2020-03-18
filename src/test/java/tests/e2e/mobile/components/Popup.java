package tests.e2e.mobile.components;

import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openset.automator.test.mobile.MobileContext;
import org.openset.automator.test.mobile.MobilePage;

@SuppressWarnings("unused")
public class Popup extends MobilePage {

    @AndroidFindBy(id = "android:id/alertTitle")
    @iOSXCUITFindBy(xpath = "//XCUIElementTypeAlert//XCUIElementTypeStaticText[1]")
    private MobileElement title;

    @AndroidFindBy(id = "android:id/message")
    @iOSXCUITFindBy(xpath = "//XCUIElementTypeAlert//XCUIElementTypeStaticText[2]")
    private MobileElement message;

    @AndroidFindBy(id = "android:id/button1")
    @iOSXCUITFindBy(xpath = "//XCUIElementTypeAlert//XCUIElementTypeButton")
    private MobileElement tryAgainButton;

    public Popup(MobileContext context) {
        super(context);
    }

    public String getTitle() {
        return title.getText();
    }

    public String getMessage() {
        return message.getText();
    }

    public void close(String buttonName) {
        getWait().until(ExpectedConditions.elementToBeClickable(tryAgainButton)).click();
    }
}
