package com.example.mrmayhem.pandoraplayer.di.player;

import com.example.mrmayhem.pandoraplayer.business.player.IPlayerInteractor;
import com.example.mrmayhem.pandoraplayer.business.player.PlayerInteractor;
import com.example.mrmayhem.pandoraplayer.data.auth.IAuthRepository;
import com.example.mrmayhem.pandoraplayer.data.music.IMusicRepository;
import com.example.mrmayhem.pandoraplayer.presentation.player.presenter.IPlayerPresenter;
import com.example.mrmayhem.pandoraplayer.presentation.player.presenter.PlayerPresenter;

import dagger.Module;
import dagger.Provides;

/**
 * Created by mrMayhem on 31.08.2017.
 */
@Module
public class PlayerModule {
    @Provides
    IPlayerInteractor provideInteractor(IMusicRepository iMusicRepository) {
        return new PlayerInteractor(iMusicRepository);
    }

    @Provides
    IPlayerPresenter providePresenter(IPlayerInteractor iPlayerInteractor) {
        return new PlayerPresenter(iPlayerInteractor);
    }
}
