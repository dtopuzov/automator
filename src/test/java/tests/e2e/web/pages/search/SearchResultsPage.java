package tests.e2e.web.pages.search;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openset.automator.test.web.WebContext;
import org.openset.automator.test.web.WebPage;

import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.awaitility.Awaitility.given;

public class SearchResultsPage extends WebPage {

    public SearchResultsPage(WebContext webContext) {
        super(webContext);
    }

    /**
     * Get search results.
     *
     * @return List of WebElements.
     */
    public List<WebElement> getResults() {
        By locator = By.xpath("//ul[@class='repo-list']//li");

        given()
                .ignoreExceptions()
                .await()
                .atMost(10, TimeUnit.SECONDS)
                .until(() -> getDriver().findElements(locator).size() > 0);

        return getDriver().findElements(locator);
    }
}
