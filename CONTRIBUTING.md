# Contributing

There are a lot of different ways to contribute to Automator project.

### Make changes to Automator code or docs

Fork the project, make a change, and send a pull request!

Please make sure:
1. Checkstyle passes
```
./gradlew clean checkstyleMain
./gradlew clean checkstyleTest
```
2. Unit and functional tests pass before sending a pull request
```
./gradlew clean test --tests "unit.*"
./gradlew clean test --tests "e2e.*"
```

### Submit bug reports or feature requests

Just use the GitHub issue tracker to submit your bug reports and feature requests.
If you are submitting a bug report, please follow the [issue template](https://github.com/dtopuzov/automator/issues/new).
