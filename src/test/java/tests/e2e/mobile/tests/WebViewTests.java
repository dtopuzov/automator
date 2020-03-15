package tests.e2e.mobile.tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openset.automator.test.mobile.MobileTest;
import tests.e2e.mobile.components.Footer;
import tests.e2e.mobile.pages.HomePage;
import tests.e2e.mobile.pages.WebViewPage;

@DisplayName("Tests for Home page")
public class WebViewTests extends MobileTest {
    private WebViewPage webViewPage;

    @BeforeEach
    public void beforeEachWebViewTests() {
        HomePage homePage = new HomePage(context);
        homePage.footer.navigateTo(Footer.FooterItem.WEBVIEW);
        webViewPage = new WebViewPage(context);
    }

    @Test
    @DisplayName("Smoke tests for WebView page")
    void smokeWebViewTest() {
        webViewPage.openTab(WebViewPage.MenuItem.GUIDE);
        webViewPage.verityTextVisible("Introduction");

        webViewPage.openTab(WebViewPage.MenuItem.API);
        webViewPage.verityTextVisible("Introduction");

        webViewPage.openTab(WebViewPage.MenuItem.HELP);
        webViewPage.verityTextVisible("Need help?");

        webViewPage.openTab(WebViewPage.MenuItem.BLOG);
        webViewPage.verityTextVisible("Recent Posts");
    }
}
