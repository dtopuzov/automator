package org.openset.automator.settings.sikuli;

import org.openset.automator.settings.base.BaseSettings;

import java.io.File;
import java.util.Properties;

/**
 * Sikuli configuration.
 */
public class SikuliConfig {
    public Float defaultSimilarity;
    public String baseImagePath;

    /**
     * Init Sikuli configuration.
     *
     * @param settings instance of BaseSettings.
     */
    public SikuliConfig(BaseSettings settings) {
        Properties properties = settings.properties;
        this.defaultSimilarity = Float.valueOf(properties.getProperty("defaultSimilarity", "0.9D"));
        this.baseImagePath = settings.baseImagePath;
    }
}
