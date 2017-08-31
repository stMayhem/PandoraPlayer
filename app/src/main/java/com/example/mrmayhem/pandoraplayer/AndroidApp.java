package com.example.mrmayhem.pandoraplayer;

import android.app.Application;
import android.content.Context;
import android.support.annotation.NonNull;

import com.example.mrmayhem.pandoraplayer.di.app.AppComponent;
import com.example.mrmayhem.pandoraplayer.di.app.AppModule;
import com.example.mrmayhem.pandoraplayer.di.app.DaggerAppComponent;
import com.example.mrmayhem.pandoraplayer.utils.Constants;


/**
 * Created by mrMayhem on 29.08.2017.
 */

public class AndroidApp extends Application {
    private AppComponent appComponent;


    @NonNull
    public static AndroidApp get(@NonNull Context context) {
        return (AndroidApp) context.getApplicationContext();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        appComponent = prepareAppComponent().build();
    }

    @NonNull
    private DaggerAppComponent.Builder prepareAppComponent() {
        return DaggerAppComponent.builder()
                .appModule(new AppModule(this, getSharedPreferences(Constants.APP_PREFERENCES, Context.MODE_PRIVATE)));
    }

    @NonNull
    public AppComponent applicationComponent() {
        return appComponent;
    }

}
