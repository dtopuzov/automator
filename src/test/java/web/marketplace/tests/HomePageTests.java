package web.marketplace.tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import web.base.WebTest;
import web.marketplace.pages.HomePage;

public class HomePageTests extends WebTest {

    private static HomePage home;

    @BeforeAll
    public static void beforeHomePageTests() {
        home = new HomePage(context);
        home.navigateTo();
    }

    @Test
    @DisplayName("should have a title NativeScript Marketplace")
    public void shouldHaveTitleNativeScriptMarketplace() {
        Assertions.assertEquals(this.home.getTitle(), "NativeScript Marketplace", "Wrong page title.");
    }

    @Test
    @DisplayName("should have link to authors page and nativescript.org")
    public void shouldHaveLinkToAuthorsPageAndNativescriptOrg() {
        Assertions.assertEquals(this.home.getTitle(), "NativeScript Marketplace", "Wrong page title.");
    }

    @Test
    @DisplayName("should have tabs with Plugins, Templates and Samples")
    public void shouldHavePluginsTemplatesAndSamplesTabs() {
        Assertions.assertEquals(this.home.getTitle(), "NativeScript Marketplace", "Wrong page title.");
    }

    @Test
    @DisplayName("submit plugin should be present")
    public void submitPluginShouldBePresent() {
        Assertions.assertEquals(this.home.getTitle(), "NativeScript Marketplace", "Wrong page title.");
        /*
                it('verify submit plugin is present', function () {
            browser.refresh();
            element(by.css('.mp-submit')).getText().then(function (text) {
                expect(text.trim()).to.equal('SUBMIT PLUGIN');
            });
        });
         */
    }

    @Test
    @DisplayName("should have search input with placeholder")
    public void shouldHaveSearchInputWithPlaceholder() {
        Assertions.assertEquals(this.home.getTitle(), "NativeScript Marketplace", "Wrong page title.");
        /*
                it('should have search input with placeholder', function () {
            element(by.css(".mp-search-input")).getAttribute('placeholder').then(function (text) {
                expect(text).to.equal('Search for plugins');
            });
        });
         */
    }

    @Test
    @DisplayName("should show all badges on home page")
    public void shouldShowAllBadgesOnHomePage() {
        /*
            it('should show all badges on home page', function () {
            var input = element(by.tagName('app-input')).element(by.tagName('input'));
            input.click();
            input.sendKeys('image picker');
            var firebasePlugin = element.all(by.css('.mp-plugin-info')).get(0);
            var firebaseBadges = firebasePlugin.element(by.tagName('app-plugin-badges')).all(by.css('.mp-badge'));
            firebaseBadges.count().then(function (count) {
                expect(count).to.equal(12);
            });
            firebaseBadges.get(0).getAttribute('title').then(function (text) {
                expect(text.trim()).to.equal('Has Android support');
            })
            firebaseBadges.get(0).getCssValue('opacity').then(function (value) {
                expect(value).to.equal('1');
            });
            firebaseBadges.get(1).getAttribute('title').then(function (text) {
                expect(text.trim()).to.equal('Has iOS support');
            });
            firebaseBadges.get(2).getAttribute('title').then(function (text) {
                expect(text.trim()).to.match(/Webpack/i);
            });
            firebaseBadges.get(3).getAttribute('title').then(function (text) {
                expect(text.trim()).to.equal('Has demos in plugin repository');
            });
            firebaseBadges.get(4).getAttribute('title').then(function (text) {
                expect(text.trim()).to.equal('Has license information');
            });
            firebaseBadges.get(5).getAttribute('title').then(function (text) {
                expect(text.trim()).to.equal('Minimum NativeScript version support');
            });
            firebaseBadges.get(6).getAttribute('title').then(function (text) {
                expect(text.trim()).to.equal('Current NativeScript version support');
            });
            firebaseBadges.get(7).getAttribute('title').then(function (text) {
                expect(text.trim()).to.equal('TypeScript typings available');
            });
            firebaseBadges.get(8).getAttribute('title').then(function (text) {
                expect(text.trim()).to.equal('Has Angular support');
            });
            firebaseBadges.get(9).getAttribute('title').then(function (text) {
                expect(text.trim()).to.equal('Has Vue support');
            });
            firebaseBadges.get(10).getAttribute('title').then(function (text) {
                expect(text.trim()).to.equal('Travis CI builds enabled');
            });
            firebaseBadges.get(11).getAttribute('title').then(function (text) {
                expect(text.trim()).to.equal('Popular plugin');
            });
        });
         */
    }

