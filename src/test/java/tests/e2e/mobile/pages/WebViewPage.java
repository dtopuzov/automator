package tests.e2e.mobile.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openset.automator.test.mobile.MobileContext;
import org.openset.automator.test.mobile.MobilePage;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SuppressWarnings("unused")
public class WebViewPage extends MobilePage {

    public enum MenuItem {
        DOCS {
            public String toString() {
                return "Docs";
            }
        },
        API {
            public String toString() {
                return "API";
            }
        },
        HELP {
            public String toString() {
                return "Help";
            }
        },
        BLOG {
            public String toString() {
                return "Blog";
            }
        }
    }

    public WebViewPage(MobileContext mobileContext) {
        super(mobileContext);
    }

    @Step("Open '{0}' tab inside webview.")
    public void openTab(MenuItem tab) {
        try {
            switchToWebContext();
            getWait().until(ExpectedConditions.visibilityOfElementLocated(By.linkText(tab.toString()))).click();
        } finally {
            switchToNativeContext();
        }
    }

    @Step("Verify '{0}' text visible in webview")
    public void verityTextVisible(String text) {
        try {
            switchToWebContext();
            By textLocator = By.xpath("//*[text()='" + text + "']");
            assertNotNull(findElement(textLocator), String.format("Faled to find element with text '%s'.", text));
        } finally {
            switchToNativeContext();
        }
    }
}
