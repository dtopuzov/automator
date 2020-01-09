package e2e.web.tests;

import e2e.web.pages.ResultsPage;
import io.qameta.allure.Issue;
import io.qameta.allure.Link;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openset.automator.test.web.WebTest;

@DisplayName("Image Search Feature")
public class ImageSearchTests extends WebTest {
    @Test
    @Link("https://example.org")
    @DisplayName("Search for selenium images")
    void searchFromHomePage() {
        ResultsPage resultsPage = new ResultsPage(context, "selenium");
        resultsPage.lookForImages();
    }

    @Test
    @Issue("123")
    @DisplayName("Search for appium images")
    void searchFromResultsPage() {
        ResultsPage resultsPage = new ResultsPage(context, "appium");
        resultsPage.lookForImages();
    }
}
