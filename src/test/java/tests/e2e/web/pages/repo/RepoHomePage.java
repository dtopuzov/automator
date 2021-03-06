package tests.e2e.web.pages.repo;

import org.openqa.selenium.By;
import org.openset.automator.test.web.WebContext;
import org.openset.automator.test.web.WebPage;

public class RepoHomePage extends WebPage {

    public RepoHomePage(WebContext webContext, String organization, String repository) {
        super(webContext);
        webContext.browser.navigateTo(webContext.settings.web.baseUrl + organization + "/" + repository);
    }

    public boolean isTagVisible(String text) {
        By locator = By.xpath("//*[@title='Topic: " + text + "']");
        return isElementVisible(locator);
    }

    public boolean isTextVisibleInReadmeForm(String text) {
        return isElementVisible(By.id("user-content-" + text.toLowerCase().replace(" ", "-")));
    }
}
