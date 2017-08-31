package com.example.mrmayhem.pandoraplayer.di.player;

import com.example.mrmayhem.pandoraplayer.presentation.player.view.PlayerActivity;

import dagger.Subcomponent;

/**
 * Created by mrMayhem on 31.08.2017.
 */

@Subcomponent(modules = {PlayerModule.class})
public interface PlayerComponent {
    void inject(PlayerActivity playerActivity);
}
