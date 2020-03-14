package tests.e2e.mobile.components;

import io.appium.java_client.MobileBy;
import io.qameta.allure.Step;
import org.openset.automator.test.mobile.MobileContext;
import org.openset.automator.test.mobile.MobilePage;

@SuppressWarnings("unused")
public class Footer extends MobilePage {

    public enum FooterItem {
        HOME {
            public String toString() {
                return "Home";
            }
        },
        WEBVIEW {
            public String toString() {
                return "WebView";
            }
        },
        LOGIN {
            public String toString() {
                return "Login";
            }
        },
        FORMS {
            public String toString() {
                return "Forms";
            }
        },
        SWIPE {
            public String toString() {
                return "Swipe";
            }
        }
    }

    public Footer(MobileContext context) {
        super(context);
    }

    @Step("Navigate to {0}.")
    public void navigateTo(FooterItem footerItem) {
        getDriver().findElement(MobileBy.AccessibilityId(footerItem.toString())).click();
    }
}
