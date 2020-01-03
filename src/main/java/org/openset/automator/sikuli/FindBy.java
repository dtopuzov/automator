package org.openset.automator.sikuli;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Retention(RUNTIME)
@Target(FIELD)
public @interface FindBy {
    String image() default "";
    float similarity() default 0.9f;
    int targetOffsetX() default 0;
    int targetOffsetY() default 0;
}