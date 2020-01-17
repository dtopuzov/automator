package e2e.web.components;

import io.appium.java_client.pagefactory.Widget;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

@SuppressWarnings("unused")
public class SignUpForm extends Widget {
    @FindBy(name = "user[login]")
    private WebElement userInput;

    @FindBy(name = "user[email]")
    private WebElement emailInput;

    @FindBy(name = "user[password]")
    private WebElement passwordInput;

    @FindBy(xpath = "//button[contains(text(), 'Sign up for GitHub')]")
    private WebElement signUpButton;

    protected SignUpForm(WebElement element) {
        super(element);
    }

    @Step("Fill SignUp Form with user=<user>, email=<email>, password=<password>")
    public void fillSignUpForm(String user, String email, String password) {
        userInput.clear();
        userInput.sendKeys(user);
        emailInput.clear();
        emailInput.sendKeys(email);
        passwordInput.clear();
        passwordInput.sendKeys(password);
    }

    @Step("Click \"Sign up for GitHub\" button")
    public void clickSignUp() {
        signUpButton.click();
    }

    public void signUp(String user, String email, String password) {
        fillSignUpForm(user, email, password);
        clickSignUp();
    }

    public boolean isErrorMessageDisplayed(String message) {
        return true;
    }

    public String getLinkByText(String text) {
        WebElement link = this.getWrappedDriver().findElement(By.partialLinkText(text));
        return link.getAttribute("href");
    }
}
