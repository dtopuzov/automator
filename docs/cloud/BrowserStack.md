# Testing at BrowserStack

The automator framework supports testing web and mobile apps on [BrowserStack](https://www.browserstack.com/).

To mark test run as `BrowserStack` set following in properties file:
```
environmentType=BROWSERSTACK
```

Then you can use standard properties for local web testing such as:
```
baseUrl=https://github.com/
defaultWait=10
headless=true
browserSize=1280x1024
```

In addition you can set following browser stack specific options in `.properties` files:
```
browserstack.browserName=Chrome
browserstack.browserVersion=80.0
browserstack.os=Windows
browserstack.osVersion=10
browserstack.resolution=1920x1080
browserstack.projectName=GitHub UI Tests
browserstack.sessionName=SmokeTests
browserstack.buildName=release-1.0.0
browserstack.local=false
browserstack.debug=false
browserstack.consoleLogs=warnings
browserstack.timezone=BG
browserstack.seleniumVersion=3.141.59
```

Please visit [Capabilities Generator](https://www.browserstack.com/automate/capabilities) to get more details for capabilities supported by BrowserStack.

In short the idea is:
- Get config from the link above, prefix it with `browserstack.` and add `=<value>`.

Please note that to establish [BrowserStack](https://www.browserstack.com/) session you need to specify your credentials in following environment variables: 
- `BROWSER_STACK_USER` for your user name.
- `BROWSER_STACK_KEY` for your access key (see your [Account Settings](https://www.browserstack.com/accounts/settings) for more details).