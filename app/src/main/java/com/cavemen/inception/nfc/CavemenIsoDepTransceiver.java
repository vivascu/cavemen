package com.cavemen.inception.nfc;

import android.nfc.tech.IsoDep;

import java.io.IOException;

/**
 * A runnable that implements the process of exchanging messages between an NFC reader and an NFC
 * tag.
 *
 * @author EgorAnd
 */
public class CavemenIsoDepTransceiver implements Runnable {

    private static final byte[] SELECT_AID_APDU = {0x00, (byte) 0xA4, 0x04, 0x00};
    private static final byte[] AID_CAVEMEN = {(byte) 0xF0, 0x01, 0x02, 0x03, 0x04, 0x05, 0x06};

    private final IsoDep isoDep;

    private final OnMessageExchangeListener listener;

    public CavemenIsoDepTransceiver(IsoDep isoDep, OnMessageExchangeListener listener) {
        this.isoDep = isoDep;
        this.listener = listener;
    }

    private byte[] createSelectAidApdu(byte[] aid) {
        byte[] result = new byte[6 + aid.length];
        System.arraycopy(SELECT_AID_APDU, 0, result, 0, SELECT_AID_APDU.length);
        result[4] = (byte) aid.length;
        System.arraycopy(aid, 0, result, 5, aid.length);
        result[result.length - 1] = 0;
        return result;
    }

    @Override
    public void run() {
        try {
            isoDep.connect();
            isoDep.transceive(createSelectAidApdu(AID_CAVEMEN));
            byte[] message = "Here's your message".getBytes();
            listener.onMessageSent(message);
            listener.onMessageReceived(isoDep.transceive(message));
            isoDep.close();
        } catch (IOException e) {
            listener.onError(e);
        }
    }

    /**
     * Callback interface.
     */
    public interface OnMessageExchangeListener {

        /**
         * Called when a message is sent to the tag.
         *
         * @param message A message in bytes.
         */
        void onMessageSent(byte[] message);

        /**
         * Called when a message from the tag arrives.
         *
         * @param message A message in bytes.
         */
        void onMessageReceived(byte[] message);

        /**
         * Called if any error occurs.
         *
         * @param exception An exception.
         */
        void onError(Exception exception);
    }
}
