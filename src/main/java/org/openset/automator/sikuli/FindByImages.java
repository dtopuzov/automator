package org.openset.automator.sikuli;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Retention(RUNTIME)
@Target(FIELD)
public @interface FindByImages {
    String[] value() default "";

    float similarity() default 70;

    int x() default 0;

    int y() default 0;
}