package web.marketplace.tests;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import web.base.WebTest;

public class ScrollTests extends WebTest {
    @Test
    @DisplayName("should work without resetting position")
    void scrollShouldWorkWithoutResettingPosition() {
        /*
            describe('Scroll', function () {
        it('should work without resetting position', function () {
            browser.get(server);
            var input = element(by.tagName('app-input')).element(by.tagName('input'));
            input.click();
            input.sendKeys('sys');

            for (let index = 0; index < 12; index++) {
                input.sendKeys(protractor.Key.PAGE_DOWN);
                // Throws UnsupportedOperationError: sendKeysToActiveElement with chromedriver 75.0.3770.8
                //browser.actions().sendKeys(protractor.Key.PAGE_DOWN).perform();
                browser.sleep(500);
            }

            var EC = protractor.ExpectedConditions;
            browser.wait(EC.visibilityOf(element(by.tagName('footer'))), 2000);
            var footer = element(by.tagName('footer')).element(by.css('.license-block')).getText().then(function (text) {
                expect(text).to.equal("NativeScript is licensed under the Apache 2.0 license.");
            });
        });
    });
         */
    }
}
