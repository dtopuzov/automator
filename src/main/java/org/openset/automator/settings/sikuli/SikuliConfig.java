package org.openset.automator.settings.sikuli;

import org.openset.automator.settings.base.BaseSettings;

import java.io.File;
import java.util.Properties;

public class SikuliConfig {
    public Float defaultSimilarity;
    public String baseImagePath;

    public SikuliConfig(BaseSettings settings) {
        Properties properties = settings.properties;
        this.defaultSimilarity = Float.valueOf(properties.getProperty("defaultSimilarity", "0.9D"));
        this.baseImagePath = properties.getProperty("baseImagePath", "").replace(".", File.separator);
    }

}
