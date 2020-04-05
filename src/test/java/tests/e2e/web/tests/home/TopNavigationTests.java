package tests.e2e.web.tests.home;

import tests.e2e.web.components.Header;
import tests.e2e.web.pages.HomePage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openset.automator.test.web.WebTest;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Top navigation bar tests")
class TopNavigationTests extends WebTest {

    private HomePage homePage = new HomePage(context);
    private Header header;

    @BeforeEach
    void beforeFooterTests() {
        homePage.navigateTo();
        header = homePage.header;
    }

    @Test
    @DisplayName("Link to home page exists")
    void linkToHomeExists() {
        assertTrue(header.getHomeLink().isDisplayed());
    }

    @Test
    @DisplayName("Why GitHub menu shows correct links")
    void whyGitHubMenuShowsCorrectLinks() {
        header.expandWhyGitHub();
        assertAll("Why GitHub? links are correct.",
                () -> assertEquals("https://github.com/features/code-review/", header.getLinkByText("Code review")),
                () -> assertEquals("https://github.com/features/project-management/", header.getLinkByText("Project management")),
                () -> assertEquals("https://github.com/features/integrations", header.getLinkByText("Integrations")),
                () -> assertEquals("https://github.com/features/actions", header.getLinkByText("Actions")),
                () -> assertEquals("https://github.com/features/packages", header.getLinkByText("Packages")),
                () -> assertEquals("https://github.com/features/security", header.getLinkByText("Security")),
                () -> assertEquals("https://github.com/features#team-management", header.getLinkByText("Team management")),
                () -> assertEquals("https://github.com/features#hosting", header.getLinkByText("Hosting"))
        );
    }

    @Test
    @DisplayName("Link to GitHub Enterprise exists")
    void linkToGitHubEnterpriseExists() {
        assertEquals("https://github.com/enterprise", header.getLinkByText("Enterprise"));
    }

    @Test
    @DisplayName("Explore menu shows correct links")
    void exploreMenuShowsCorrectLinks() {
        header.expandExplore();
        assertAll("Explore links are correct.",
                () -> assertEquals("https://github.com/topics", header.getLinkByText("Topics")),
                () -> assertEquals("https://github.com/collections", header.getLinkByText("Collections")),
                () -> assertEquals("https://github.com/trending", header.getLinkByText("Trending")),
                () -> assertEquals("https://lab.github.com/", header.getLinkByText("Learning Lab")),
                () -> assertEquals("https://opensource.guide/", header.getLinkByText("Open source guides")),
                () -> assertEquals("https://github.com/events", header.getLinkByText("Events")),
                () -> assertEquals("https://github.community/", header.getLinkByText("Community forum")),
                () -> assertEquals("https://education.github.com/", header.getLinkByText("GitHub Education"))
        );
    }

    @Test
    @DisplayName("Link to GitHub Marketplace exists")
    void linkToGitHubMarketplaceExists() {
        assertEquals("https://github.com/marketplace", header.getLinkByText("Marketplace"));
    }

    @Test
    @DisplayName("Pricing menu shows correct links")
    void pricingMenuShowsCorrectLinks() {
        header.expandPricing();
    }

    @Test
    @DisplayName("Search leads to search results page")
    void searchLeadsToSearchResultsPage() {
        header.searchFor("Appium");
        homePage.waitForUrl("search?q=Appium");
    }

    @Test
    @DisplayName("SignIn leads to sign in page")
    void signInLeadsToSignInPage() {
        header.signIn();
        String expectedUrl = context.settings.web.baseUrl + "login";
        String actualUrl = context.browser.getUrl();
        assertEquals(expectedUrl, actualUrl, "Sign In do not lead do Sign In page");
    }

    @Test
    @DisplayName("SignUp leads to sign up page")
    void signUpLeadsToSignUpPage() {
        header.signUp();
        String expectedUrl = context.settings.web.baseUrl + "join?source=header-home";
        String actualUrl = context.browser.getUrl();
        assertEquals(expectedUrl, actualUrl, "Sign Up do not lead do Sign Up page");
    }
}
