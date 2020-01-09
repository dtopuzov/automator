# Getting Started

## Create project based on Automator framework
- TODO

## Create Tests
- TODO

## Test Settings

Some test (like WebTest) have default configs and will run out of the box.

Default configs are there to make test development workflow easier.

However for running tests on multiple configurations you will need to add custom test configurations and then pass it as parameter to test run.

Please note that config files are mandatory for some test types (like ElectronTest) because `automator` is not aware for some mandatory test settings such as path to application under test.

**Create config files**

Config file location:
```
$projectRoot/src/test/resources/<your-config-name>.properties
```

Sample web test config file content:
```
browserType=Chrome
browserSize=1280x1024
baseUrl=https://www.google.com/ncr
defaultWait=15
```
Please read [Settings.md](docs/Settings.md) for more details on test settings.

**Pass config files as test run parameter**

Gradle commandline params:
```
gradlew clean test --tests "web.*" -Dconfig=<your-config-name>
```

In order to specify configs in IDEs please add following in VM options:
```
-Dconfig=<your-config-name>
```

Notes: 
- Do NOT pass `.properties` extension.
- If properties are located in subfolder of `resources` folder then pass it like `subfolder/<your-config-name>`.

## Execute Tests & Reporting

Execute tests and generate [Allure](http://allure.qatools.ru/) reports:
```
gradlew clean test --tests "web.*" -Dconfig=<your-config-name> allureReport
```

For convenience you can also serve the report: 
```
gradlew clean test --tests "web.*" -Dconfig=<your-config-name> allureReport allureServe
```

Notes:
- Allure [plugin](https://plugins.jenkins.io/allure-jenkins-plugin) for [Jenkins](https://jenkins.io/) is available and it auto generate reports (no need to run `allureReport` or `allureServe` tasks).