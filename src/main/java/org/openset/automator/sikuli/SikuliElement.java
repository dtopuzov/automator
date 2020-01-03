package org.openset.automator.sikuli;

import org.sikuli.basics.Settings;

@SuppressWarnings({"unused", "UnusedReturnValue"})
public class SikuliElement {

    private String image;
    private float similarity;
    private int targetOffsetX;
    private int targetOffsetY;

    public SikuliElement(String image, float similarity, int targetOffsetX, int targetOffsetY) {
        super();
        this.image = image;
        this.similarity = similarity;
        this.targetOffsetX = targetOffsetX;
        this.targetOffsetY = targetOffsetY;
    }

    public SikuliElement(String image, float similarity) {
        super();
        this.image = image;
        this.similarity = similarity;
        this.targetOffsetX = 0;
        this.targetOffsetY = 0;
    }

    public SikuliElement(String image) {
        super();
        this.image = image;
        this.similarity = (float) Settings.MinSimilarity;
        this.targetOffsetX = 0;
        this.targetOffsetY = 0;
    }

    public String getImage() {
        return image;
    }

    public float getSimilarity() {
        return similarity;
    }

    public int getTargetOffsetX() {
        return targetOffsetX;
    }

    public int getTargetOffsetY() {
        return targetOffsetY;
    }
}
