package tests.e2e.web.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.support.FindBy;
import org.openset.automator.test.web.WebContext;
import org.openset.automator.test.web.WebPage;
import tests.e2e.web.components.Footer;
import tests.e2e.web.components.Header;
import tests.e2e.web.components.SignUpForm;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SuppressWarnings("unused")
public class HomePage extends WebPage {

    private WebContext webContext;

    @FindBy(css = "header")
    public Header header;

    @FindBy(css = "footer")
    public Footer footer;

    @FindBy(css = "div > div.mx-auto.col-sm-8.col-md-6.hide-sm")
    public SignUpForm signUpForm;

    @FindBy(xpath = "//form[@aria-label='Sign up']")
    public SignUpForm footerSignUpForm;

    public HomePage(WebContext webContext) {
        super(webContext);
        this.webContext = webContext;
    }

    @Step("Navigate to home page")
    public HomePage navigateTo() {
        webContext.browser.navigateTo(webContext.settings.web.baseUrl);
        By signUpFormLocator = By.cssSelector("div > div.mx-auto.col-sm-8.col-md-6.hide-sm");
        assertTrue(isElementVisible(signUpFormLocator), "Failed to find SignUp form on home page.");
        return this;
    }
}
