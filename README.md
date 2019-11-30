<p align="center">
  <a href="https://github.com/dtopuzov/automator"><img alt="CI Status" src="https://github.com/dtopuzov/automator/workflows/CI/badge.svg"></a>
</p>

# The Automator
Java based automation framework for web, mobile, desktop and APIs.

**!!! Note: Work in progress !!!**

## Technology Stack
- [Junit5](https://github.com/junit-team/junit5) as unit testing framework.
- [Appium](https://github.com/appium/java-client) to drive mobile and web.
- [WebDriverManager](https://github.com/bonigarcia/webdrivermanager) to manage of Selenium WebDriver binaries.

## Samples
Sample tests are available in `test` package.

Details:
- [WebTests.md](src/test/java/web/WebTests.md)

### Execute Test
```
./gradlew clean test --tests "web.perf.*"
```

### Run Checkstyle
```
./gradlew clean checkstyleMain
./gradlew clean checkstyleTest
```