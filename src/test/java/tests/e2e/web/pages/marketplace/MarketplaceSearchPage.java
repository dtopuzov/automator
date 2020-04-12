package tests.e2e.web.pages.marketplace;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openset.automator.test.web.WebContext;
import org.openset.automator.test.web.WebPage;
import tests.e2e.web.components.Header;

import java.util.List;

@SuppressWarnings({"unused", "UnusedReturnValue"})
public class MarketplaceSearchPage extends WebPage {

    @FindBy(css = "header")
    public Header header;

    @FindBy(name = "query")
    public WebElement searchInput;


    public MarketplaceSearchPage(WebContext webContext) {
        super(webContext);
    }

    /**
     * Open search page for url.
     *
     * @param webContext instance of WebContext.
     * @param url        search url.
     */
    public MarketplaceSearchPage(WebContext webContext, String url) {
        super(webContext);
        String searchUrl = webContext.settings.web.baseUrl + url;
        webContext.browser.navigateTo(searchUrl);
        waitForUrl(searchUrl);
    }

    @Step("Search for {0}")
    public MarketplaceSearchPage searchFor(String text) {
        getWait().until(ExpectedConditions.elementToBeClickable(searchInput));
        searchInput.clear();
        searchInput.sendKeys(text);
        searchInput.sendKeys(Keys.ENTER);
        return this;
    }

    public List<WebElement> getResults() {
        return getDriver().findElements(By.xpath("//a[@data-hydro-click and contains(@href,'/marketplace/')]"));
    }
}
