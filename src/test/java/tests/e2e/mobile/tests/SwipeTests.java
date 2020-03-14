package tests.e2e.mobile.tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openset.automator.test.mobile.MobileTest;
import tests.e2e.mobile.components.Footer;
import tests.e2e.mobile.pages.HomePage;
import tests.e2e.mobile.pages.SwipePage;

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
    void swipeHorizontal() {
    }
}
