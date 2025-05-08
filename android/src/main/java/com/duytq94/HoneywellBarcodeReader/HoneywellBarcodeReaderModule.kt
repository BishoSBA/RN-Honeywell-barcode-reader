package com.duytq94.HoneywellBarcodeReader

import android.os.Build
import android.util.Log
import com.facebook.react.bridge.*
import com.facebook.react.module.annotations.ReactModule
import com.facebook.react.modules.core.DeviceEventManagerModule
import com.honeywell.aidc.AidcManager
import com.honeywell.aidc.BarcodeFailureEvent
import com.honeywell.aidc.BarcodeReadEvent
import com.honeywell.aidc.BarcodeReader
import com.honeywell.aidc.ScannerUnavailableException

@ReactModule(name = HoneywellBarcodeReaderModule.NAME)
class HoneywellBarcodeReaderModule(reactContext: ReactApplicationContext) :
    HoneywellBarcodeReaderSpec(reactContext), BarcodeReader.BarcodeListener {

    companion object {
        const val NAME = "HoneywellBarcodeReader"
        private const val TAG = "HoneywellBarcodeReader"
        private const val D = true

        private const val BARCODE_READ_SUCCESS = "barcodeReadSuccess"
        private const val BARCODE_READ_FAIL = "barcodeReadFail"
    }

    private var manager: AidcManager? = null
    private var reader: BarcodeReader? = null
    private val mReactContext: ReactApplicationContext = reactContext

    init {
        // Log that we're using the new architecture
        Log.i(TAG, "HoneywellBarcodeReader initialized with NEW architecture (TurboModule)")
    }

    override fun getName(): String = NAME

    private fun sendEvent(eventName: String, params: WritableMap?) {
        if (mReactContext.hasActiveReactInstance()) {
            if (D) Log.d(TAG, "Sending event: $eventName")
            mReactContext
                .getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter::class.java)
                .emit(eventName, params)
        }
    }

    override fun onBarcodeEvent(barcodeReadEvent: BarcodeReadEvent) {
        if (D) Log.d(TAG, "HoneywellBarcodeReader - Barcode scan read")
        val params = Arguments.createMap().apply {
            putString("data", barcodeReadEvent.barcodeData)
        }
        sendEvent(BARCODE_READ_SUCCESS, params)
    }

    override fun onFailureEvent(barcodeFailureEvent: BarcodeFailureEvent) {
        if (D) Log.d(TAG, "HoneywellBarcodeReader - Barcode scan failed")
        sendEvent(BARCODE_READ_FAIL, null)
    }

    @ReactMethod
    override fun startReader(promise: Promise) {
        AidcManager.create(mReactContext) { aidcManager ->
            manager = aidcManager
            reader = manager?.createBarcodeReader()
            reader?.let { barcodeReader ->
                try {
                    barcodeReader.addBarcodeListener(this)

                    // Configure barcode reader properties using string constants
                    setReaderProperty("DEC_EAN13_ENABLED", true)
                    setReaderProperty("DEC_EAN8_CHECK_DIGIT_TRANSMIT", true)
                    setReaderProperty("DEC_EAN13_CHECK_DIGIT_TRANSMIT", true)
                    setReaderProperty("DEC_UPCA_ENABLE", true)
                    setReaderProperty("DEC_UPCA_CHECK_DIGIT_TRANSMIT", true)
                    setReaderProperty("DEC_UPCE_ENABLE", true)
                    setReaderProperty("DEC_UPCE_CHECK_DIGIT_TRANSMIT", true)
                    setReaderProperty("DEC_CODABAR_ENABLED", true)
                    setReaderProperty("DEC_CODABAR_CHECK_DIGIT_MODE", true)
                    setReaderProperty("DEC_I25_ENABLED", true)
                    setReaderProperty("DEC_I25_CHECK_DIGIT_MODE", true)

                    barcodeReader.claim()
                    promise.resolve(true)
                } catch (e: ScannerUnavailableException) {
                    promise.resolve(false)
                    e.printStackTrace()
                }
            }
        }
    }

    @ReactMethod
    override fun stopReader(promise: Promise) {
        reader?.let { barcodeReader ->
            try {
                barcodeReader.release()
                barcodeReader.close()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }

        manager?.let { aidcManager ->
            try {
                aidcManager.close()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }

        promise.resolve(null)
    }

    @ReactMethod
    override fun setReaderProperty(propName: String, value: Int) {
        try {
            reader?.setProperty(propName, value)
        } catch (e: Exception) {
            if (D) Log.e(TAG, "Error setting reader property: ${e.message}")
        }
    }

    @ReactMethod
    override fun setReaderProperty(propName: String, value: Boolean) {
        try {
            reader?.setProperty(propName, value)
        } catch (e: Exception) {
            if (D) Log.e(TAG, "Error setting reader property: ${e.message}")
        }
    }

    @ReactMethod
    override fun setReaderProperty(propName: String, value: String) {
        try {
            reader?.setProperty(propName, value)
        } catch (e: Exception) {
            if (D) Log.e(TAG, "Error setting reader property: ${e.message}")
        }
    }

    private fun isCompatible(): Boolean =
        Build.BRAND.lowercase().contains("honeywell")

    override fun getConstants(): Map<String, Any> = mapOf(
        BARCODE_READ_SUCCESS to BARCODE_READ_SUCCESS,
        BARCODE_READ_FAIL to BARCODE_READ_FAIL,
        "BRAND" to Build.BRAND,
        "isCompatible" to isCompatible()
    )
}
