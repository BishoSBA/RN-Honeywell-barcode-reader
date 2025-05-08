/**
 * React Native Honeywell Barcode Reader
 * @version 2.0.0
 * @requires React Native New Architecture (Fabric)
 */
declare module "react-native-honeywell" {
  interface HoneywellBarcodeReaderType {
    BARCODE_READ_SUCCESS: string;
    BARCODE_READ_FAIL: string;
    BRAND: string;
    isCompatible: boolean;

    startReader(): Promise<boolean>;
    stopReader(): Promise<void>;
    setReaderProperty(propName: string, value: boolean | number | string): void; // Fixed typo in method name
    onBarcodeReadSuccess(handler: (event: { data: string }) => void): void;
    onBarcodeReadFail(handler: () => void): void;
    offBarcodeReadSuccess(): void;
    offBarcodeReadFail(): void;
  }

  const HoneywellBarcodeReader: HoneywellBarcodeReaderType;
  export default HoneywellBarcodeReader;
}
