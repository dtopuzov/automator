package tests.e2e.mobile.tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openset.automator.test.mobile.MobileTest;
import tests.e2e.mobile.components.Footer;
import tests.e2e.mobile.pages.HomePage;
import tests.e2e.mobile.pages.LoginPage;

@DisplayName("Tests for SignUp form")
public class SignUpTests extends MobileTest {
    private HomePage homePage = new HomePage(context);
    private LoginPage loginPage;

    @BeforeEach
    void beforeLoginTests() {
        context.app.restart();
        homePage.footer.navigateTo(Footer.FooterItem.LOGIN);
        loginPage = new LoginPage(context);
        loginPage.navigateSignUp();
    }

    @Test
    @DisplayName("SignUp with valid account")
    void signUpWithValidAccount() {
        loginPage.signUp("dtopuzov@gmail.com", "fakePassword", "fakePassword");
        loginPage.verifySuccessfullSignUp();
    }

    @Test
    @DisplayName("SignUp with invalid email adress")
    void signUpWithInvalidEmail() {
        loginPage.signUp("dtopuzovgmail.com", "fakePassword", "password123");
        loginPage.verifyErrorDialog();
        loginPage.verifyErrorMessage("Please enter a valid email address");
    }

    @Test
    @DisplayName("SignUp with not matching confirm password")
    void signUpWithNotMatchingPassword() {
        loginPage.signUp("dtopuzov@gmail.com", "fakePassword", "password123");
        loginPage.verifyErrorDialog();
        loginPage.verifyErrorMessage("Please enter the same password");
    }

    @Test
    @DisplayName("SignUp with short password")
    void signUpWithNotShortPassword() {
        loginPage.signUp("dtopuzov@gmail.com", "pass", "pass");
        loginPage.verifyErrorDialog();
        loginPage.verifyErrorMessage("Please enter at least 8 characters");
    }
}
