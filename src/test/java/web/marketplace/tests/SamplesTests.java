package web.marketplace.tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import web.base.WebTest;
import web.marketplace.pages.HomePage;

public class SamplesTests extends WebTest {
    private static HomePage home;

    @BeforeAll
    public static void beforeHomePageTests() {
        home = new HomePage(context);
        home.navigateTo();
    }

    @Test
    @DisplayName("samples should be shown when samples tab is clicked")
    public void samplesShouldBeShownWhenSamplesTabIsClicked() {
        Assertions.assertEquals(this.home.getTitle(), "NativeScript Marketplace", "Wrong page title.");
        /*
                it('Samples should be shown when samples tab is clicked', function () {
            browser.get(server);
            util.verifyUrl(server);

            var samplesElem = element.all(by.css('.mp-tabs li')).last();
            samplesElem.getText().then(function (text) {
                expect(text.trim()).to.equal('Samples', "Samples text not found in last tab");
            });
            samplesElem.click();
        });

         */
    }

    @Test
    @DisplayName("frameworks filters should be present")
    public void frameworksFiltersShouldBePresent() {
    /*
            it('Frameworks filters should be present', async () => {
            var frameworkLabel = element(by.css(".mp-first-app-tab")).element(by.tagName("p"));
            frameworkLabel.getText().then(function (text) {
                expect(text).to.equal("FRAMEWORKS");
            });
            var frameworks = element.all(by.css(".mp-first-app-tab li")).then(function (items) {
                items[0].getText().then(function (text) {
                    expect(text).to.equal("All");
                });
                items[1].getText().then(function (text) {
                    expect(text).to.equal("Angular");
                });
                items[2].getText().then(function (text) {
                    expect(text).to.equal("Vue");
                });
                items[3].getText().then(function (text) {
                    expect(text).to.equal("Core");
                });
            });
        });
     */
    }

    @Test
    @DisplayName("categories filters should be present")
    public void categoriesFiltersShouldBePresent() {
    /*
        it('Categories filters should be present', async () => {
            var categoriesLabel = element(by.css(".mp-second-app-tab")).element(by.tagName("p"));
            categoriesLabel.getText().then(function (text) {
                expect(text).to.equal("CATEGORIES");
            });
            var frameworks = element.all(by.css(".mp-second-app-tab li")).then(function (items) {
                items[0].getText().then(function (text) {
                    expect(text).to.equal("All");
                });
                items[1].getText().then(function (text) {
                    expect(text).to.equal("Layouts & Pages");
                });
                //merge the third item assertion when 'and' and & are changed
                items[2].getText().then(function (text) {
                    expect(text).to.contain("Forms ");
                });
                items[2].getText().then(function (text) {
                    expect(text).to.contain(" Data");
                });
                items[3].getText().then(function (text) {
                    expect(text).to.equal("Interaction");
                });
                items[4].getText().then(function (text) {
                    expect(text).to.equal("Animations");
                });
                items[5].getText().then(function (text) {
                    expect(text).to.equal("Media");
                });
            });
        });
     */
    }

    @Test
    @DisplayName("should have search input with placeholder")
    public void shouldHaveSearchInputWithPlaceholder() {
        /*
                it('should have search input with placeholder', function () {
            element(by.css(".mp-search-input")).getAttribute('placeholder').then(function (text) {
                expect(text).to.equal('Search for samples');
            });
        });
         */
    }

    @Test
    @DisplayName("should show results when type in the search input")
    public void shouldShowResultsWhenTypeInTheSearchInput() {
        /*
        it('should show results when type in the search input', function () {
            var input = element(by.tagName('app-input')).element(by.tagName('input'));
            input.getAttribute('placeholder').then(function (text) {
                expect(text).to.equal('Search for samples');
            });
            input.click();
            input.sendKeys('calendar');
            element.all(by.css('.mp-plugin-info')).last().element(by.tagName('h3')).getText().then(function (text) {
                expect(text.trim()).to.equal('Basic Usage of RadCalendar');
            });
            element.all(by.css('.mp-plugin-description')).last().getText().then(function (text) {
                expect(text.trim()).to.contain('RadCalendar from NativeScript UI has all the features you need to show calendar, range date picking, showing events, and more.');
            });
        });
         */
    }

    @Test
    @DisplayName("verify submit sample is present")
    public void verifySubmitSampleIsPresent() {
        /*
                it('verify submit sample is present', function () {
            browser.refresh();
            element(by.css('.mp-submit')).getText().then(function (text) {
                expect(text.trim()).to.equal('SUBMIT SAMPLE');
            });
        });
         */
    }

    @Test
    @DisplayName("verify description is provided when no results are found")
    public void verifyDescriptionIsProvidedWhenNoResultsAreFound() {
        /*
        it('verify description is provided when no results are found', function () {
            browser.refresh();
            var input = element(by.tagName('app-input')).element(by.tagName('input'));
            input.getAttribute('placeholder').then(function (text) {
                expect(text).to.equal('Search for samples');
            });
            input.click();
            input.sendKeys('search for non-existing sample');
            element(by.css('.no-plugins-heading')).getText().then(function (text) {
                expect(text).to.contain("We looked and looked...");
            })
            element(by.css('.no-plugins-heading')).element(by.tagName('p')).getText().then(function (text) {
                expect(text).to.equal("but we couldn't find any results for \"search for non-existing sample\"");
            });
        });
         */
    }

    @Test
    @DisplayName("verify clear icon restores the search input to initial state")
    public void verifyClearIconRestoresTheSearchInputToInitialState() {
        /*
                it('verify clear icon restores the search input to initial state', function () {
            element(by.css('.mp-clear-icon')).click();
            var input = element(by.tagName('app-input')).element(by.tagName('input'));
            input.getAttribute('placeholder').then(function (text) {
                expect(text).to.equal('Search for samples');
            });
            input.getText().then(function (text) {
                expect(text).to.equal("");
            })
        })
         */
    }
}
