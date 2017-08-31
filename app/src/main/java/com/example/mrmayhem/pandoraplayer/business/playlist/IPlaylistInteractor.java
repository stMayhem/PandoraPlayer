package com.example.mrmayhem.pandoraplayer.business.playlist;

import com.example.mrmayhem.pandoraplayer.business.models.SongModel;

import java.util.List;
import java.util.Map;

import io.reactivex.Single;

/**
 * Created by mrMayhem on 30.08.2017.
 */

public interface IPlaylistInteractor {
    void logout();

    Single<List<SongModel>> getUserPlaylist();
}
