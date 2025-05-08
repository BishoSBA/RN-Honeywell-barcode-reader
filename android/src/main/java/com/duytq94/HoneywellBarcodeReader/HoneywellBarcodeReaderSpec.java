package com.duytq94.HoneywellBarcodeReader;

import androidx.annotation.Nullable;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.module.annotations.ReactModule;

/**
 * Abstract base class for HoneywellBarcodeReader Native Module implementations using the TurboModule system.
 */
@ReactModule(name = HoneywellBarcodeReaderModule.NAME)
public abstract class HoneywellBarcodeReaderSpec extends NativeHoneywellBarcodeReaderSpec {
    public HoneywellBarcodeReaderSpec(ReactApplicationContext reactContext) {
        super(reactContext);
    }
}
