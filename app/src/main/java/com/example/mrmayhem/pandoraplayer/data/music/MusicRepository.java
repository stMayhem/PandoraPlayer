package com.example.mrmayhem.pandoraplayer.data.music;

import com.example.mrmayhem.pandoraplayer.Dummy;
import com.example.mrmayhem.pandoraplayer.PandoraApi;
import com.example.mrmayhem.pandoraplayer.business.models.SongModel;
import com.google.gson.JsonObject;

import io.reactivex.Single;

/**
 * Created by mrMayhem on 30.08.2017.
 */

public class MusicRepository implements IMusicRepository {
    private PandoraApi api;

    public MusicRepository(PandoraApi api) {
        this.api = api;
    }

    @Override
    public Single<JsonObject> getUserPlayList(JsonObject jsonObject) {
        return null;
    }

    @Override
    public Single<SongModel> getSong(int id) {
        return Single.just(Dummy.songModels.get(id));
    }

}
