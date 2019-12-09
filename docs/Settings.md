# Test Settings

Test settings can be specified in properties files at test resources folder.

## Base Settings

**isDebug**
- Read Only!
- `true` if debugger is attached.

**wait**
- Set implicit wait of driver (in seconds).
- Examples: `30`
- Default: No default value (in this case implicit wait is not set).

## Web Settings

**browserType**
- Selenium [BrowserType](https://selenium.dev/selenium/docs/api/java/org/openqa/selenium/remote/BrowserType.html) enum value:
- Examples: `chrome`, `firefox`, `safari`, `MicrosoftEdge`, `iexplore`
- Default: `chrome`

**browserSize**
- Example: `1280x1024`
- Default: No default value (browser is maximized to current screen).

**baseUrl**
- Example: `https://www.google.com/ncr`
- Default: No default value (no url is opened before each test).

**headless**
- Example: `true` or `false`
- Default: `false`.