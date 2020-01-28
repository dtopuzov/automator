package tests.e2e.web.pages.marketplace;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openset.automator.test.web.WebContext;
import org.openset.automator.test.web.WebPage;
import tests.e2e.web.components.Header;

import java.util.List;

@SuppressWarnings("unused")
public class MarketplaceSearchPage extends WebPage {

    @FindBy(css = "header")
    public Header header;

    @FindBy(name = "query")
    public WebElement searchInput;


    public MarketplaceSearchPage(WebContext webContext) {
        super(webContext);
    }

    public MarketplaceSearchPage(WebContext webContext, String url) {
        super(webContext);
        webContext.browser.navigateTo(webContext.settings.web.baseUrl + url);
    }

    @Step("Search for {0}")
    public MarketplaceSearchPage searchFor(String text) {
        searchInput.clear();
        searchInput.sendKeys(text);
        searchInput.sendKeys(Keys.ENTER);
        return this;
    }

    public List<WebElement> getResults() {
        return getDriver().findElements(By.xpath("//a[@data-hydro-click and contains(@href,'/marketplace/')]"));
    }
}
