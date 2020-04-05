# Testing at BrowserStack

The automator framework supports testing web and mobile apps on [BrowserStack](https://www.browserstack.com/).

## Settings

Please visit [Capabilities Generator](https://www.browserstack.com/automate/capabilities) to get more details for capabilities supported by BrowserStack.

Then you can set following in your properites config file:

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

## SauceLabs Settings

**saucelabsUrl**
- Endpoint of SauceLabs data center.
- Example: `https://ondemand.saucelabs.com/wd/hub`
- Default: `https://ondemand.eu-central-1.saucelabs.com/wd/hub`

**platformName**
- Type of operating system.
- Example: `Windows 10`
- Default: No default value, please use [SauceLabs Platform Configurator](https://wiki.saucelabs.com/display/DOCS/Platform+Configurator#/) to check available versions.

**browserVersion**
- Version of browser.
- Example: `68.0`
- Default: No default value, please use [SauceLabs Platform Configurator](https://wiki.saucelabs.com/display/DOCS/Platform+Configurator#/) to check available versions.

**appiumVersion**
- Version of Appium server on SauceLabs cloud.
- Example: `1.15.0`
- Default: No default value, please use [SauceLabs Platform Configurator](https://wiki.saucelabs.com/display/DOCS/Platform+Configurator#/) to check available versions.

**sauceUserName**
- SauceLabs user name.
- Value can be set with `sauceUserName` key in properties file or in `SAUCE_USER_NAME` environment variable.
- Default: No default value.

**sauceAccessKey**
- SauceLabs access key.
- Value can be set with `sauceAccessKey` key in properties file or in `SAUCE_ACCESS_KEY` environment variable.
- Default: No default value.

SauceLabs settings that can be passed only as environment variables (with same name):
- `name` to set name of jobs at SauceLabs portal.
- `tags` can be used to filter results at SauceLabs portal.
- `build` build number of the run displayed at SauceLabs portal.
        
Please use [SauceLabs Platform Configurator](https://wiki.saucelabs.com/display/DOCS/Platform+Configurator#/) to check available devices and get capabilities.