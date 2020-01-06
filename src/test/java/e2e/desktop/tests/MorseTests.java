package e2e.desktop.tests;

import e2e.desktop.app.MorseApp;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openset.automator.test.desktop.DesktopTest;

@DisplayName("Smoke tests for More Code Translator desktop application.")
public class MorseTests extends DesktopTest {

    private MorseApp morseApp = new MorseApp(getContext().getSettings());

    @Test
    @DisplayName("Translate english to morse.")
    public void testTranslateEnglishToMorse() {
        morseApp.clearEnglishText();
        morseApp.typeEnglishText("Automator");
        morseApp.translateEnglishToMorse();
        // Ignore this check because Sikuli OCR can not recognize dots and dashes correctly.
        // morseApp.verifyTextVisibleInMorse(".- ..- - --- -- .- - --- .-.  /");
    }

    @Test
    @DisplayName("Translate morse to english.")
    public void testTranslateMorseToEnglish() {
        morseApp.clearMorseText();
        morseApp.typeMorseCode(".... . .-.. .-.. ---  / .-- --- .-. .-.. -..  /");
        morseApp.translateMorseToEnglish();
        morseApp.verifyTextVisibleInEnglish("hello world");
    }
}
