package web.marketplace.pages;

import org.awaitility.core.ConditionTimeoutException;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import web.base.WebContext;
import web.base.WebPage;
import web.marketplace.enums.Flavour;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.awaitility.Awaitility.await;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SuppressWarnings({"unused", "MismatchedQueryAndUpdateOfCollection"})
public class DetailsPage extends WebPage {
    private WebContext context;
    private WebDriver driver;

    @FindBy(css = "p.mp-plugin-detail-sub-title a")
    private WebElement authorLink;

    @FindBy(className = "mp-plugin-detail-description")
    private WebElement description;

    @FindBy(className = "mp-plugin-detail-info-list")
    private WebElement detailsInfoPanel;

    @FindBy(className = "mp-search-input")
    private WebElement createCommandText;

    @FindBy(css = "div.mp-plugin-detail-flavor app-link")
    private List<WebElement> flavorLinks;

    public DetailsPage(WebContext webContext) {
        super(webContext);
        this.driver = webContext.browser.getDriver();
    }

    public String getCreateCommand() {
        return createCommandText.getAttribute("value");
    }

    public String getAuthor() {
        return authorLink.getText().trim();
    }

    public String getVersion() {
        return detailsInfoPanel.findElement(By.tagName("li")).getText().trim();
    }

    public String getDescription() {
        return description.getText().trim();
    }

    public void selectFlavour(Flavour flavour) {
        WebElement flavourLink = flavorLinks.stream()
                .filter(x -> flavour.toString().equals(x.getText())).findFirst().orElse(null);
        if (flavourLink != null) {
            flavourLink.click();
            try {
                await().atMost(10, TimeUnit.SECONDS).until(() -> this.getSelectedFlavor() == flavour);
            } catch (ConditionTimeoutException e) {
                Assertions.fail(String.format("%s flavor not selected.", flavour));
            }
        } else {
            Assertions.fail(String.format("Failed to find %s flavor.", flavour));
        }
    }

    public void verifySupportForFlavor(Flavour flavour) {
        List<Flavour> flavours = new ArrayList<>();
        flavorLinks.forEach(element -> flavours.add(Flavour.valueOf(element.getText().toUpperCase())));
        assertTrue(flavours.contains(flavour), String.format("%s flavor not supported.", flavour));
    }

    public Flavour getSelectedFlavor() {
        try {
            WebElement selected = this.driver.findElement(By.cssSelector("div.mp-plugin-detail-flavor span.selected"));
            return Flavour.valueOf(selected.getText().toUpperCase());
        } catch (NoSuchElementException e) {
            return null;
        }
    }
}
