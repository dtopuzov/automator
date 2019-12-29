package org.openset.automator.image;

import java.awt.image.BufferedImage;

/**
 * Contains info for the result of image comparison.
 */
public class ImageComparisonResult {

    public final long diffPixels;
    public final double diffPercent;
    public final BufferedImage actualImage;
    public final BufferedImage diffImage;
    public final BufferedImage expectedImage;

    /**
     * Init image comparison result.
     */
    public ImageComparisonResult(long diffPixels,
                                 double diffPercent,
                                 BufferedImage actualImage,
                                 BufferedImage diffImage,
                                 BufferedImage expectedImage) {
        this.diffPixels = diffPixels;
        this.diffPercent = diffPercent;
        this.actualImage = actualImage;
        this.diffImage = diffImage;
        this.expectedImage = expectedImage;
    }
}
