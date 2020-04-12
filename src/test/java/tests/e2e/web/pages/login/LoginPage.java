package tests.e2e.web.pages.login;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openset.automator.test.web.WebContext;
import org.openset.automator.test.web.WebPage;

import java.util.concurrent.TimeUnit;

import static org.awaitility.Awaitility.await;

@SuppressWarnings("unused")
public class LoginPage extends WebPage {

    private WebContext webContext;

    @FindBy(id = "login_field")
    private WebElement userNameInput;

    @FindBy(id = "password")
    private WebElement passwordInput;

    @FindBy(name = "commit")
    private WebElement signInButton;

    public LoginPage(WebContext webContext) {
        super(webContext);
        this.webContext = webContext;
    }

    @Step("Navigate to home page")
    public LoginPage navigateTo() {
        webContext.browser.navigateTo(webContext.settings.web.baseUrl + "login");
        return this;
    }

    @Step("Login with user='{0}' and password='{1}'")
    public void login(String userName, String password) {
        userNameInput.clear();
        userNameInput.sendKeys(userName);
        passwordInput.clear();
        passwordInput.sendKeys(password);
        signInButton.click();
    }

    @Step("Get value of error message")
    public String getValueOfErrorMessage() {
        By locator = By.id("js-flash-container");
        getWait().until(ExpectedConditions.visibilityOfElementLocated(locator));
        await()
                .atMost(10, TimeUnit.SECONDS)
                .until(() -> getDriver().findElement(locator).getText().trim().length() > 3);
        return getDriver().findElement(locator).getText().trim();
    }
}
