package web.marketplace.data;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class ResultItem {
    private WebElement element;

    public ResultItem(WebElement element) {
        this.element = element;
    }

    public String getTitle() {
        return this.element.findElement(By.tagName("h3")).getText().trim();
    }

    public String getAuthor() {
        return this.element.findElement(By.xpath("//span[contains(text(),'by ')]")).getText().trim();
    }

    public String getVersion() {
        return this.element.findElement(By.xpath("//span[contains(text(),'Version')]")).getText().replace("|", "").trim();
    }

    public String getDescription() {
        return this.element.findElement(By.className("mp-plugin-description")).getText().trim();
    }

    public void click() {
        this.element.click();
    }
}
