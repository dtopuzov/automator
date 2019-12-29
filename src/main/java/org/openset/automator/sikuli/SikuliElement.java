package org.openset.automator.sikuli;

public class SikuliElement {
    private String image;
    private String description;

    public SikuliElement(String image) {
        init(image, null);
    }

    public SikuliElement(String image, String description) {
        init(image, description);
    }

    public String getDescription() {
        return description;
    }

    public String getImage() {
        return image;
    }

    private void init(String image, String description) {
        this.image = image;
        this.description = description;
    }
}
