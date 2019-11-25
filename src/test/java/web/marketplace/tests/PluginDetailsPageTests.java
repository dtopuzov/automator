package web.marketplace.tests;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import web.base.WebTest;
import web.marketplace.enums.Tab;
import web.marketplace.pages.HomePage;

public class PluginDetailsPageTests extends WebTest {

    private static HomePage home;

    @BeforeAll
    public static void beforeSearchTests() {
        home = new HomePage(context);
        home.navigateTo();
        home.openTab(Tab.PLUGINS);
    }

    @Test
    @DisplayName("should have header with link to nativescript.org")
    public void shouldHaveHeaderWithLinkToNativescriptOrg() {
        //            util.verifyHeader();
    }

    @Test
    @DisplayName("should have CLI command for adding the plugin")
    public void shouldHaveCLICommandForAddingThePlugin() {
        /*
        element(by.css('.mp-input')).element(by.css('.mp-search-input')).getAttribute('value').then(function (value) {
                expect(value).to.equal('tns plugin add nativescript-pro-ui');
            });
         */
    }

    @Test
    @DisplayName("should have link to verified plugin criteria")
    public void shouldHaveLinkToVerifiedPluginCriteria() {
        /*
            element(by.css('.mp-plugin-detail-link')).element(by.css('.mp-link')).getText().then(function (text) {
                expect(text).to.equal('Verified vs. unverified plugins. Want to know the difference?');
            });
         */
    }

    @Test
    @DisplayName("should have link to navigate to Home Page")
    public void shouldHaveLinkToNavigateToHomePage() {
        /*
        it('should have link to navigate to Home Page', function () {
            var headerName = element(by.css('.mp-header-name'));
            headerName.getText().then(function (text) {
                expect(text).to.contain('Marketplace');
            });
            headerName.click();
        });
         */
    }

/*
    describe('Plugin github homepage verification', function () {
        it('Navigate to nativescript-zip plugin and verify github page is present in details list', function () {
            browser.get(server);
            util.verifyHeader();

            var input = element(by.tagName('app-input')).element(by.tagName('input'));
            input.getAttribute('placeholder').then(function (text) {
                expect(text).to.equal('Search for plugins');
            });
            input.click();
            input.sendKeys('nativescript-zip');
            element.all(by.css('.mp-plugin-description')).first().getText().then(function (text) {
                expect(text.trim()).to.equal('A simple utility class for zipping and unzipping files in NativeScript')
            });

            var pluginItem = element.all(by.css('.mp-plugin-item')).first();
            pluginItem.element(by.tagName('h3')).getText().then(function (text) {
                expect(text.trim()).to.equal('Zip');
            });
            pluginItem.click();
            util.verifyUrl(server + 'plugins/nativescript-zip');

            var gitLink = element(by.css('.mp-plugin-detail-info-list')).element(by.tagName('a'));
            gitLink.getText().then(function (text) {
                expect(text.trim()).to.equal('nativescript-zip', "github link not found");
            })
        });

        it('Navigate to couch-db plugin and verify github page is present in details list', function () {
            this.timeout(40000);
            browser.get(server);
            util.verifyHeader();

            var input = element(by.tagName('app-input')).element(by.tagName('input'));
            input.getAttribute('placeholder').then(function (text) {
                expect(text).to.equal('Search for plugins');
            });
            input.click();
            input.sendKeys('couchdb');
            element(by.css('.mp-plugin-description')).getText().then(function (text) {
                expect(text.trim()).to.equal('Couchdb plugin for nativescript')
            });

            var pluginItem = element(by.css('.mp-plugin-item'));
            pluginItem.element(by.tagName('h3')).getText().then(function (text) {
                expect(text.trim()).to.equal('Couchdb');
            });
            pluginItem.click();
            util.verifyUrl(server + 'plugins/nativescript-couchdb');

            var gitLink = element(by.css('.mp-plugin-detail-info-list')).element(by.tagName('a'));
            gitLink.getText().then(function (text) {
                expect(text.trim()).to.equal('nativescript-couchdb', "github link not found");
            })
            gitLink.click();
            browser.sleep(3000); // Fix for https://github.com/angular/protractor/issues/2643
            browser.waitForAngularEnabled(false); // Fix for https://github.com/angular/protractor/issues/2643
            browser.getAllWindowHandles().then(function (handles) {
                browser.waitForAngularEnabled(false); // Fix for https://github.com/angular/protractor/issues/2643
                browser.driver.switchTo().window(handles[1]);
                browser.driver.sleep(5000);
                util.verifyUrl("https://github.com/paragasu/nativescript-couchdb");
                browser.driver.close();
                browser.driver.switchTo().window(handles[0]);
            });
            browser.waitForAngularEnabled(true); // Fix for https://github.com/angular/protractor/issues/2643
        });

        it('Navigate to Will plugin and verify github page is NOT present in details list', function () {
            browser.get(server);
            util.verifyHeader();

            var input = element(by.tagName('app-input')).element(by.tagName('input'));
            input.getAttribute('placeholder').then(function (text) {
                expect(text).to.equal('Search for plugins');
            });
            input.click();
            input.sendKeys('Will');
            element(by.css('.mp-plugin-description')).getText().then(function (text) {
                expect(text.trim()).to.equal('NativeScript plugin to play audio (but not record, removed for this fork)')
            });

            var pluginItem = element(by.css('.mp-plugin-item'));
            pluginItem.element(by.tagName('h3')).getText().then(function (text) {
                expect(text.trim()).to.equal('Will');
            });
            pluginItem.click();
            util.verifyUrl(server + 'plugins/nativescript-will');

            var infoItems = element(by.css('.mp-plugin-detail-info-list')).all(by.tagName('li')).then(function (items) {
                expect(items.length).to.equal(2);
                items[0].getText().then(function (text) {
                    expect(text).to.contain("Version");
                });
                items[1].getText().then(function (text) {
                    expect(text).to.contain("License");
                });
            });
        });

        it('Navigate to nativescript-CallLog plugin and verify tns install command is Case Sensitive', function () {
            browser.get(server);
            util.verifyHeader();

            var input = element(by.tagName('app-input')).element(by.tagName('input'));
            input.getAttribute('placeholder').then(function (text) {
                expect(text).to.equal('Search for plugins');
            });
            input.click();
            input.sendKeys('nativescript-CallLog');
            element(by.css('.mp-plugin-description')).getText().then(function (text) {
                expect(text.trim()).to.equal('Provides call log history for NativeScript mobile applications.')
            });

            var pluginItem = element(by.css('.mp-plugin-item'));
            pluginItem.element(by.tagName('h3')).getText().then(function (text) {
                expect(text.trim()).to.equal('Call Log');
            });
            pluginItem.click();
            util.verifyUrl(server + 'plugins/nativescript-CallLog');

            var tnsCommand = element(by.css('.mp-input')).element(by.css('.mp-search-input')).getAttribute('value').then(function (value) {
                expect(value).to.equal('tns plugin add nativescript-CallLog');
            });

        });
    });

*/

}
