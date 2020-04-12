# Browsers & Drivers Knowledge Base

## Screen Size

Snippet for setting Firefox browser size:
```
FirefoxOptions firefoxOptions = new FirefoxOptions();
firefoxOptions.AddArguments("--headless");
firefoxOptions.AddArgument("--width=1920");
firefoxOptions.AddArgument("--height=1080");
FirefoxDriver firefoxDriver = new FirefoxDriver(firefoxOptions);
```
Please note that even with this options if you call `webDriver.Manage().Window.Maximize();` it will set browser size to `1366 x 768`.
This happens because headless mode use virtual display.

<p>To overwrite this behaviors you can set following variables:

```
export MOZ_HEADLESS_WIDTH=1920
export MOZ_HEADLESS_HEIGHT=1080
```

Resources:
- [Firefox in headless mode run in small size (1366, 768)](https://github.com/mozilla/geckodriver/issues/1354)

## Safari

Safari browser brings its own driver.

To enable it please read following article:
- [Configure Safari to Enable WebDriver Support](https://developer.apple.com/documentation/webkit/testing_with_webdriver_in_safari)

Please note that SafariDriver allows one session at a time, please see:
- [Safari Driver Official Docs](https://developer.apple.com/documentation/webkit/about_webdriver_for_safari)