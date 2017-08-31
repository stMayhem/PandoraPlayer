package com.example.mrmayhem.pandoraplayer.business.results;

import com.example.mrmayhem.pandoraplayer.business.models.SongModel;

import java.util.List;
import java.util.Queue;

import io.reactivex.Single;

/**
 * Created by mrMayhem on 31.08.2017.
 */

public interface IResultsInteractor {
    Single<List<SongModel>> getQueryResults(String query);
}
