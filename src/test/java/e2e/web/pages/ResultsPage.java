package e2e.web.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openset.automator.test.web.WebContext;
import org.openset.automator.test.web.WebPage;

import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SuppressWarnings("unused")
public class ResultsPage extends WebPage {

    private WebContext webContext;

    @FindBy(name = "q")
    private WebElement searchBox;

    @FindBy(xpath = "//*[@id='hdtb-msb-vis']/div[1]")
    private WebElement allButton;

    @FindBy(xpath = "//*[@id='hdtb-msb-vis']/div[2]")
    private WebElement imagesButton;

    public ResultsPage(WebContext webContext) {
        super(webContext);
        this.webContext = webContext;
    }

    public ResultsPage(WebContext webContext, String text) {
        super(webContext);
        this.webContext = webContext;
        String url = webContext.settings.web.baseUrl + "/search?q=" + text;
        webContext.browser.navigateTo(url);
    }

    @Step("'{0}' exists in results")
    public void verifyResultExists(String text) {
        WebElement link = getDriver().findElement(By.cssSelector("a[href*='" + text + "']"));
        assertTrue(link.isDisplayed(), String.format("%s not found in results.", text));
    }

    @Step("'{0}' does not exist in results")
    public void verifyResultDoesNotExist(String text) {
        getDriver().manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
        List<WebElement> links = getDriver().findElements(By.linkText(text));
        getDriver().manage().timeouts().implicitlyWait(webContext.settings.base.defaultWait, TimeUnit.SECONDS);
        assertEquals(0, links.size(), String.format("%s found in results.", text));
    }

    @Step("Search for '{0}'")
    public ResultsPage searchFor(String text) {
        searchBox.clear();
        searchBox.sendKeys(text + Keys.ENTER);
        return this;
    }

    @Step("Click all results link")
    public ResultsPage lookForAll() {
        allButton.click();
        return this;
    }

    @Step("Click image results link")
    public ResultsPage lookForImages() {
        imagesButton.click();
        return this;
    }
}
