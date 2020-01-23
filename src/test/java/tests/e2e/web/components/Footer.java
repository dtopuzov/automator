package tests.e2e.web.components;

import io.appium.java_client.pagefactory.Widget;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

@SuppressWarnings("unused")
public class Footer extends Widget {

    protected Footer(WebElement element) {
        super(element);
    }

    public String getLinkByText(String text) {
        WebElement link = this.getWrappedDriver().findElement(By.partialLinkText(text));
        return link.getAttribute("href");
    }

    public String getLinkByTitle(String title) {
        WebElement link = this.getWrappedDriver().findElement(By.xpath("//a[contains(@title,'" + title + "')]"));
        return link.getAttribute("href");
    }
}
