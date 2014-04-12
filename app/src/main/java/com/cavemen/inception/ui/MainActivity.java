package com.cavemen.inception.ui;

import android.content.Intent;
import android.view.View;

import com.cavemen.inception.R;
import com.cavemen.inception.nfc.CavemenNFCReaderActivity;

import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;

@EActivity(R.layout.activity_main)
public class MainActivity extends BaseActivity {


    @Click(R.id.startNfcButton)
    public void startNfcMode(View v) {
        startActivity(new Intent(this, CavemenNFCReaderActivity.class));
    }
}
