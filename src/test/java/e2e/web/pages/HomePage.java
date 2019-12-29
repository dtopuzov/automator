package e2e.web.pages;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openset.automator.test.web.WebContext;
import org.openset.automator.test.web.WebPage;

public class HomePage extends WebPage {
    @FindBy(name = "q")
    private WebElement searchBox;

    private WebContext webContext;

    public HomePage(WebContext webContext) {
        super(webContext);
        this.webContext = webContext;
        webContext.browser.navigateTo(webContext.settings.web.baseUrl);
    }

    public ResultsPage searchFor(String text) {
        searchBox.clear();
        searchBox.sendKeys(text + Keys.ENTER);
        return new ResultsPage(webContext);
    }
}
