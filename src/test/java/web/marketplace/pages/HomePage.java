package web.marketplace.pages;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import web.base.WebContext;
import web.base.WebPage;
import web.marketplace.data.ResultItem;
import web.marketplace.enums.Tab;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("unused")
public class HomePage extends WebPage {
    @FindBy(className = "mp-search-input")
    private WebElement searchBox;

    @FindBy(className = "mp-submit")
    private WebElement submitButton;

    private WebContext context;
    private WebDriver driver;

    public HomePage(WebContext webContext) {
        super(webContext);
        this.context = webContext;
        this.driver = this.context.browser.getDriver();
    }

    public void navigateTo() {
        this.context.browser.navigateTo(this.context.settings.baseUrl);
    }

    public Tab getCurrentTab() {
        WebElement tab = this.driver.findElement(By.cssSelector("app-tabs ul li.selected"));
        return Enum.valueOf(Tab.class, tab.getText().toUpperCase());
    }

    public void openTab(Tab tab) {
        this.driver.findElement(By.xpath("//li[contains(text(), '" + tab + "')]")).click();
    }

    public String getSearchBoxPlaceholderValue() {
        return this.searchBox.getAttribute("placeholder");
    }

    public void searchFor(String text) {
        this.getWait(Duration.ofSeconds(10)).until(ExpectedConditions.elementToBeClickable(searchBox));
        this.searchBox.clear();
        this.searchBox.sendKeys(text);
        this.searchBox.sendKeys(Keys.ENTER);
    }

    public String getSubmitButtonText() {
        return this.submitButton.getText().trim();
    }

    public List<ResultItem> getResults() {
        List<WebElement> elements = this.driver.findElements(By.className("mp-plugin-item"));
        List<ResultItem> results = new ArrayList<>();
        elements.forEach(item -> results.add(new ResultItem(item)));
        return results;
    }

    public void waitForResultByTitle(String title) {
        By locator = By.xpath("//*[@class='mp-plugin-info']/h3[contains(text(), '" + title + "')]");
        WebElement el = this.context.browser.find(locator);
        Assertions.assertTrue(el.isDisplayed());
    }
}
