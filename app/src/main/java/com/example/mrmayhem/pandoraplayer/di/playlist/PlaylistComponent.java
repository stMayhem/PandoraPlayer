package com.example.mrmayhem.pandoraplayer.di.playlist;

import com.example.mrmayhem.pandoraplayer.presentation.playlist.view.PlaylistActivity;

import dagger.Subcomponent;

/**
 * Created by mrMayhem on 30.08.2017.
 */
@Subcomponent(modules = {PlaylistModule.class})
public interface PlaylistComponent {
    void inject(PlaylistActivity playlistActivity);
}
