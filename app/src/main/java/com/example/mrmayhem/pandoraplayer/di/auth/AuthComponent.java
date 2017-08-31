package com.example.mrmayhem.pandoraplayer.di.auth;

import com.example.mrmayhem.pandoraplayer.presentation.auth.view.AuthActivity;

import dagger.Subcomponent;

/**
 * Created by mrMayhem on 30.08.2017.
 */
@Subcomponent(modules = {AuthModule.class})
public interface AuthComponent {
    void inject(AuthActivity authActivity);
}
