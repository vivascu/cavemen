package com.cavemen.inception.events;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 * Created by eandreevici on 4/13/2014.
 */
public class FloorUpdatedReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d(FloorUpdatedReceiver.class.getSimpleName(), "update");
    }
}
