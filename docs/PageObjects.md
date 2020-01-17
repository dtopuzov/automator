# Page Object Pattern 

## About

[Page Object](https://selenium.dev/documentation/en/guidelines_and_recommendations/page_object_models/) model is an object design pattern in Selenium, where web pages are represented as classes, and the various elements on the page are defined as variables on the class. 

All possible user interactions can then be implemented as methods on the class.

## Selenium Sample

Page class with Selenium (without Automator framework):
```
public class HomePage {
    private WebDriver driver;

    @FindBy(name = "q")
    private WebElement searchBox;

    public HomePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void searchFor(String text) {
        searchBox.clear();
        searchBox.sendKeys(user);
        searchBox.sendKeys(Keys.ENTER);
    }
}
```
Usage in tests:
```
@DisplayName("Footer tests")
class SearchTests {
    
    private WebDriver driver;

    @BeforeEach
    public void beforeEach() {
       // some code to initialzie driver
    }

    @Test
    void testFooterFeature1() {
        HomePage homePage = new HomePage(driver);
        homePage.searchFor("Selenium");
        // Some asserts
    }
}
```

## Appium Sample

In mobile world we have both Android and iOS, for this purpose Appium allows creating single Page for both platforms:
```
public class LoginPage {

    @AndroidFindBy(id = "username")
    @iOSXCUITFindBy(id = "username")
    private MobileElement user;

    @AndroidFindBy(id = "password")
    @iOSXCUITFindBy(id = "password")
    private MobileElement pass;

    @AndroidFindBy(id = "loginBtn")
    @iOSXCUITFindBy(id = "loginBtn")
    private MobileElement login;

    private AppiumDriver driver;

    public LoginDemo(AppiumDriver driver) {
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }
    ...
}
```
Usage is similar to Selenium sample above.

## Page Objects in Automator Framework

Usually all samples of Page Object model on the internet use `WebDriver` instance to initialize page:
```
public class HomePage {
    private WebDriver driver;

    public HomePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
}
```

There are cases when `WebDriver` is not enough.

For example you may need to do different things on the page on Android and iOS (or different browser, or different test environment or something else).  

That is base WebPage in automator looks like:
```
public abstract class WebPage {

    private WebDriver driver;

    public WebPage(WebContext webContext) {
        this.driver = webContext.browser.getDriver();
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }
    ...
}
```
Respectively user pages looks like:
```
public class HomePage extends WebPage {

    private WebContext webContext;

    public HomePage(WebContext webContext) {
        super(webContext);
        this.webContext = webContext;
    }
    ...
}
```
In webContext we have both `WebSettings` to interact with settings and `Browser` to control the browser.


## Widgets Encapsulating Nested Elements

In some web sites or mobile apps there are forms with same content on different pages.

For example web site footer or some kind of navigation bar.

In this case you can extend appium-java `Widget` class like this:

```
public class FooterForm extends Widget {

    protected FooterForm(WebElement element) {
        super(element);
    }

    public void someMethod() {
        // Somethings specific that this widget can do.
    }
}
```

This `FooterForm` widget can be used in pages like this:
```
public class HomePage extends WebPage {

    private WebContext webContext;

    @FindBy(css = "body > footer")
    public FooterForm footer;

    public HomePage(WebContext webContext) {
        super(webContext);
        this.webContext = webContext;
    }

    public void navigateTo() {
        webContext.browser.navigateTo(webContext.settings.web.baseUrl);
    }
}
```

Then in tests we have:
```
@DisplayName("Footer tests")
class FooterTests extends WebTest {
    private HomePage homePage = new HomePage(context);

    @Test
    void testFooterFeature1() {
        homePage.navigateTo();
        homePage.footer.someMethod()
        // Some asserts
    }
}
```

Additional resources on encapsulating elements:
- Source of [Widget](https://github.com/appium/java-client/blob/master/src/main/java/io/appium/java_client/pagefactory/Widget.java) class.
- [Component encapsulating nested elements in Selenium POM](https://grasshopper.tech/178/)
- [Component objects instead of page objects in selenium](https://stackoverflow.com/questions/40536349/component-objects-instead-of-page-objects-in-selenium)
- [Page Object design extension question](https://github.com/appium/java-client/issues/320) issue contains some interesting discussions and details how `Widget` works.