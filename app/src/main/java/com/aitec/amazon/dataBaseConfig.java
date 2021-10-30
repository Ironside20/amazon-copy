package com.aitec.amazon;

import android.app.Application;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class dataBaseConfig extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        Realm.init(this);
        RealmConfiguration config = new RealmConfiguration.Builder().name("amazon.realm").build();
        Realm.setDefaultConfiguration(config);
    }
}
