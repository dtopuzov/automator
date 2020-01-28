package tests.e2e.web.pages.marketplace;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.support.FindBy;
import org.openset.automator.test.web.WebContext;
import org.openset.automator.test.web.WebPage;
import tests.e2e.web.components.Footer;
import tests.e2e.web.components.Header;

@SuppressWarnings("unused")
public class MarketplaceHomePage extends WebPage {

    private WebContext webContext;

    @FindBy(css = "header")
    public Header header;

    @FindBy(css = "footer")
    public Footer footer;

    public MarketplaceHomePage(WebContext webContext) {
        super(webContext);
        this.webContext = webContext;
    }

    @Step("Navigate to marketplace page")
    public MarketplaceHomePage navigateTo() {
        webContext.browser.navigateTo(webContext.settings.web.baseUrl + "marketplace");
        return this;
    }

    @Step("Click explore free apps")
    public MarketplaceSearchPage exploreFreeApps() {
        getDriver().findElement(By.partialLinkText("Explore free apps")).click();
        return new MarketplaceSearchPage(webContext);
    }

    @Step("Click explore Actions")
    public MarketplaceSearchPage exploreActions() {
        getDriver().findElement(By.partialLinkText("Explore Actions")).click();
        return new MarketplaceSearchPage(webContext);
    }
}
