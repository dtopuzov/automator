package tests.perf;

import java.util.Random;

public class Utils {
    private static String[] terms = {"Java", "Junit4", "Junit5", "TestNG", "Selenium", "Appium"};

    public static String getRandomSearchUrl() {
        int rnd = new Random().nextInt(terms.length);
        return String.format("https://www.google.com/search?q=%s", terms[rnd]);
    }
}
