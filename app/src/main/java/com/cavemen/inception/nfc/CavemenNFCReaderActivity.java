package com.cavemen.inception.nfc;

import android.annotation.TargetApi;
import android.app.Activity;
import android.nfc.NfcAdapter;
import android.nfc.Tag;
import android.nfc.tech.IsoDep;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.cavemen.inception.R;

/**
 * An Activity for the NFC reader functionality. This Activity will start a transceiver in background
 * in order to recognize NFC tags and make a simple message exchange.
 *
 * @author EgorAnd
 */
@TargetApi(Build.VERSION_CODES.KITKAT)
public class CavemenNFCReaderActivity extends Activity implements CavemenIsoDepTransceiver.OnMessageExchangeListener,
        NfcAdapter.ReaderCallback {

    private static final String TAG = CavemenNFCReaderActivity.class.getSimpleName();

    private NfcAdapter nfcAdapter;

    private TextView messageConsole;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nfc_reader);
        messageConsole = (TextView) findViewById(R.id.console);

        nfcAdapter = NfcAdapter.getDefaultAdapter(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "enabling reader mode");
        nfcAdapter.enableReaderMode(this, this, NfcAdapter.FLAG_READER_NFC_A | NfcAdapter.FLAG_READER_SKIP_NDEF_CHECK, null);
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "disabling reader mode");
        nfcAdapter.disableReaderMode(this);
    }

    @Override
    public void onTagDiscovered(Tag tag) {
        Log.d(TAG, "tag discovered");
        IsoDep isoDep = IsoDep.get(tag);
        CavemenIsoDepTransceiver transceiver = new CavemenIsoDepTransceiver(isoDep, this);
        Thread thread = new Thread(transceiver);
        thread.start();
    }

    @Override
    public void onMessageSent(final byte[] message) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                messageConsole.setText("Message sent: " + new String(message));
            }
        });
    }

    @Override
    public void onMessageReceived(final byte[] message) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                messageConsole.setText("Message received: " + new String(message));
            }
        });
    }

    @Override
    public void onError(final Exception exception) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                messageConsole.setText("Error occurred: " + exception.getLocalizedMessage());
            }
        });
    }
}
