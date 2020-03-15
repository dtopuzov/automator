package tests.unit;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.openqa.selenium.Rectangle;
import org.openset.automator.image.Image;
import org.openset.automator.image.ImageComparisonResult;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Image comparison tests.
 */
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@DisplayName("Image comparison tests")
class ImageComparisonTests {

    private static BufferedImage empty;
    private static BufferedImage test;
    private static BufferedImage testSimilar;

    @BeforeAll
    static void beforeImageTests() throws IOException {
        empty = getImageFile("100x100-empty.png");
        test = getImageFile("100x100-test.png");
        testSimilar = getImageFile("100x100-test-almost-the-same.png");
    }

    @Test
    @DisplayName("Compare equal images")
    void compareEqualImages() {
        ImageComparisonResult result = Image.compare(empty, empty);
        assertEquals(result.diffPercent, 0.0, "Image comparison failed.");
        assertEquals(result.diffPixels, 0, "Image comparison failed.");
    }

    @Test
    @DisplayName("Compare different images")
    void compareDifferentImages() {
        ImageComparisonResult result = Image.compare(empty, test);
        assertEquals(result.diffPercent, 10.59, "Image comparison failed.");
        assertEquals(result.diffPixels, 1059, "Image comparison failed.");
    }

    @Test
    @DisplayName("Compare similar images")
    void compareSimilarImages() {
        // Compare with similarity
        ImageComparisonResult result = Image.compare(test, testSimilar, 30);
        assertEquals(result.diffPercent, 0.0, "Image comparison failed.");
        assertEquals(result.diffPixels, 0, "Image comparison failed.");

        // Compare without similaroty
        result = Image.compare(test, testSimilar, 10);
        assertEquals(result.diffPercent, 89.41, "Image comparison failed.");
        assertEquals(result.diffPixels, 8941, "Image comparison failed.");
    }

    @Test
    @DisplayName("Compare with ignore header pixels")
    void compareSimilarImagesWithCustomRectangle() {
        // Compare with full-width rectangle
        Rectangle rectangle = new Rectangle(0, 30, 50, 100);
        ImageComparisonResult result = Image.compare(empty, test, rectangle, 0);
        assertEquals(4.97, result.diffPercent, "Image comparison failed.");
        assertEquals(497, result.diffPixels, "Image comparison failed.");

        // Compare with half-width rectangle
        rectangle = new Rectangle(50, 30, 50, 100);
        result = Image.compare(empty, test, rectangle, 0);
        assertEquals(2.36, result.diffPercent, "Image comparison failed.");
        assertEquals(236, result.diffPixels, "Image comparison failed.");
    }

    private static BufferedImage getImageFile(String imageName) throws IOException {
        ClassLoader classLoader = ImageComparisonTests.class.getClassLoader();
        File imageFile = new File(Objects.requireNonNull(classLoader.getResource("images/" + imageName)).getFile());
        return ImageIO.read(imageFile);
    }
}