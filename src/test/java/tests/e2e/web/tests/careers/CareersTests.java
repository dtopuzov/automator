package tests.e2e.web.tests.careers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openset.automator.test.web.WebTest;
import tests.e2e.web.pages.careers.CareersPage;

import static org.junit.jupiter.api.Assertions.assertTrue;

@DisplayName("Careers tests")
public class CareersTests extends WebTest {

    private CareersPage careersPage;

    @BeforeEach
    void beforeEachCareerTests() {
        careersPage = new CareersPage(context).navigateTo();
    }

    @Test
    void checkEngineeringPositions() {
        int openedPositions = careersPage.expandPositions("Engineering").getPositionsInArea("Engineering").size();
        assertTrue(openedPositions > 1, "Opened Engineering positions are less than expected.");
    }
}
