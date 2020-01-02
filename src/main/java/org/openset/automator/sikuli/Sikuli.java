package org.openset.automator.sikuli;

import org.openset.automator.image.Image;
import org.openset.automator.settings.sikuli.SikuliConfig;
import org.sikuli.basics.Settings;
import org.sikuli.script.Pattern;
import org.sikuli.script.Region;
import org.sikuli.script.Screen;
import org.sikuli.script.TextRecognizer;

import java.nio.file.Path;
import java.nio.file.Paths;

public class Sikuli {
    private SikuliConfig config;
    private Screen screen;

    public Sikuli(SikuliConfig config) {
        this.config = config;
        Settings.MinSimilarity = config.defaultSimilarity;
        screen = new Screen();
    }

    public SikuliElementOld elementOf(String image, String description) {
        Path imagePath = Paths.get("src", "test", "resources", config.baseImagePath, image + ".png");
        return new SikuliElementOld(imagePath.toAbsolutePath().toString(), description);
    }

    public SikuliElementOld elementOf(String image) {
        return elementOf(image, getDescriptionFromImageName(image));
    }

    private String getDescriptionFromImageName(String image) {
        return image.replaceAll(
                String.format("%s|%s|%s",
                        "(?<=[A-Z])(?=[A-Z][a-z])",
                        "(?<=[^A-Z])(?=[A-Z])",
                        "(?<=[A-Za-z])(?=[^A-Za-z])"
                ),
                " "
        );
    }

    public Region find(SikuliElementOld element, Float similarity, int xOffset, int yOffset) {
        Pattern pattern = new Pattern(element.getImage()).similar(similarity);
        return screen.exists(pattern).offset(xOffset, yOffset);
    }

    public Region find(SikuliElementOld element, int xOffset, int yOffset) {
        return find(element, config.defaultSimilarity, xOffset, yOffset);
    }

    public Region find(SikuliElementOld element) {
        return find(element, config.defaultSimilarity, 0, 0);
    }

    public void click(SikuliElementOld element, int xOffset, int yOffset) {
        find(element, xOffset, yOffset).click();
    }

    public void click(SikuliElementOld element) {
        click(element, 0, 0);
    }

    public void type(String text) {
        screen.type(text);
    }

    public boolean isVisible(String text, Region region) {
        String actualText = TextRecognizer.doOCR(Image.getScreen(region.getRect()));
        return actualText.contains(text);
    }
}
