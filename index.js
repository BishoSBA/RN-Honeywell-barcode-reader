import { NativeEventEmitter, Platform } from "react-native";
import HoneywellBarcodeReader from "./js/NativeHoneywellBarcodeReader";

if (!HoneywellBarcodeReader) {
  console.warn("HoneywellBarcodeReader is not available on this platform");
}

const barcodeReaderEmitter = new NativeEventEmitter(HoneywellBarcodeReader);

let subscriptionBarcodeReadSuccess = null;
let subscriptionBarcodeReadFail = null;

const HoneywellBarcodeReaderModule = {
  ...HoneywellBarcodeReader,

  onBarcodeReadSuccess: (handler) => {
    subscriptionBarcodeReadSuccess?.remove();
    subscriptionBarcodeReadSuccess = null;
    subscriptionBarcodeReadSuccess = barcodeReaderEmitter.addListener(
      HoneywellBarcodeReader.BARCODE_READ_SUCCESS,
      handler
    );
  },

  onBarcodeReadFail: (handler) => {
    subscriptionBarcodeReadFail?.remove();
    subscriptionBarcodeReadFail = null;
    subscriptionBarcodeReadFail = barcodeReaderEmitter.addListener(
      HoneywellBarcodeReader.BARCODE_READ_FAIL,
      handler
    );
  },

  offBarcodeReadSuccess: () => {
    subscriptionBarcodeReadSuccess?.remove();
    subscriptionBarcodeReadSuccess = null;
  },

  offBarcodeReadFail: () => {
    subscriptionBarcodeReadFail?.remove();
    subscriptionBarcodeReadFail = null;
  },

  isCompatible: () => {
    return Platform.OS === "android" && HoneywellBarcodeReader?.isCompatible;
  },
  getBrand: () => {
    return HoneywellBarcodeReader?.BRAND;
  },
};

export default HoneywellBarcodeReaderModule;
