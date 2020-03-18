package tests.e2e.mobile.tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openset.automator.test.mobile.MobileTest;
import tests.e2e.mobile.components.Footer;
import tests.e2e.mobile.pages.HomePage;
import tests.e2e.mobile.pages.LoginPage;

@DisplayName("Tests for Login form")
public class LoginTests extends MobileTest {
    private LoginPage loginPage;

    @BeforeEach
    void beforeLoginTests() {
        HomePage homePage = new HomePage(context);
        homePage.footer.navigateTo(Footer.FooterItem.LOGIN);
        loginPage = new LoginPage(context);
        loginPage.navigateToLogin();
    }

    @Test
    @DisplayName("Login with valid account")
    void loginWithValidAccount() {
        loginPage.login("dtopuzov@gmail.com", "password");
        loginPage.verifySuccessfulLogin();
    }

    @Test
    @DisplayName("Login with invalid email")
    void loginWithInvalidAccount() {
        loginPage.login("dtopuzovgmail.com", "password");
        loginPage.verifyErrorMessage("Please enter a valid email address");
    }

    @Test
    @DisplayName("Login with short password")
    void loginWithShortPassword() {
        loginPage.login("dtopuzov@gmail.com", "pass");
        loginPage.verifyErrorMessage("Please enter at least 8 characters");
    }
}
