package com.duytq94.HoneywellBarcodeReader;

import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.turbomodule.core.interfaces.TurboModule;

/**
 * TurboModule interface for HoneywellBarcodeReader
 */
public abstract class NativeHoneywellBarcodeReaderSpec extends ReactContextBaseJavaModule implements TurboModule {
    
    public NativeHoneywellBarcodeReaderSpec(ReactApplicationContext reactContext) {
        super(reactContext);
    }

    @ReactMethod
    public abstract void startReader(Promise promise);

    @ReactMethod
    public abstract void stopReader(Promise promise);

    @ReactMethod
    public abstract void setReaderProperty(String propName, int value);

    @ReactMethod
    public abstract void setReaderProperty(String propName, boolean value);

    @ReactMethod
    public abstract void setReaderProperty(String propName, String value);
}
