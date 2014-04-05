package com.cavemen.inception;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.cavemen.inception.nfc.CavemenNFCReaderActivity;


public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void startNfcMode(View v) {
        startActivity(new Intent(this, CavemenNFCReaderActivity.class));
    }
}
