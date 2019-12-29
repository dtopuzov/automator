package e2e.desktop.app;

import org.junit.jupiter.api.Assertions;
import org.openset.automator.app.desktop.DesktopApp;
import org.openset.automator.settings.desktop.DesktopSettings;
import org.openset.automator.sikuli.SikuliElement;
import org.sikuli.script.Region;

public class MorseApp extends DesktopApp {
    private SikuliElement clearEnglishText = sikuli.elementOf("ClearEnglishText");
    private SikuliElement clearMorseText = sikuli.elementOf("ClearMorseText");
    private SikuliElement englishToMorse = sikuli.elementOf("EnglishToMorse");
    private SikuliElement morseToEnglish = sikuli.elementOf("MorseToEnglish");
    private SikuliElement englishTextLabel = sikuli.elementOf("EnglishText");
    private SikuliElement morseTextLabel = sikuli.elementOf("MorseCode");

    public MorseApp(DesktopSettings settings) {
        super(settings);
    }

    public void typeEnglishText(String text) {
        sikuli.click(englishTextLabel, 0, 200);
        sikuli.type(text);
    }

    public void typeMorseCode(String text) {
        sikuli.click(morseTextLabel, 0, 200);
        sikuli.type(text);
    }

    public void clearEnglishText() {
        sikuli.click(clearEnglishText);
    }

    public void clearMorseText() {
        sikuli.click(clearMorseText);
    }

    public void translateEnglishToMorse() {
        sikuli.click(englishToMorse);
    }

    public void translateMorseToEnglish() {
        sikuli.click(morseToEnglish);
    }

    public void verifyTextVisibleInEnglish(String text) {
        Region region = sikuli.find(englishTextLabel).grow(150, 150, 0, 50);
        Assertions.assertTrue(sikuli.isVisible(text, region), String.format("\"%s\" is not visible.", text));
    }

    public void verifyTextVisibleInMorse(String text) {
        Region region = sikuli.find(morseTextLabel).grow(150, 150, 0, 50);
        Assertions.assertTrue(sikuli.isVisible(text, region), String.format("\"%s\" is not visible.", text));
    }
}
