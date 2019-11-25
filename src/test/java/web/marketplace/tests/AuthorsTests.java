package web.marketplace.tests;

public class AuthorsTests {
    /*
        describe('Author Detail page', function () {
        it('should to navigate to Home Page when click the link', function () {
            var headerName = element(by.css('.mp-header-name'));
            headerName.getText().then(function (text) {
                expect(text).to.contain('Marketplace');
            });
            headerName.click();
            util.verifyUrl(server);
            util.verifyHeader();
        });
    });

    describe('Authors Page', function () {
        it('Home page should have navigation link to Authors page', function () {
            browser.get(server);
            var authors = element.all(by.css('.mp-header-link')).first();
            authors.click();
            util.verifyUrl(server + 'authors');
            util.verifyHeader();
        });

        it('Should have sections with Top and New authors', function () {
            var topAuthors = element(by.css('.mp-authors-sections')).all(by.css('.mp-authors-heading')).first();
            var newAuthors = element(by.css('.mp-authors-sections')).all(by.css('.mp-authors-heading')).last();
            topAuthors.getText().then(function (text) {
                expect(text.trim()).to.equal('Top Authors');
            });
            newAuthors.getText().then(function (text) {
                expect(text.trim()).to.equal('New Authors');
            });
        });

        it('Top and New Sections should have 12 authors', function () {
            element(by.css('.mp-authors-sections')).all(by.css('.mp-author-item')).then(function (authors) {
                expect(authors.length).to.equal(12);
            });
        })

        it('Should have section with all authors', function () {
            var allAuthorsSection = element(by.css('.mp-authors-list')).all(by.css('.mp-authors')).last();
            allAuthorsSection.element(by.css('.mp-authors-heading')).getText().then(function (text) {
                expect(text.trim()).to.contain('All Authors (');
            })

        })

        it('At least 20 authors should be loaded initially in all authors section', function () {
            var allAuthorsSection = element(by.css('.mp-authors-list')).all(by.css('.mp-authors')).last();
            allAuthorsSection.all(by.css('.mp-author-item')).then(function (authors) {
                expect(authors.length).to.be.above(20);
            });
        })

        it('should show Plugins, Templates and Samples labels', function () {
            const samplesLabel = element.all(by.xpath('//span[contains(text(), "Samples")]')).first().getText().then(function (text) {
                expect(text).to.contain("Samples");
            });
            const templatesLabel = element.all(by.xpath('//span[contains(text(), "Templates")]')).first().getText().then(function (text) {
                expect(text).to.contain("Templates");
            });
            const pluginsLabel = element.all(by.xpath('//span[contains(text(), "Plugins")]')).first().getText().then(function (text) {
                expect(text).to.contain("Plugins");
            });
        })

        it('Should navigate to authors details page when click on authors name', function () {
            element.all(by.xpath('//h3[contains(text(), "NativeScript Team")]')).first().click();
            util.verifyUrl(server + 'author/tns-bot');
            util.verifyHeader();
            element(by.css('.mp-author-detail-title')).getText().then(function (text) {
                expect(text.trim()).to.equal('NativeScript Team');
            });
        })

        it('should find Plugins and Templates tab on author details page', function () {
            const allTabs = element(by.tagName('app-tabs')).all(by.tagName('li'));
            allTabs.first().getAttribute('class').then(function (classAttribute) {
                expect(classAttribute).to.equal('selected');
            });
            allTabs.first().getText().then(function (text) {
                expect(text).to.equal('Plugins');
            });
            allTabs.last().getAttribute('class').then(function (classAttribute) {
                expect(classAttribute).to.equal('-hidden');
            });
            allTabs.get(1).getText().then(function (text) {
                expect(text).to.equal('Templates');
            });

            allTabs.get(1).click();
            const samplesLabel = element(by.xpath('//h3[contains(text(), "Master-Detail (with Firebase)")]')).getText().then(function (text) {
                expect(text).to.equal("Master-Detail (with Firebase)");
            });
        })

        it('should have at least one Template with labels for at least three flavors', function () {
            const templates = element.all(by.css('.mp-link.mp-plugin-item'));
            templates.count().then(function (count) {
                expect(count).to.be.at.least(1);
            });
            const flavors = element.all(by.css('.mp-link.mp-plugin-item')).first().all(by.tagName('app-icon'));
            flavors.count().then(function (count) {
                expect(count).to.be.at.least(3);
            });
        })

        it('should find Samples tab on author details page', function () {
            const authors = element.all(by.css('.mp-header-link')).get(0).click();
            authors.click();
            element.all(by.css('.mp-authors-heading')).first().click();
            const elem = element(by.tagName('body'));
            elem.sendKeys(protractor.Key.PAGE_DOWN);
            element(by.xpath('//h3[contains(text(), "Shiva Prasad")]')).click();
            const allTabs = element(by.tagName('app-tabs')).all(by.tagName('li'));
            allTabs.first().getAttribute('class').then(function (classAttribute) {
                expect(classAttribute).to.equal('selected');
            });
            allTabs.first().getText().then(function (text) {
                expect(text).to.equal('Plugins');
            });
            allTabs.get(1).getAttribute('class').then(function (classAttribute) {
                expect(classAttribute).to.equal('-hidden');
            });
            allTabs.get(2).getText().then(function (text) {
                expect(text).to.equal('Samples');
            });

            allTabs.get(2).click();
            const innerTabs = element(by.css('.mp-tabs.-light'));
            innerTabs.element(by.tagName('p')).getText().then(function (text) {
                expect(text).to.equal('FRAMEWORKS');
            });

            const allFrameworks = innerTabs.all(by.tagName('li'));
            allFrameworks.get(0).getText().then(function (text) {
                expect(text).to.equal('ANGULAR');
            });
            allFrameworks.get(0).getAttribute('class').then(function (classAttr) {
                expect(classAttr).to.equal('selected');
            });
            element.all(by.xpath('//h3[contains(text(), "Adding Video to Your App")]')).first().getText().then(function (text) {
                expect(text).to.contain("Adding Video to Your App");
            });
            allFrameworks.get(1).getText().then(function (text) {
                expect(text).to.equal('VUE');
            });
            allFrameworks.get(2).getText().then(function (text) {
                expect(text).to.equal('CORE');
            });
            allFrameworks.get(1).click();
            element.all(by.css('.mp-plugin-item')).first().element(by.tagName('h3')).getText().then(function (text) {
                expect(text).to.equal('Implementing a Parallax Scroll Effect');
            });
            allFrameworks.get(2).click();
            element.all(by.xpath('//h3[contains(text(), "Adding Dialogs to Your App")]')).first().getText().then(function (text) {
                expect(text).to.contain("Adding Dialogs to Your App");
            });
        })
    });

     */
}
