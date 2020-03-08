package tests.e2e.mobile.tests;

import org.junit.jupiter.api.*;
import org.openset.automator.test.mobile.MobileTest;
import tests.e2e.mobile.pages.HomePage;
import tests.e2e.mobile.pages.LoginPage;

public class LoginAndSignUpTests extends MobileTest {
    private HomePage homePage = new HomePage(context);
    private LoginPage loginPage;

    @BeforeEach
    void beforeLoginTests() {
        homePage.footer.navigateToLogin();
        loginPage = new LoginPage(context);
    }

    @Nested
    @DisplayName("Tests for login")
    class LoginTests {
        @BeforeEach
        void beforeSignUpTests() {
            loginPage.navigateToLogin();
        }

        @Test
        @Disabled("Not implemented because we don't know valid credentials.")
        @DisplayName("Login with valid account")
        void loginWithValidAccount() {
        }

        @Test
        @DisplayName("Login with invalid account")
        void loginWithInvalidAccount() {
        }
    }

    @Nested
    @DisplayName("Tests for signup form in footer")
    class SignUpTests {
        @BeforeEach
        void beforeSignUpTests() {
            loginPage.navigateSignUp();
        }

        @Test
        @Disabled("Not implemented because we don't know valid credentials.")
        @DisplayName("SignUp with valid account")
        void signUpWithValidAccount() {
        }

        @Test
        @DisplayName("SignUp with invalid account")
        void signUpWithInvalidAccount() {
        }
    }
}
