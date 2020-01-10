package e2e.web.tests;

import e2e.web.pages.ResultsPage;
import io.qameta.allure.Allure;
import io.qameta.allure.Issue;
import io.qameta.allure.Link;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openset.automator.test.web.WebTest;

import static io.qameta.allure.Allure.*;

@DisplayName("Image Search Feature")
@Link(name = "Getting Started", value = "GettingStarted.md", type="docs")
@Link(name = "Test Settings", value = "Settings.md", type="docs")
@Link(name = "Selenium Docs", value = "Selenium.md", type="docs")
public class ImageSearchTests extends WebTest {

    @Test
    @Issue("13")
    @DisplayName("Search for selenium images")
    void searchFromHomePage() {
        ResultsPage resultsPage = new ResultsPage(context, "selenium");
        resultsPage.lookForImages();
    }

    @Test
    @Issue("13")
    @DisplayName("Search for appium images")
    void searchFromResultsPage() {
        ResultsPage resultsPage = new ResultsPage(context, "appium");
        resultsPage.lookForImages();
    }
}
