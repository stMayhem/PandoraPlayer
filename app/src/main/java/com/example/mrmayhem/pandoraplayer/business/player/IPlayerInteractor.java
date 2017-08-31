package com.example.mrmayhem.pandoraplayer.business.player;

import com.example.mrmayhem.pandoraplayer.business.models.SongModel;

import io.reactivex.Single;

/**
 * Created by mrMayhem on 31.08.2017.
 */

public interface IPlayerInteractor {
    Single<SongModel> getSong(int currentId);
    Single<SongModel> playSong(boolean stop);
}
