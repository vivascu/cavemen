package com.cavemen.inception;

import android.app.Application;

import com.parse.Parse;
import com.parse.ParseObject;

/**
 * Main application class.
 * <p/>
 * Created by eandreevici on 4/12/2014.
 */
public class CavemenApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Parse.initialize(this, "tCnfD28oNLmp61ZpwQebIWgQfkyR4ReNmyWpT8Mt", "nUiSuYHKfwvBev3igpBYlUq1TAbrcBBbn5u9dW2w");

        ParseObject testObject = new ParseObject("TestObject");
        testObject.put("foo", "bar");
        testObject.saveInBackground();
    }
}
