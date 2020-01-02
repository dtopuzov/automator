package org.openset.automator.sikuli;

public class SikuliElementOld {
    private String image;
    private String description;

    public SikuliElementOld(String image) {
        init(image, null);
    }

    public SikuliElementOld(String image, String description) {
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
