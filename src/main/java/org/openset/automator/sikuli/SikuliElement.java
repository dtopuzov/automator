package org.openset.automator.sikuli;

import org.sikuli.basics.Settings;

@SuppressWarnings({"unused", "UnusedReturnValue"})
public class SikuliElement {

    private String image;
    private float similarity;
    private int targetOffsetX;
    private int targetOffsetY;

    /**
     * Init Sikuli element.
     * @param image path to image (relative to baseImagePath from SikuliConfig).
     * @param similarity similarity as float (max is 1.0).
     * @param targetOffsetX x offset of center of the picture in pixels.
     * @param targetOffsetY y offset of center of the picture in pixels.
     */
    public SikuliElement(String image, float similarity, int targetOffsetX, int targetOffsetY) {
        super();
        this.image = image;
        this.similarity = similarity;
        this.targetOffsetX = targetOffsetX;
        this.targetOffsetY = targetOffsetY;
    }

    /**
     * Init Sikuli element.
     * @param image path to image (relative to baseImagePath from SikuliConfig).
     * @param similarity similarity as float (max is 1.0).
     */
    public SikuliElement(String image, float similarity) {
        super();
        this.image = image;
        this.similarity = similarity;
        this.targetOffsetX = 0;
        this.targetOffsetY = 0;
    }

    /**
     * Init Sikuli element.
     * @param image path to image (relative to baseImagePath from SikuliConfig).
     */
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
