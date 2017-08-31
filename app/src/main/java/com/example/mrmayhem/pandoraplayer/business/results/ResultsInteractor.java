package com.example.mrmayhem.pandoraplayer.business.results;

import com.example.mrmayhem.pandoraplayer.Dummy;
import com.example.mrmayhem.pandoraplayer.business.models.SongModel;
import com.example.mrmayhem.pandoraplayer.data.music.IMusicRepository;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Single;

/**
 * Created by mrMayhem on 31.08.2017.
 */

public class ResultsInteractor implements IResultsInteractor {
    private IMusicRepository repository;

    public ResultsInteractor(IMusicRepository repository) {
        this.repository = repository;
    }

    @Override
    public Single<List<SongModel>> getQueryResults(String query) {
        return Observable.fromIterable(Dummy.list)
                .filter(s -> s.getArtist().contains(query))
                .toList();

    }
}
