package unit;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
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
public class ImageComparisonTests {

    private static BufferedImage empty;
    private static BufferedImage test;
    private static BufferedImage testSimilar;

    @BeforeAll
    public static void beforeImageTests() throws IOException {
        empty = getImageFile("100x100-empty.png");
        test = getImageFile("100x100-test.png");
        testSimilar = getImageFile("100x100-test-almost-the-same.png");
    }

    @Test
    @DisplayName("Compare equal images")
    public void compareEqualImages() {
        ImageComparisonResult result = Image.compare(empty, empty);
        assertEquals(result.diffPercent, 0.0, "Image comparison failed.");
        assertEquals(result.diffPixels, 0, "Image comparison failed.");
    }

    @Test
    @DisplayName("Compare different images")
    public void compareDifferentImages() {
        ImageComparisonResult result = Image.compare(empty, test);
        assertEquals(result.diffPercent, 10.59, "Image comparison failed.");
        assertEquals(result.diffPixels, 1059, "Image comparison failed.");
    }

    @Test
    @DisplayName("Compare similar images")
    void compareSimilarImages() {
        ImageComparisonResult result = Image.compare(test, testSimilar);
        assertEquals(result.diffPercent, 0.0, "Image comparison failed.");
        assertEquals(result.diffPixels, 0, "Image comparison failed.");
    }

    @Test
    @DisplayName("Compare with custom tolerance")
    void compareSimilarImagesWithCustomTolerance() {
        ImageComparisonResult result = Image.compare(test, testSimilar, 1);
        assertEquals(result.diffPercent, 89.41, "Image comparison failed.");
        assertEquals(result.diffPixels, 8941, "Image comparison failed.");
    }

    @Test
    @DisplayName("Compare with ignore header pixels")
    void compareSimilarImagesIgnoreHeader() {
        ImageComparisonResult result = Image.compare(empty, test, 30, 50);
        assertEquals(result.diffPercent, 5.62, "Image comparison failed.");
        assertEquals(result.diffPixels, 562, "Image comparison failed.");
    }

    private static BufferedImage getImageFile(String imageName) throws IOException {
        ClassLoader classLoader = ImageComparisonTests.class.getClassLoader();
        File imageFile = new File(Objects.requireNonNull(classLoader.getResource("images/" + imageName)).getFile());
        return ImageIO.read(imageFile);
    }
}