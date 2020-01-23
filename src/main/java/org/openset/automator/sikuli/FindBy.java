package org.openset.automator.sikuli;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * FindBy annotations for Sikuli.
 */
@Retention(RUNTIME)
@Target(FIELD)
public @interface FindBy {
    /**
     * Path to image (relative to base image path, see SikuliConfig).
     *
     * @return path to image.
     */
    String image() default "";

    /**
     * Similarity used when searching for this image.
     *
     * @return similarity as float.
     */
    float similarity() default 0.9f;

    /**
     * X offset of center of the element found by image.
     *
     * @return X offset in pixels.
     */
    int targetOffsetX() default 0;

    /**
     * Y offset of center of the element found by image.
     *
     * @return y offset in pixels.
     */
    int targetOffsetY() default 0;
}