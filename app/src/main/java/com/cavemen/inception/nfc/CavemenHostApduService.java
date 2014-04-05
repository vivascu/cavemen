package com.cavemen.inception.nfc;

import android.annotation.TargetApi;
import android.nfc.cardemulation.HostApduService;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;

import java.util.Arrays;

/**
 * A background service which enables the device to act as an NFC tag. The device can receive APDU
 * commands and return the commands of its own.
 *
 * @author EgorAnd
 */
@TargetApi(Build.VERSION_CODES.KITKAT)
public class CavemenHostApduService extends HostApduService {

    private static final String TAG = CavemenHostApduService.class.getSimpleName();

    @Override
    public byte[] processCommandApdu(byte[] commandApdu, Bundle extras) {
        Log.d(TAG, "Received message: " + Arrays.toString(commandApdu));
        return "Roger that".getBytes();
    }

    @Override
    public void onDeactivated(int reason) {
        Log.d(TAG, "Deactivated, reason: " + reason);
    }
}
