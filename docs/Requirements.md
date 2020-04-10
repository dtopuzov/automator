# Requirements

## Java

[JDK 11](https://adoptopenjdk.net/?variant=openjdk11&jvmVariant=hotspot) or newer.

## Mobile Testing

### Appium
[Appium](http://appium.io/) Appium is an open source test automation framework for use with native, hybrid and mobile web apps.

Appium [java-client](https://github.com/appium/java-client/) is available in [The Automator](https://github.com/dtopuzov/automator) so no need to add it as dependency of your project.

This framework require latest stable version of Appium [server](https://www.npmjs.com/package/appium) need to be installed.

Notes:
- You can skip local Appium installation in case you run tests entirely on device clouds such as SauceLabs.

Tutorials:
- [Install Appium Server on Windows](https://www.edgewordstraining.co.uk/2017/07/05/install-appium-server-windows/)
- [Install Appium on macOS](https://www.swtestacademy.com/how-to-install-appium-on-mac/)

### Android Studio (or Android SDK)

In oder to execute tests on Android devices and emulators [Android Studio](https://developer.android.com/studio/install) is required.

Notes:
- For CI needs you can use [Android SDK](https://developer.android.com/studio#downloads) only (no need to install Android Studio GUI).

Tutorials:
- [Appium Tutorial](https://www.swtestacademy.com/appium-tutorial/) including Android Studio and emulator setup.

## Sikuli Based Testing

Sikuli OCR feature require `tesseract` (only on Linux and macOS).

Please read [official Sikuli docs](https://github.com/RaiMan/SikuliX1/wiki/macOS-Linux:-Support-libraries-for-Tess4J-Tesseract-4-OCR).
