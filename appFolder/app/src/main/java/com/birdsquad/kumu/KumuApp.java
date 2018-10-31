package com.birdsquad.kumu;

import android.app.Application;

public class KumuApp extends Application {

    private static KumuApp singleton;
    private static Storage appStorage;

    public static KumuApp getInstance() {
        return singleton;
    }

    public static Storage getAppStorage() {
        return appStorage;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        this.appStorage = new Storage();
        singleton = this;
    }
}
