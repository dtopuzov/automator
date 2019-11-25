package web.marketplace.tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import web.base.WebTest;
import web.marketplace.enums.Tab;
import web.marketplace.pages.HomePage;

class UrlParametersTests extends WebTest {

    private static HomePage home;

    @BeforeAll
    static void beforeHomePageTests() {
        home = new HomePage(context);
    }

    @Test
    @DisplayName("should navigate to Home when parameter is missing")
    void shouldNavigateToHomeWhenParameterIsMissing() {
        // Move to Samples tab, just to make sure missing param with return to Plugins (default tab).
        String url = context.settings.baseUrl + "?tab=samples&framework=angular&category=all_samples";
        context.browser.navigateTo(url);
        Assertions.assertEquals(home.getCurrentTab(), Tab.SAMPLES);
        Assertions.assertEquals(context.browser.getUrl(), url);

        // Open urls with missing param.
        url = context.settings.baseUrl + "?tab=samples&category=layouts_and_pages";
        context.browser.navigateTo(url);
        Assertions.assertEquals(home.getCurrentTab(), Tab.PLUGINS);
        Assertions.assertEquals(context.browser.getUrl(), context.settings.baseUrl);
    }

    @Test
    @DisplayName("should navigate to Home when parameter is wrong")
    void shouldNavigateToHomeWhenParameterIsWrong() {
        // Move to Samples tab, just to make sure missing param with return to Plugins (default tab).
        String url = context.settings.baseUrl + "?tab=samples&framework=angular&category=all_samples";
        context.browser.navigateTo(url);
        Assertions.assertEquals(home.getCurrentTab(), Tab.SAMPLES);
        Assertions.assertEquals(context.browser.getUrl(), url);

        // Open urls with wrong param.
        url = context.settings.baseUrl + "?tab=samples&category=wrong";
        context.browser.navigateTo(url);
        Assertions.assertEquals(home.getCurrentTab(), Tab.PLUGINS);
        Assertions.assertEquals(context.browser.getUrl(), context.settings.baseUrl);
    }
}
