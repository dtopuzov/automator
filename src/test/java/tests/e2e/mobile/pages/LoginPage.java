package tests.e2e.mobile.pages;

import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.qameta.allure.Step;
import org.openset.automator.test.mobile.MobileContext;
import org.openset.automator.test.mobile.MobilePage;
import tests.e2e.mobile.components.Popup;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;

@SuppressWarnings("unused")
public class LoginPage extends MobilePage {

    @AndroidFindBy(accessibility = "button-login-container")
    private MobileElement loginButton;

    @AndroidFindBy(accessibility = "input-email")
    private MobileElement loginEmailInput;

    @AndroidFindBy(accessibility = "input-password")
    private MobileElement loginPassword;

    @AndroidFindBy(accessibility = "button-LOGIN")
    private MobileElement loginFormLoginButton;

    @AndroidFindBy(accessibility = "input-email")
    private AndroidElement signUpEmailInput;

    @AndroidFindBy(accessibility = "input-password")
    private MobileElement signUpPassword;

    @AndroidFindBy(accessibility = "input-repeat-password")
    private MobileElement signUpConfirmPassword;

    @AndroidFindBy(accessibility = "button-SIGN UP")
    private MobileElement signUpFormSignUpButton;

    private Popup popup;

    public LoginPage(MobileContext mobileContext) {
        super(mobileContext);
        this.popup = new Popup(mobileContext);
    }

    @Step("Open Login tab")
    public void navigateToLogin() {
        loginButton.click();
    }

    @Step("Open SignUp tab")
    public void navigateSignUp() {
        getDriver().findElement(MobileBy.AccessibilityId("button-sign-up-container")).click();
    }

    @Step("Login with '{0}' email and '{1}' password.")
    public void login(String email, String password) {
        loginEmailInput.clear();
        loginEmailInput.sendKeys(email);
        loginPassword.clear();
        loginPassword.sendKeys(password);
        loginFormLoginButton.click();
    }

    @Step("Sign up with '{0}' email, '{1}' password and confirm with '{2}'.")
    public void signUp(String email, String password, String confirmPassword) {
        signUpEmailInput.clear();
        signUpEmailInput.sendKeys(email);
        signUpPassword.clear();
        signUpPassword.sendKeys(password);
        signUpConfirmPassword.clear();
        signUpConfirmPassword.sendKeys(confirmPassword);
        signUpFormSignUpButton.click();
    }

    @Step("Verify successfull Login")
    public void verifySuccessfullLogin() {
        verifyDialog("Success", "You are logged in!");
    }

    @Step("Verify successfull SignUp")
    public void verifySuccessfullSignUp() {
        verifyDialog("Signed Up!", "You successfully signed up!");
    }

    @Step("Verify and handle error dialog")
    public void verifyErrorDialog() {
        verifyDialog("Failure", "Some fields are not valid!");
    }

    @Step("Verify '{0}' error massage displayed")
    public void verifyErrorMessage(String message) {
        assertNotNull(findByText(message, Duration.ofSeconds(10), true));
    }

    private void verifyDialog(String title, String message) {
        // Handle popup
        String actualTitle = popup.getTitle();
        String actualMessage = popup.getMessage();
        popup.close();

        // Assert popup and page messages
        assertAll(
                () -> assertEquals(title, actualTitle),
                () -> assertEquals(message, actualMessage)
        );
    }
}
