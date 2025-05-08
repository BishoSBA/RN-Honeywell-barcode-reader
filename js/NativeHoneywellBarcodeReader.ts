import type { TurboModule } from "react-native/Libraries/TurboModule/RCTExport";
import { TurboModuleRegistry } from "react-native";

export interface Spec extends TurboModule {
  startReader(): Promise<boolean>;
  stopReader(): Promise<void>;
  setReaderProperty(propName: string, value: string | number | boolean): void;
  getConstants(): {
    BARCODE_READ_SUCCESS: string;
    BARCODE_READ_FAIL: string;
    BRAND: string;
    isCompatible: boolean;
  };
}

export default TurboModuleRegistry.getEnforcing<Spec>("HoneywellBarcodeReader");
