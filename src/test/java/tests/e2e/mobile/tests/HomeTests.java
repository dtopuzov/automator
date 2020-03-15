package tests.e2e.mobile.tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openset.automator.app.mobile.Direction;
import org.openset.automator.test.mobile.MobileTest;
import tests.e2e.mobile.components.Footer;
import tests.e2e.mobile.pages.HomePage;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@DisplayName("Tests for Home page")
public class HomeTests extends MobileTest {
    private HomePage homePage;

    @BeforeEach
    public void beforeEach() {
        homePage = new HomePage(context);
        homePage.footer.navigateTo(Footer.FooterItem.HOME);
    }

    @Test
    @DisplayName("Home page smoke test")
    void homePageTest() {
        // Verify initial state
        homePage.match("webdriverio_home");

        // Scroll down to find "Support" text (on some low resolution devices 'Support' is hidden until scroll).
        homePage.swipe(Direction.DOWN, 0.5);
        assertNotNull(homePage.findByText("Support"), "Support text not found after swipe.");
    }
}
