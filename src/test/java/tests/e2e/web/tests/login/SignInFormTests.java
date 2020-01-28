package tests.e2e.web.tests.login;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openset.automator.test.web.WebTest;
import tests.e2e.web.pages.login.LoginPage;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Sign in form tests")
public class SignInFormTests extends WebTest {

    private LoginPage loginPage = new LoginPage(context);

    @BeforeEach
    void beforeSignInFormTests() {
        loginPage.navigateTo();
    }

    @Test
    @Disabled("Not implemented because I don't want to share my credentials.")
    @DisplayName("Sign in with valid account")
    void signInWithValidAccount() {
    }

    @Test
    @DisplayName("Sign up with invalid password")
    void signUpWithInvalidUserOrPassword() {
        loginPage.login("fakeUser", "fakePass");
        assertEquals("Incorrect username or password.", loginPage.getValueOfErrorMessage());
    }

    @Test
    @DisplayName("Link for forgotten password exists")
    void forgotPasswordLinkExists() {
        assertEquals("https://github.com/password_reset", loginPage.getLinkByText("Forgot password?"));
    }

    @Test
    @DisplayName("Link for create new user exists")
    void createNewAccountLinkExists() {
        assertEquals("https://github.com/join?source=login", loginPage.getLinkByText("Create an account"));
    }
}
