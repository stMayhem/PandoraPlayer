package com.example.mrmayhem.pandoraplayer.di.app;

import com.example.mrmayhem.pandoraplayer.di.auth.AuthComponent;
import com.example.mrmayhem.pandoraplayer.di.auth.AuthModule;
import com.example.mrmayhem.pandoraplayer.di.player.PlayerComponent;
import com.example.mrmayhem.pandoraplayer.di.player.PlayerModule;
import com.example.mrmayhem.pandoraplayer.di.playlist.PlaylistComponent;
import com.example.mrmayhem.pandoraplayer.di.playlist.PlaylistModule;
import com.example.mrmayhem.pandoraplayer.di.results.ResultsComponent;
import com.example.mrmayhem.pandoraplayer.di.results.ResultsModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by mrMayhem on 29.08.2017.
 */
@Component(modules = {AppModule.class, NetModule.class})
@Singleton
public interface AppComponent {
    AuthComponent plus(AuthModule authModule);
    PlaylistComponent plus(PlaylistModule playlistModule);
    ResultsComponent plus(ResultsModule resultsModule);
    PlayerComponent plus(PlayerModule resultsModule);
}
