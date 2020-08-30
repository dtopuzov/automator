package tests.e2e.web.tests.repo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.DisabledOnOs;
import org.junit.jupiter.api.condition.OS;
import org.openset.automator.test.web.WebTest;
import tests.e2e.web.pages.repo.RepoHomePage;

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
    @DisabledOnOs(OS.OTHER)
    void repoLabelsAreCorrect() {
        assertAll("Repo labels are displayed",
                () -> assertTrue(repoHomePage.isTagVisible("java")),
                () -> assertTrue(repoHomePage.isTagVisible("junit5")),
                () -> assertTrue(repoHomePage.isTagVisible("selenium")),
                () -> assertTrue(repoHomePage.isTagVisible("webdrivermanager")),
                () -> assertTrue(repoHomePage.isTagVisible("appium"))
        );
    }

    @Test
    void readmeIsDisplayed() {
        assertAll("Data from README.md is rendered",
                () -> assertTrue(repoHomePage.isTextVisibleInReadmeForm("The Automator")),
                () -> assertTrue(repoHomePage.isTextVisibleInReadmeForm("Technology Stack"))
        );
    }
}
