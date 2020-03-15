package tests.e2e.mobile.tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openset.automator.app.mobile.Direction;
import org.openset.automator.test.mobile.MobileTest;
import tests.e2e.mobile.components.Footer;
import tests.e2e.mobile.pages.HomePage;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

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
        assertNotNull(homePage.findByText("WEBDRIVER"), "WEBDRIVER should be visible on home page.");
        assertNull(homePage.findByText("Support", Duration.ofSeconds(1)),
                "Support text should not be visible before scroll.");

        // Scroll down to find "Support" text
        homePage.swipe(Direction.DOWN, 0.5);
        assertNotNull(homePage.findByText("Support"), "Support text not found after swipe.");

        // Scroll up to find "Support" text
        homePage.swipe(Direction.UP, 0.5);
        assertNull(homePage.findByText("Support", Duration.ofSeconds(3)), "Support text in initial state.");
    }
}
