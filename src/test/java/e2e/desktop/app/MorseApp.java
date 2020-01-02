package e2e.desktop.app;

import org.junit.jupiter.api.Assertions;
import org.openset.automator.app.desktop.DesktopApp;
import org.openset.automator.settings.desktop.DesktopSettings;
import org.openset.automator.sikuli.SikuliElementOld;
import org.sikuli.script.Region;

public class MorseApp extends DesktopApp {
    /*
    public class AppPage {

    Screen sikuli;

    @FindBy(image = "honey.png")
    private SikuliElement honey;

    @FindBy(image = "exit-button.png")
    private SikuliElement exitButton;

    @FindBy(image = "congratulations.png")
    private SikuliElement congratulations;

    public AppPage(Screen sikuli) {
        this.sikuli = sikuli;
        SikuliFactory.initElements(sikuli, this);
    }

    public void play() {
        honey.wait(5);
        while (!exitButton.exists(0)) {
            honey.click();
        }
    }

    public boolean hasThePlayerWon() {
        return congratulations.exists(0);
    }
}

     */
    private SikuliElementOld clearEnglishText = sikuli.elementOf("ClearEnglishText");
    private SikuliElementOld clearMorseText = sikuli.elementOf("ClearMorseText");
    private SikuliElementOld englishToMorse = sikuli.elementOf("EnglishToMorse");
    private SikuliElementOld morseToEnglish = sikuli.elementOf("MorseToEnglish");
    private SikuliElementOld englishTextLabel = sikuli.elementOf("EnglishText");
    private SikuliElementOld morseTextLabel = sikuli.elementOf("MorseCode");

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
