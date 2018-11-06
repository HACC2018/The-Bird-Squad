package com.birdsquad.kumu;

import android.app.Application;
import android.util.Log;

public class KumuApp extends Application {

    public static String URLToServerPostForms = "192.168.2.2:8000/api";

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
        singleton = this;
        appStorage = new Storage();
    }
}
