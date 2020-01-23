package tests.e2e.desktop.app;

import org.junit.jupiter.api.Assertions;
import org.openset.automator.app.desktop.DesktopApp;
import org.openset.automator.settings.desktop.DesktopSettings;
import org.openset.automator.sikuli.FindBy;
import org.openset.automator.sikuli.SikuliElement;
import org.sikuli.script.Region;

@SuppressWarnings("unused")
public class MorseApp extends DesktopApp {

    @FindBy(image = "clean-english-text.png")
    private SikuliElement clearEnglishText;

    @FindBy(image = "clean-morse-text.png")
    private SikuliElement clearMorseText;

    @FindBy(image = "english-to-morse.png")
    private SikuliElement englishToMorse;

    @FindBy(image = "morse-to-english.png")
    private SikuliElement morseToEnglish;

    @FindBy(image = "english-text-label.png")
    private SikuliElement englishTextLabel;

    @FindBy(image = "morse-code-label.png")
    private SikuliElement morseCodeLabel;

    @FindBy(image = "english-text-label.png", targetOffsetY = 200)
    private SikuliElement englishTextBox;

    @FindBy(image = "morse-code-label.png", targetOffsetY = 200)
    private SikuliElement morseCodeTextBox;

    public MorseApp(DesktopSettings settings) {
        super(settings);
    }

    public void typeEnglishText(String text) {
        sikuli.click(englishTextBox);
        sikuli.type(text);
    }

    public void typeMorseCode(String text) {
        sikuli.click(morseCodeTextBox);
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
        Region region = sikuli.find(morseCodeLabel).grow(150, 150, 0, 50);
        Assertions.assertTrue(sikuli.isVisible(text, region), String.format("\"%s\" is not visible.", text));
    }
}
