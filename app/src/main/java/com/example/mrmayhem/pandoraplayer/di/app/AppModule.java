package com.example.mrmayhem.pandoraplayer.di.app;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;

import com.example.mrmayhem.pandoraplayer.PandoraApi;
import com.example.mrmayhem.pandoraplayer.data.LocalStorage;
import com.example.mrmayhem.pandoraplayer.data.auth.AuthRepository;
import com.example.mrmayhem.pandoraplayer.data.auth.IAuthRepository;
import com.example.mrmayhem.pandoraplayer.data.music.IMusicRepository;
import com.example.mrmayhem.pandoraplayer.data.music.MusicRepository;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by mrMayhem on 29.08.2017.
 */

@Module
public class AppModule {
    private final Context appContext;
    private SharedPreferences sharedPreferences;

    public AppModule(@NonNull Context context, @NonNull SharedPreferences sharedPreferences) {
        this.sharedPreferences = sharedPreferences;
        this.appContext = context;
    }

    @Provides
    @Singleton
    Context provideContext() {
        return appContext;
    }

    @Provides
    @Singleton
    SharedPreferences providePrefs() {
        return sharedPreferences;
    }

    @Provides
    @Singleton
    IAuthRepository provideAuthRepository(PandoraApi api, LocalStorage storage) {
        return new AuthRepository(api, storage);
    }

    @Provides
    @Singleton
    IMusicRepository provideMusicRepository(PandoraApi api) {
        return new MusicRepository(api);
    }

    @Provides
    @Singleton
    LocalStorage provideStorage(Context context, SharedPreferences sharedPreferences) {
        return new LocalStorage(context, sharedPreferences);
    }

}