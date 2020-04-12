package tests.e2e.web.pages.careers;

import io.qameta.allure.Step;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openset.automator.test.web.WebContext;
import org.openset.automator.test.web.WebPage;

import java.util.List;

public class CareersPage extends WebPage {
    private WebContext webContext;

    public CareersPage(WebContext webContext) {
        super(webContext);
        this.webContext = webContext;
    }

    @Step("Open careers page")
    public CareersPage navigateTo() {
        webContext.browser.navigateTo(webContext.settings.web.baseUrl + "about/careers");
        return this;
    }

    @Step("Expand {0} positions")
    public CareersPage expandPositions(String area) {
        By locator = By.xpath("//button[@aria-label='Expand and collapse']/h3[text() = '" + area + "']");
        getDriver().findElement(locator).click();
        return this;
    }

    @Step("Get positions in {0} area")
    public List<WebElement> getPositionsInArea(String area) {
        By openedListLocator = By.xpath("//div[contains(@class, 'js-details-container open')]");
        By areaButtonLocator = By.xpath("//button//*[text() = '" + area + "']");
        WebElement areaElement = getDriver().findElements(openedListLocator)
                .stream()
                .filter(element -> element.findElements(areaButtonLocator).size() > 0)
                .findFirst()
                .orElse(null);
        if (areaElement != null) {
            return areaElement.findElements(By.tagName("li"));
        } else {
            Assertions.fail(String.format("%s is not expanded or not present at all.", area));
            return null;
        }
    }
}
