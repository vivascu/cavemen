package com.cavemen.inception;

import android.app.Application;

import com.parse.Parse;

/**
 * Created by eandreevici on 4/12/2014.
 */
public class CavemenApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Parse.initialize(this, "X7woT6Y1lWzo9LWvVGTLalqEyHueHiz10XP5iwaa", "2nDd05km9XcNmHZShR7GZNOkpgOsOFs7CVwk7LGe");
    }
}