    @Test
    @DisplayName("should navigate to details page and verify author and versions of item")
    public void shouldNavigateToDetailsPageAndVerifyAuthorAndVersionsOfItem() {
        /*
         it('should navigate to details page and verify author and versions of item', function () {
            var pluginItem = element(by.css('.mp-plugin-item'));
            pluginItem.element(by.tagName('h3')).getText().then(function (text) {
                expect(text.trim()).to.equal('Pro UI');
            });
            var infoItems = pluginItem.element(by.css('.mp-plugin-info')).all(by.tagName('p')).first().all(by.tagName('span'));
            var author;
            var version;
            infoItems.first().getText().then(function (text) {
                author = text;
            });
            infoItems.last().getText().then(function (text) {
                version = text;
            });
            pluginItem.click();
            util.verifyUrl(server + 'plugins/nativescript-pro-ui');
            util.verifyHeader();

            element(by.css('.mp-plugin-detail-main')).element(by.css('.mp-deprecated')).getText().then(function (text) {
                expect(text).to.equal("DEPRECATED")
            });

            element(by.css('.mp-plugin-detail-main')).element(by.css('.mp-link')).getText().then(function (text) {
                expect(text.trim()).to.equal(author.trim())
            });
            element(by.css('.mp-plugin-detail-info-list')).all(by.tagName('li')).first().getText().then(function (text) {
                expect(version.trim()).to.contain(text.trim())
            });
        });
         */
    }

    @Test
    @DisplayName("should have sections with Recently verified and updated plugins")
    public void test1() {
        /*
                it('should have sections with Recently verified and updated plugins', function () {
            var verifiedList = element(by.css('.mp-plugins-sections')).all(by.css('.mp-plugins')).first().element(by.tagName('h2'));
            verifiedList.getText(function (text) {
                expect(text).to.equal('Recently Verified')
            });
            var updatedList = element(by.css('.mp-plugins-sections')).all(by.css('.mp-plugins')).last().element(by.tagName('h2'));
            updatedList.getText(function (text) {
                expect(text).to.equal('Recently Updated')
            });
        });
         */
    }

    @Test
    @DisplayName("should have section with all plugins")
    public void test2() {
        /*
            it('should have section with all plugins', function () {
            var allPlugins = element(by.css('.mp-plugins-list')).all(by.tagName('section')).last().element(by.tagName('h2'));
            allPlugins.getText().then(function (text) {
                expect(text).to.contain('All Plugins')
            });
        });
         */
    }

    @Test
    @DisplayName("should show plugins containing version and space")
    public void shouldShowPluginsContainingVersionAndSpace() {
        /*
                it('should show plugins containing \"version + interval\" string', function () {
            var input = element(by.tagName('app-input')).element(by.tagName('input'));
            input.getAttribute('placeholder').then(function (text) {
                expect(text).to.equal('Search for plugins');
            });
            input.click();
            input.sendKeys('version ');
            var pluginVersionNumber = element.all(by.css('.mp-plugin-item')).first();
            pluginVersionNumber.element(by.tagName('h3')).getText().then(function (text) {
                expect(text.trim()).to.equal('Version Number');
            });
            element(by.css('.mp-clear-icon')).click();
        })
         */
    }

