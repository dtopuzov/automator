package tests.e2e.mobile.pages;

import org.openset.automator.test.mobile.MobileContext;
import org.openset.automator.test.mobile.MobilePage;
import tests.e2e.mobile.components.Footer;

public class BasePage extends MobilePage {
    public Footer footer;

    public BasePage(MobileContext mobileContext) {
        super(mobileContext);
        this.footer = new Footer(mobileContext);
    }
}
