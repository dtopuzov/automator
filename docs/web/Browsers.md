# Browsers & Drivers Knowalge Base

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
This happens because headless mode use virual display.

<p>To overwrite this behavious you can set follwing variables:

```
export MOZ_HEADLESS_WIDTH=1920
export MOZ_HEADLESS_HEIGHT=1080
```

Resources:
- [Firefox in headless mode run in small size (1366, 768)](https://github.com/mozilla/geckodriver/issues/1354)