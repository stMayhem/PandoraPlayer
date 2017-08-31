package com.example.mrmayhem.pandoraplayer.data.music;

import com.example.mrmayhem.pandoraplayer.business.models.SongModel;
import com.google.gson.JsonObject;

import io.reactivex.Single;

/**
 * Created by mrMayhem on 30.08.2017.
 */

public interface IMusicRepository  {
    Single<JsonObject> getUserPlayList(JsonObject jsonObject);

    Single<SongModel> getSong(int id);
}
