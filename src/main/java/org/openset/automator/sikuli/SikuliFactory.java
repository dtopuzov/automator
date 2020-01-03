package org.openset.automator.sikuli;

import org.apache.commons.lang3.NotImplementedException;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

@SuppressWarnings("unused")
public class SikuliFactory {

    public static void initElements(Object page) {
        Class<?> proxyIn = page.getClass();
        while (proxyIn != Object.class) {
            proxyFields(page, proxyIn);
            proxyIn = proxyIn.getSuperclass();
        }
    }

    private static void proxyFields(Object page, Class<?> proxyIn) {
        Field[] fields = proxyIn.getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            if (field.getType().getTypeName().contains("SikuliElement")) {
                SikuliElement sikuliElement = createSikuliElement(field);
                try {
                    field.set(page, sikuliElement);
                } catch (IllegalArgumentException | IllegalAccessException e) {
                    throw new RuntimeException(e);
                }
            }

        }
    }

    private static SikuliElement createSikuliElement(Field field) {
        Annotation[] annotations = field.getDeclaredAnnotations();
        String image = null;
        float similarity = 0.9f;
        int x = 0;
        int y = 0;
        for (Annotation annotation : annotations) {
            if (annotation instanceof FindBy) {
                FindBy myAnnotation = (FindBy) annotation;
                image = myAnnotation.image();
                similarity = myAnnotation.similarity();
                x = myAnnotation.targetOffsetX();
                y = myAnnotation.targetOffsetY();
            } else {
                throw new NotImplementedException("");
            }
        }
        return new SikuliElement(image, similarity, x, y);
    }
}
