package com.example.mrmayhem.pandoraplayer.di.auth;

import android.content.SharedPreferences;

import com.example.mrmayhem.pandoraplayer.business.auth.AuthInteractor;
import com.example.mrmayhem.pandoraplayer.business.auth.IAuthInteractor;
import com.example.mrmayhem.pandoraplayer.data.LocalStorage;
import com.example.mrmayhem.pandoraplayer.data.auth.IAuthRepository;
import com.example.mrmayhem.pandoraplayer.presentation.auth.presenter.AuthPresenter;
import com.example.mrmayhem.pandoraplayer.presentation.auth.presenter.IAuthPresenter;

import dagger.Module;
import dagger.Provides;

/**
 * Created by mrMayhem on 30.08.2017.
 */
@Module
public class AuthModule {
    @Provides
    IAuthInteractor provideInteractor(IAuthRepository iAuthRepository) {
        return new AuthInteractor(iAuthRepository);
    }

    @Provides
    IAuthPresenter providePresenter(IAuthInteractor iAuthInteractor) {
        return new AuthPresenter(iAuthInteractor);
    }
}
