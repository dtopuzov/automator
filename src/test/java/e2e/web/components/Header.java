package e2e.web.components;

import io.appium.java_client.pagefactory.Widget;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

@SuppressWarnings("unused")
public class Header extends Widget {
    @FindBy(xpath = "//a[@aria-label='Homepage']")
    private WebElement homeLink;

    @FindBy(xpath = "//summary[contains(text(), 'Why GitHub?')]")
    private WebElement whyGitHubButton;

    @FindBy(xpath = "//a[./text()='Enterprise')]")
    private WebElement enterpriseLink;

    @FindBy(xpath = "//*[contains(text(), 'Explore')]")
    private WebElement exploreButton;

    @FindBy(xpath = "//a[./text()='Marketplace')]")
    private WebElement marketLink;

    @FindBy(xpath = "//*[contains(text(), 'Pricing')]")
    private WebElement pricingButton;

    @FindBy(name = "q")
    private WebElement searchInput;

    protected Header(WebElement element) {
        super(element);
    }

    @Step("Search for <text>")
    public void searchFor(String text) {
        searchInput.clear();
        searchInput.sendKeys(text);
        searchInput.sendKeys(Keys.ENTER);
    }

    @Step
    public void signIn() {
        getWrappedDriver().findElement(By.partialLinkText("Sign in")).click();
    }

    @Step
    public void signUp() {
        getWrappedDriver().findElement(By.partialLinkText("Sign up")).click();
    }

    @Step
    public void expandWhyGitHub() {
        whyGitHubButton.click();
    }

    @Step
    public void expandExplore() {
        exploreButton.click();
    }

    @Step
    public void expandPricing() {
        pricingButton.click();
    }

    public String getLinkByText(String text) {
        WebElement link = this.getWrappedDriver().findElement(By.xpath("//a[contains(text(), '" + text + "')]"));
        return link.getAttribute("href");
    }
}
