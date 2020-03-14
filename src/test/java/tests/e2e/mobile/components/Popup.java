package tests.e2e.mobile.components;

import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openset.automator.test.mobile.MobileContext;
import org.openset.automator.test.mobile.MobilePage;

@SuppressWarnings("unused")
public class Popup extends MobilePage {

    @AndroidFindBy(id = "android:id/alertTitle")
    private MobileElement title;

    @AndroidFindBy(id = "android:id/message")
    private MobileElement message;

    @AndroidFindBy(id = "android:id/button1")
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

    public void close() {
        tryAgainButton.click();
    }
}
