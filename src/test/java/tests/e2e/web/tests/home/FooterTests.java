package tests.e2e.web.tests.home;

import org.junit.jupiter.api.*;
import org.openqa.selenium.remote.BrowserType;
import org.openset.automator.test.web.WebTest;
import tests.e2e.web.components.Footer;
import tests.e2e.web.components.SignUpForm;
import tests.e2e.web.pages.HomePage;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Footer tests")
class FooterTests extends WebTest {
    private HomePage homePage = new HomePage(context);
    private SignUpForm signUpForm;
    private Footer footer;

    @BeforeEach
    void beforeFooterTests() {
        homePage.navigateTo();
        signUpForm = homePage.footerSignUpForm;
        footer = homePage.footer;

    }

    @Nested
    @DisplayName("Tests for signup form in footer")
    class FooterSignUpFormTests {
        @Test
        @Disabled("Not implemented because we don't want to polute GitHub with fake users")
        @DisplayName("Sign up with valid account via footer")
        void signUpWithValidAccountViaFooter() {
        }

        @Test
        @DisplayName("Sign up with wrong account via footer")
        void signUpWithWrongAccountViaFooter() {
            signUpForm.fillSignUpForm("dtopuzov", "dtopuzov@gmail.com", "fakePass1");
            boolean isUserErrorDisplayed = signUpForm.isErrorMessageDisplayed("Username dtopuzov is not available");
            boolean isEmailErrorDisplayed = signUpForm.isErrorMessageDisplayed("Email is invalid or already taken");
            assertAll("Error message for existing user and email should be displayed.",
                    () -> assertTrue(isUserErrorDisplayed, "Invalid username error not displayed."),
                    () -> assertTrue(isEmailErrorDisplayed, "Invalid email error not displayed.")
            );
        }

        @Test
        @DisplayName("Link to terms of service exists in footer signup form")
        void linkToTermsOfServiceExistsInFooterSignUpForm() {
            assertEquals("https://docs.github.com/terms", signUpForm.getLinkByText("terms of service"));
        }

        @Test
        @DisplayName("Link to privacy statment exists in footer signup form")
        void linkToPrivacyStatementExistsInFooterSignUpForm() {
            assertEquals("https://docs.github.com/privacy", signUpForm.getLinkByText("privacy statement"));
        }
    }

    @Nested
    @DisplayName("Tests for signup form in footer")
    class FooterLinksTests {
        @Test
        @DisplayName("Product links are correct")
        void productLinksAreCorrect() {
            assertEquals("https://github.com/features", footer.getLinkByText("Features"));
            assertEquals("https://github.com/security", footer.getLinkByText("Security"));
            assertEquals("https://github.com/enterprise", footer.getLinkByText("Enterprise"));
            assertEquals("https://github.com/customer-stories", footer.getLinkByText("Customer stories"));
            assertEquals("https://github.com/pricing", footer.getLinkByText("Pricing"));
            assertEquals("https://resources.github.com/", footer.getLinkByText("Resources"));
        }

        @Test
        @DisplayName("Platform links are correct")
        void platformLinksAreCorrect() {
            assertAll("Verify platform links",
                    () -> assertEquals("https://docs.github.com/", footer.getLinkByText("Developer API")),
                    () -> assertEquals("http://partner.github.com/", footer.getLinkByText("Partners")),
                    () -> assertEquals("https://atom.io/", footer.getLinkByText("Atom")),
                    () -> assertEquals("http://electronjs.org/", footer.getLinkByText("Electron")),
                    () -> assertEquals("https://desktop.github.com/", footer.getLinkByText("GitHub Desktop"))
            );
        }

        @Test
        @DisplayName("Support links are correct")
        void supportLinksAreCorrect() {
            assertAll("Verify support links",
                    () -> assertEquals("https://docs.github.com/", footer.getLinkByText("Help")),
                    () -> assertEquals("https://github.community/", footer.getLinkByText("Community Forum")),
                    () -> assertEquals("https://services.github.com/", footer.getLinkByText("Professional Services")),
                    () -> assertEquals("https://lab.github.com/", footer.getLinkByText("Learning Lab")),
                    () -> assertEquals("https://githubstatus.com/", footer.getLinkByText("Status")),
                    () -> assertEquals("https://github.com/contact", footer.getLinkByText("Contact GitHub"))
            );
        }

        @Test
        @DisplayName("Company links are correct")
        void companyLinksAreCorrect() {
            assertAll("Verify company's links",
                    () -> assertEquals("https://github.com/about", footer.getLinkByText("About")),
                    () -> assertEquals("https://github.blog/", footer.getLinkByText("Blog")),
                    () -> assertEquals("https://github.com/about/careers", footer.getLinkByText("Careers")),
                    () -> assertEquals("https://github.com/about/press", footer.getLinkByText("Press")),
                    () -> assertEquals("https://shop.github.com/", footer.getLinkByText("Shop"))
            );
        }

        @Test
        @DisplayName("Footer links are correct")
        void footerLinksAreCorrect() {
            if (context.settings.web.browserType.equals(BrowserType.SAFARI)) {
                // TODO: Need to scroll down.
                // Safari do not find elements outside viewport.
            } else {
                assertAll("Verify footer links",
                        () -> assertEquals("https://docs.github.com/terms", footer.getLinkByText("Terms")),
                        () -> assertEquals("https://docs.github.com/privacy", footer.getLinkByText("Privacy")),
                        () -> assertEquals("https://twitter.com/github", footer.getLinkByTitle("GitHub on Twitter")),
                        () -> assertEquals("https://www.facebook.com/GitHub", footer.getLinkByTitle("GitHub on Facebook")),
                        () -> assertEquals("https://www.youtube.com/github", footer.getLinkByTitle("GitHub on YouTube")),
                        () -> assertEquals("https://www.linkedin.com/company/github", footer.getLinkByTitle("GitHub on Linkedin")),
                        () -> assertEquals("https://github.com/github", footer.getLinkByTitle("organization"))
                );
            }
        }
    }
}
