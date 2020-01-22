package e2e.web.tests.repo;

import e2e.web.pages.repo.RepoHomePage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openset.automator.test.web.WebTest;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DisplayName("Repo home page tests")
public class RepoHomeTests extends WebTest {

    private RepoHomePage repoHomePage;

    @BeforeEach
    public void beforeEach() {
        repoHomePage = new RepoHomePage(context, "dtopuzov", "automator");
    }

    @Test
    void defaultBranchIsMaster() {

    }

    @Test
    void commitsLinkIsCorrect() {

    }

    @Test
    void branchesLinkIsCorrect() {

    }

    @Test
    void packagesLinkIsCorrect() {

    }

    @Test
    void releasesLinkIsCorrect() {

    }

    @Test
    void contributorLinkIsCorrect() {

    }

    @Test
    void licenseLinkIsCorrect() {

    }

    @Test
    void repoLabelsAreCorrect() {
        assertAll("Repo labels are displayed",
                () -> assertTrue(repoHomePage.isTagVisible("java")),
                () -> assertTrue(repoHomePage.isTagVisible("junit5")),
                () -> assertTrue(repoHomePage.isTagVisible("selenium")),
                () -> assertTrue(repoHomePage.isTagVisible("webdrivermanager")),
                () -> assertTrue(repoHomePage.isTagVisible("appium")),
                () -> assertTrue(repoHomePage.isTagVisible("saucelabs")),
                () -> assertTrue(repoHomePage.isTagVisible("sikuli"))
        );
    }

    @Test
    void readmeIsDisplayed() {
        assertAll("Data from README.md is rendered",
                () -> assertTrue(repoHomePage.isTextVisibleInReadmeForm("The Automator")),
                () -> assertTrue(repoHomePage.isTextVisibleInReadmeForm("Java based automation framework")),
                () -> assertTrue(repoHomePage.isTextVisibleInReadmeForm("Technology Stack"))
        );
    }
}
