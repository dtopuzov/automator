package tests.e2e.web.tests.home;

import tests.e2e.web.components.SignUpForm;
import tests.e2e.web.pages.HomePage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openset.automator.test.web.WebTest;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Sign up form tests")
public class SignUpFormTests extends WebTest {

    private HomePage homePage = new HomePage(context);
    private SignUpForm signUpForm;

    @BeforeEach
    void beforeSignUpFormTests() {
        homePage.navigateTo();
        signUpForm = homePage.signUpForm;
    }

    @Test
    @Disabled("Not implemented because we don't want to polute GitHub with fake users")
    @DisplayName("Sign up with valid account")
    void signUpWithValidAccount() {
    }

    @Test
    @DisplayName("Sign up with invalid password")
    void signUpWithInvalidPassword() {
        signUpForm.fillSignUpForm("fake", "fake@mail.com", "fake");
        boolean isErrorDisplayed = signUpForm.isErrorMessageDisplayed("Password is invalid.");
        assertTrue(isErrorDisplayed, "Invalid password error not displayed.");
    }

    @Test
    @DisplayName("Sign up with existing username")
    void signUpWithExistingUserNameAndEmail() {
        signUpForm.fillSignUpForm("dtopuzov", "dtopuzov@gmail.com", "fakePass1");
        boolean isUserErrorDisplayed = signUpForm.isErrorMessageDisplayed("Username dtopuzov is not available");
        boolean isEmailErrorDisplayed = signUpForm.isErrorMessageDisplayed("Email is invalid or already taken");
        assertAll("Error message for existing user and email should be displayed.",
                () -> assertTrue(isUserErrorDisplayed, "Invalid username error not displayed."),
                () -> assertTrue(isEmailErrorDisplayed, "Invalid email error not displayed.")
        );
    }

    @Test
    @DisplayName("Link to Terms of Service exists")
    void linkToTermsOfServiceExists() {
        assertEquals("https://help.github.com/terms", signUpForm.getLinkByText("Terms of Service"));
    }

    @Test
    @DisplayName("Link to Privacy Statement exists")
    void linkToPrivacyStatementExists() {
        assertEquals("https://help.github.com/privacy", signUpForm.getLinkByText("Privacy Statement"));
    }
}
