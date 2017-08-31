package com.example.mrmayhem.pandoraplayer.business.player;

import com.example.mrmayhem.pandoraplayer.Dummy;
import com.example.mrmayhem.pandoraplayer.business.models.SongModel;
import com.example.mrmayhem.pandoraplayer.data.music.IMusicRepository;

import io.reactivex.Single;

/**
 * Created by mrMayhem on 31.08.2017.
 */

public class PlayerInteractor implements IPlayerInteractor{
    private IMusicRepository repository;

    public PlayerInteractor(IMusicRepository repository) {
        this.repository = repository;
    }

    @Override
    public Single<SongModel> getSong(int id) {
        return repository.getSong(id);

    }


    @Override
    public Single<SongModel> playSong(boolean stop) {
        return null;
    }
}
