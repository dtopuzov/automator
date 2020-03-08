package tests.e2e.mobile.pages;

import org.openset.automator.test.mobile.MobileContext;
import org.openset.automator.test.mobile.MobilePage;

@SuppressWarnings("unused")
public class LoginPage extends MobilePage {
    public LoginPage(MobileContext mobileContext) {
        super(mobileContext);
    }

    public LoginPage navigateToLogin() {
        return this;
    }

    public LoginPage navigateSignUp() {
        return this;
    }
}
