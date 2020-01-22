package e2e.web.pages.search;

import e2e.web.components.Header;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openset.automator.test.web.WebContext;
import org.openset.automator.test.web.WebPage;

import java.util.List;

public class SearchResultsPage extends WebPage {

    @FindBy(css = "header")
    public Header header;

    @FindBy(xpath = "//ul[@class='repo-list']/li")
    private List<WebElement> searchResults;

    public SearchResultsPage(WebContext webContext) {
        super(webContext);
    }

    public List<WebElement> getResults() {
        return searchResults;
    }
}
