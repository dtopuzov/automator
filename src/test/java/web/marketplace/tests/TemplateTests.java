package web.marketplace.tests;

import org.junit.jupiter.api.*;
import web.base.WebTest;
import web.marketplace.data.ResultItem;
import web.marketplace.enums.Flavour;
import web.marketplace.enums.Tab;
import web.marketplace.pages.DetailsPage;
import web.marketplace.pages.HomePage;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.awaitility.Awaitility.await;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class TemplateTests extends WebTest {

    private static HomePage home;
    private static DetailsPage details;

    private String template = "tns-template-drawer-navigation";

    @BeforeAll
    static void beforeHomePageTests() {
        home = new HomePage(context);
        home.navigateTo();
    }

    @Test
    @Order(0)
    @DisplayName("templates should be shown when templates tab is clicked")
    void templatesShouldBeShownWhenTemplatesTabIsClicked() {
        home.openTab(Tab.TEMPLATES);
        String actualUrl = context.browser.getUrl();
        String expectedUrl = context.settings.baseUrl + "?tab=templates";
        Assertions.assertEquals(actualUrl, expectedUrl, "Failed to navigate to templates tab.");
    }

    @Test
    @Order(1)
    @DisplayName("should have search input with placeholder")
    void shouldHaveSearchInputWithPlaceholder() {
        String actual = home.getSearchBoxPlaceholderValue();
        String expected = "Search for templates";
        Assertions.assertEquals(actual, expected, "Wrong placeholder text on Templates tab.");
    }

    @Test
    @Order(2)
    @DisplayName("verify submit template is present")
    void verifySubmitTemplateIsPresent() {
        String actual = home.getSubmitButtonText();
        String expected = "SUBMIT TEMPLATE";
        Assertions.assertEquals(actual, expected, "Wrong submit button text on Templates tab.");
    }

    @Test
    @Order(3)
    @DisplayName("should show results when type in the search input")
    void shouldShowResultsWhenTypeInTheSearchInput() {
        home.searchFor("drawer navigation");
        ResultItem result = home.getResults().get(0);
        Assertions.assertEquals(result.getTitle(), "Drawer Navigation");
        Assertions.assertEquals(result.getDescription(), "Side navigation template");
    }

    @Test
    @Order(4)
    @DisplayName("should navigate to details page and verify author and versions of item")
    void shouldNavigateToDetailsPageAndVerifyAuthorAndVersionsOfItem() {
        String pluginSearchTerm = "drawer navigation";
        String pluginDetailsUrl = context.settings.baseUrl + "plugins/tns-template-drawer-navigation-ng";

        // Refresh, search and wait for results
        context.browser.refresh();
        home.searchFor(pluginSearchTerm);
        await().atMost(10, TimeUnit.SECONDS).until(() -> home.getResults().size() == 1);

        // Get first result details
        ResultItem result = home.getResults().get(0);
        String team = result.getAuthor();
        String version = result.getVersion();
        String description = result.getDescription();

        // Click first result and verify details page loaded
        result.click();
        Assertions.assertEquals(context.browser.getUrl(), pluginDetailsUrl, "Failed to load plugin details page.");

        // Verify info on details page
        details = new DetailsPage(context);
        Assertions.assertEquals(details.getAuthor(), team, "Author on details page is not correct.");
        Assertions.assertEquals(details.getVersion(), version, "Version on details page is not correct.");
        Assertions.assertEquals(details.getDescription(), description, "Description on details page is not correct.");
    }

    @Test
    @Order(6)
    @DisplayName("should verify flavors tabs")
    void shouldVerifyFlavorsTabs() {
        List<Flavour> flavours = Arrays.asList(Flavour.JAVASCRIPT, Flavour.TYPESCRIPT, Flavour.ANGULAR, Flavour.VUE);
        flavours.forEach(flavour -> details.verifySupportForFlavor(flavour));
        Assertions.assertEquals(details.getSelectedFlavor(), Flavour.ANGULAR, "Default flavor is not Angular.");
    }

    @Test
    @Order(7)
    @DisplayName("should have CLI command for creating app from template")
    void shouldHaveCLICommandForCreatingAppFromTemplate() {
        String cmd = "tns create my-app-name --template tns-template-drawer-navigation" + Flavour.ANGULAR.getSuffix();
        Assertions.assertEquals(details.getCreateCommand(), cmd, "Wrong create command.");
    }

    @Test
    @Order(8)
    @DisplayName("should switch to Vue tab and have CLI command and repo")
    void shouldSwitchToVueTabAndHaveCLICommandAndRepo() {
        details.selectFlavour(Flavour.VUE);
        String cmd = "tns create my-app-name --template " + this.template + Flavour.VUE.getSuffix();
        Assertions.assertEquals(details.getCreateCommand(), cmd, "Wrong create command.");
    }

    @Test
    @Order(9)
    @DisplayName("should switch to TS tab and have CLI command and repo")
    void shouldSwitchToTSTabAndHaveCLICommandAndRepo() {
        details.selectFlavour(Flavour.TYPESCRIPT);
        String cmd = "tns create my-app-name --template " + this.template + Flavour.TYPESCRIPT.getSuffix();
        Assertions.assertEquals(details.getCreateCommand(), cmd, "Wrong create command.");
    }

    @Test
    @Order(10)
    @DisplayName("should switch to JS tab and have CLI command and repo")
    void shouldSwitchToJSTabAndHaveCLICommandAndRepo() {
        details.selectFlavour(Flavour.JAVASCRIPT);
        String cmd = "tns create my-app-name --template " + this.template + Flavour.JAVASCRIPT.getSuffix();
        Assertions.assertEquals(details.getCreateCommand(), cmd, "Wrong create command.");
    }

    @Test
    @Order(11)
    @DisplayName("should navigate to authors page when click on the author link")
    void shouldNavigateToAuthorsPageWhenClickOnTheAuthorLink() {
        /*
                it('should navigate to authors page when click on the author link', function () {
            var authorTitle = element(by.css('.mp-plugin-detail-main')).element(by.css('.mp-link'));
            var author;
            authorTitle.getText().then(function (text) {
                author = text;
            });
            authorTitle.click();
            browser.getCurrentUrl().then(function (browserUrl) {
                expect(browserUrl).to.contain(server + 'author/');
            })
            util.verifyHeader();
            element(by.css('.mp-author-detail-title')).getText().then(function (text) {
                expect(author).to.contain(text);
            });
        });
         */
    }
}
