package com.example.mrmayhem.pandoraplayer.business.playlist;

import com.example.mrmayhem.pandoraplayer.Dummy;
import com.example.mrmayhem.pandoraplayer.business.models.SongModel;
import com.example.mrmayhem.pandoraplayer.data.auth.IAuthRepository;
import com.example.mrmayhem.pandoraplayer.data.music.IMusicRepository;

import java.util.List;
import java.util.Map;

import io.reactivex.Single;

/**
 * Created by mrMayhem on 30.08.2017.
 */

public class PlaylistInteractor implements IPlaylistInteractor {
    private IMusicRepository musicRepository;
    private IAuthRepository authRepository;

    public PlaylistInteractor(IMusicRepository musicRepository,IAuthRepository authRepository) {
        this.musicRepository = musicRepository;
        this.authRepository = authRepository;
    }

    @Override
    public void logout() {
        authRepository.logout();

    }

    @Override
    public Single<List<SongModel>> getUserPlaylist() {
        return Single.just(Dummy.list);

    }
}