    @Test
    @DisplayName("should show plugin with name:nativescript-imagepicker, when search by keyword")
    public void shouldShowPluginWithNamePrefix() {
        /*
            it('should show plugin with name:nativescript-imagepicker, when search by keyword', function () {
            var input = element(by.tagName('app-input')).element(by.tagName('input'));
            input.getAttribute('placeholder').then(function (text) {
                expect(text).to.equal('Search for plugins');
            });
            input.click();
            input.sendKeys('name:nativescript-imagepicker');
            var pluginVersionNumber = element(by.css('.mp-plugin-item'));
            pluginVersionNumber.element(by.tagName('h3')).getText().then(function (text) {
                expect(text.trim()).to.equal('Image Picker');
            });
            element(by.css('.mp-clear-icon')).click();
        })
         */
    }

    @Test
    @DisplayName("should show all badges on details page")
    public void shouldShowAllBadgesOnDetailsPage() {
        /*
                it('should show all badges on details page', function () {
            var firebasePlugin = element.all(by.css('.mp-plugin-info')).get(0);
            firebasePlugin.click();
            firebaseBadges = element(by.tagName('app-plugin-badges')).all(by.css('.mp-badge'));
            firebaseBadges.count().then(function (count) {
                expect(count).to.equal(12);
            });
            firebaseBadges.get(0).getAttribute('title').then(function (text) {
                expect(text.trim()).to.equal('Has Android support');
            })
            firebaseBadges.get(0).getCssValue('opacity').then(function (value) {
                expect(value).to.equal('1');
            });
            firebaseBadges.get(1).getAttribute('title').then(function (text) {
                expect(text.trim()).to.equal('Has iOS support');
            });
            firebaseBadges.get(2).getAttribute('title').then(function (text) {
                expect(text.trim()).to.match(/Webpack/i);
            });
            firebaseBadges.get(3).getAttribute('title').then(function (text) {
                expect(text.trim()).to.equal('Has demos in plugin repository');
            });
            firebaseBadges.get(4).getAttribute('title').then(function (text) {
                expect(text.trim()).to.equal('Has license information');
            });
            firebaseBadges.get(5).getAttribute('title').then(function (text) {
                expect(text.trim()).to.equal('Minimum NativeScript version support');
            });
            firebaseBadges.get(6).getAttribute('title').then(function (text) {
                expect(text.trim()).to.equal('Current NativeScript version support');
            });
            firebaseBadges.get(7).getAttribute('title').then(function (text) {
                expect(text.trim()).to.equal('TypeScript typings available');
            });
            firebaseBadges.get(8).getAttribute('title').then(function (text) {
                expect(text.trim()).to.equal('Has Angular support');
            });
            firebaseBadges.get(9).getAttribute('title').then(function (text) {
                expect(text.trim()).to.equal('Has Vue support');
            });
            firebaseBadges.get(10).getAttribute('title').then(function (text) {
                expect(text.trim()).to.equal('Travis CI builds enabled');
            });
            firebaseBadges.get(11).getAttribute('title').then(function (text) {
                expect(text.trim()).to.equal('Popular plugin');
            });
        });
         */
    }

    @Test
    @DisplayName("should show results when type in the search input")
    public void shouldShowResultsWhenTypeInTheSearchInput() {
        /*
                it('should show results when type in the search input', function () {
            browser.get(server);
            var input = element(by.tagName('app-input')).element(by.tagName('input'));
            input.getAttribute('placeholder').then(function (text) {
                expect(text).to.equal('Search for plugins');
            });
            input.click();
            input.sendKeys('pro ui');

            var proPlugin = element.all(by.css('.mp-plugin-info')).get(0);
            var proBadges = proPlugin.element(by.tagName('app-plugin-badges')).all(by.css('.mp-badge'));
            proBadges.get(0).getCssValue('opacity').then(function (value) {
                expect(value).to.equal('0.2');
            });
            proBadges.get(0).getAttribute('class').then(function (classValue) {
                expect(classValue).to.include('badge-disabled');
            });

            element(by.css('.mp-plugin-description')).getText().then(function (text) {
                expect(text.trim()).to.equal('Progress NativeScript Pro UI is a suite of rich user ' +
                    'interface components based on the native iOS and Android implementations.', '')
            });
            element(by.css('.mp-deprecated')).getText().then(function (text) {
                expect(text.trim()).to.equal('DEPRECATED', 'Verified text not present');
            });
        });
         */
    }
}
