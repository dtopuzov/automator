package org.openset.automator.sikuli;

import org.openset.automator.image.Image;
import org.openset.automator.settings.sikuli.SikuliConfig;
import org.sikuli.basics.Settings;
import org.sikuli.script.*;

public class Sikuli {
    private SikuliConfig config;
    private Screen screen;

    /**
     * Init Sikuli.
     *
     * @param config instance of SikuliConfig.
     */
    public Sikuli(SikuliConfig config) {
        this.config = config;
        this.screen = new Screen();
        ImagePath.add(config.baseImagePath);
        Settings.MinSimilarity = config.defaultSimilarity;
        Settings.MoveMouseDelay = 0;
        Settings.ActionLogs = false;
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

    /**
     * Find element on screen.
     *
     * @param element    SikuliElement.
     * @param similarity similarity.
     * @return region
     */
    public Region find(SikuliElement element, Float similarity) {
        Pattern pattern = new Pattern(element.getImage()).similar(similarity);
        // TODO: When element is not found screen.exists(pattern) is null and .offset() throws NullPointerException.
        return screen.exists(pattern, 10).offset(element.getTargetOffsetX(), element.getTargetOffsetY());
    }

    public Region find(SikuliElement element) {
        return find(element, config.defaultSimilarity);
    }

    public void click(SikuliElement element) {
        find(element).click();
    }

    public void type(String text) {
        screen.type(text);
    }

    public boolean isVisible(String text, Region region) {
        String actualText = OCR.readText(Image.getScreen(region.getRect()));
        return actualText.contains(text);
    }
}
