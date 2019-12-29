package e2e.electron;

import org.openset.automator.test.electron.ElectronTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ElectronApiDemosTests extends ElectronTest {

    @Test
    @DisplayName("Electron API Demos Smoke Tests")
    public void smokeTest() {
        String page = getApp().getDriver().getPageSource();
        System.out.println(page);
    }
}
