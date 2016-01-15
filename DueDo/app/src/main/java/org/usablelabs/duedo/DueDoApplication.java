package org.usablelabs.duedo;

import android.app.Application;

import com.activeandroid.ActiveAndroid;

public class DueDoApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        ActiveAndroid.initialize(this);
    }
}
