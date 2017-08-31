package com.example.mrmayhem.pandoraplayer.di.playlist;

import com.example.mrmayhem.pandoraplayer.business.playlist.IPlaylistInteractor;
import com.example.mrmayhem.pandoraplayer.business.playlist.PlaylistInteractor;
import com.example.mrmayhem.pandoraplayer.data.auth.IAuthRepository;
import com.example.mrmayhem.pandoraplayer.data.music.IMusicRepository;
import com.example.mrmayhem.pandoraplayer.presentation.playlist.presenter.IPlaylistPresenter;
import com.example.mrmayhem.pandoraplayer.presentation.playlist.presenter.PlaylistPresenter;

import dagger.Module;
import dagger.Provides;

/**
 * Created by mrMayhem on 30.08.2017.
 */

@Module
public class PlaylistModule {
    @Provides
    IPlaylistInteractor provideInteractor(IMusicRepository iMusicRepository, IAuthRepository authRepository) {
        return new PlaylistInteractor(iMusicRepository, authRepository);
    }

    @Provides
    IPlaylistPresenter providePresenter(IPlaylistInteractor iPlaylistInteractor) {
        return new PlaylistPresenter(iPlaylistInteractor);
    }
}