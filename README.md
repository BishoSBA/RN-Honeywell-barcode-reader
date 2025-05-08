# React Native Honeywell Barcode Reader

This package works with Honeywell devices that have an integrated barcode scanner, like the Honeywell EDA50K and EDA51 (tested).

## Features

- **New Architecture Only**: Built exclusively for React Native's new architecture (Fabric/TurboModule)
- Fixed the issue where scan events would fire twice
- Modern TypeScript support
- Optimized for React Native 0.72.0+

> **Note**: This package requires React Native's New Architecture to be enabled in your project.

This version is a fork from https://github.com/duytq94/react-native-honeywell-barcode-reader

## Installation

```
npm i react-native-honeywell
```

## Requirements

This package requires React Native's New Architecture to be enabled in your project:

1. In `android/gradle.properties`, ensure these properties are set:
   ```
   newArchEnabled=true
   reactEnabled=true
   hermesEnabled=true
   ```

2. In your app's `babel.config.js`, ensure you have:
   ```js
   module.exports = {
     presets: ['module:@react-native/babel-preset'],
   };
   ```

## Installation

Modern React Native versions (0.72+) will automatically link the native modules
```

## Link manually (recommend):

1. In `app\build.gradle` add

```
implementation project(':react-native-honeywell')
```

2. In `settings.gradle` add

```
include ':react-native-honeywell'
project(':react-native-honeywell').projectDir = new File(rootProject.projectDir, '../node_modules/react-native-honeywell/android')
```

3. In `MainApplication.java`

Add this line to import package

```
import com.duytq94.HoneywellBarcodeReader.HoneywellBarcodeReaderPackage;
```

and add this line to getPackages()

```
new HoneywellBarcodeReaderPackage()
```

## Usage

First you'll want to check whether the device is a Honeywell scanner:

```js
import HoneywellBarcodeReader from "react-native-honeywell";

HoneywellBarcodeReader.isCompatible; // true or false
```

The barcode reader needs to be "claimed" by your application; meanwhile no other application can use it. You can do that like this:

```js
HoneywellBarcodeReader.startReader().then((claimed) => {
  console.log(claimed ? "Barcode reader is claimed" : "Barcode reader is busy");
});
```

To get events from the barcode scanner:

```js
HoneywellBarcodeReader.onBarcodeReadSuccess((event) => {
  console.log("Received data", event);
});

HoneywellBarcodeReader.onBarcodeReadFail(() => {
  console.log("Barcode read failed");
});
```

To free the claim and stop the reader, also freeing up resources:

```js
HoneywellBarcodeReader.stopReader().then(() => {
  console.log("Freedom!");
});
```

To stop receiving events:

```js
HoneywellBarcodeReader.offBarcodeReadSuccess();
HoneywellBarcodeReader.offBarcodeReadFail();
```
