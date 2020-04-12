package tests.e2e.web.pages.search;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openset.automator.test.web.WebContext;
import org.openset.automator.test.web.WebPage;
import tests.e2e.web.components.Header;

@SuppressWarnings("unused")
public class SimpleSearchPage extends WebPage {
    private WebContext webContext;

    @FindBy(css = "header")
    private Header header;

    @FindBy(name = "q")
    private WebElement searchBox;

    private By cheatSheetDialogLocator = By.tagName("details-dialog");
    private By closeDialogButtonLocator = By.xpath("//details-dialog//*[@aria-label='Close dialog']");

    public SimpleSearchPage(WebContext webContext) {
        super(webContext);
        this.webContext = webContext;
    }

    @Step("Navigate to /search page")
    public SimpleSearchPage navigateTo() {
        webContext.browser.navigateTo(webContext.settings.web.baseUrl + "search");
        return this;
    }

    @Step("Search for '{0}'")
    public SearchResultsPage searchFor(String text) {
        searchBox.clear();
        searchBox.sendKeys(text + Keys.ENTER);
        return new SearchResultsPage(webContext);
    }

    @Step("Click prefixes link")
    public void clickPrefixesLink() {
        By locator = By.cssSelector("#js-pjax-container details > summary");
        getWait()
                .until(ExpectedConditions.elementToBeClickable(locator))
                .click();
    }

    @Step("Search cheat sheet form is displayed")
    public void searchCheatSheetFormDisplayed() {
        getWait().until(ExpectedConditions.visibilityOfElementLocated(cheatSheetDialogLocator));
    }

    @Step("Close search cheat sheet form")
    public void closeSearchCheatSheetForm() {
        getDriver().findElement(closeDialogButtonLocator).click();
        getWait().until(ExpectedConditions.invisibilityOfElementLocated(cheatSheetDialogLocator));
    }
}
