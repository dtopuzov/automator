package tests.e2e.mobile.tests;

import io.appium.java_client.MobileElement;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openset.automator.app.mobile.Direction;
import org.openset.automator.test.mobile.MobileTest;
import tests.e2e.mobile.components.Footer;
import tests.e2e.mobile.pages.HomePage;
import tests.e2e.mobile.pages.SwipePage;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@DisplayName("Tests for Swipe page")
public class SwipeTests extends MobileTest {
    private SwipePage swipePage;

    @BeforeEach
    public void beforeEach() {
        HomePage homePage = new HomePage(context);
        homePage.footer.navigateTo(Footer.FooterItem.SWIPE);
        swipePage = new SwipePage(context);
    }

    @Test
    @DisplayName("Swipe page smoke test")
    void swipeDemoTest() {
        // Verify initial state
        MobileElement firstSlideText = swipePage.findByText("FULLY OPEN SOURCE");
        assertNotNull(firstSlideText, "Wrong initial state of Swipe sample.");

        // Swipe LEFT should not be possible
        swipePage.swipe(Direction.LEFT);
        assertNotNull(swipePage.findByText("FULLY OPEN SOURCE"), "Swipe LEFT shown not change slide.");

        // Swipe and verify second slide
        swipePage.swipe(Direction.RIGHT);
        assertNotNull(swipePage.findByText("CREAT COMMUNITY"), "Swipe RIGHT do not open next slide.");

        // Swipe and verify 3rd slide
        swipePage.swipe(Direction.RIGHT);
        assertNotNull(swipePage.findByText("JS.FOUNDATION"), "Swipe RIGHT do not open next slide.");

        // Swipe and verify 4th slide
        swipePage.swipe(Direction.RIGHT);
        assertNotNull(swipePage.findByText("SUPPORT VIDEOS"), "Swipe RIGHT do not open next slide.");

        // Swipe and verify 5th slide
        swipePage.swipe(Direction.RIGHT);
        assertNotNull(swipePage.findByText("EXTENDABLE"), "Swipe RIGHT do not open next slide.");

        // Swipe and verify 6th slide
        swipePage.swipe(Direction.RIGHT);
        assertNotNull(swipePage.findByText("COMPATIBLE"), "Swipe RIGHT do not open next slide.");

        // Swipe and verify no more slides (still same slide)
        swipePage.swipe(Direction.RIGHT);
        assertNotNull(swipePage.findByText("COMPATIBLE"), "Swipe RIGHT do not open next slide.");

        // Swipe left and verify previous slide is loaded
        swipePage.swipe(Direction.LEFT);
        assertNotNull(swipePage.findByText("EXTENDABLE"), "Swipe LEFT do not open previous slide.");
    }
}
