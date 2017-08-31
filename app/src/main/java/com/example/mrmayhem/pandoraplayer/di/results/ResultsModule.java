package com.example.mrmayhem.pandoraplayer.di.results;

import android.content.SharedPreferences;

import com.example.mrmayhem.pandoraplayer.business.results.IResultsInteractor;
import com.example.mrmayhem.pandoraplayer.business.results.ResultsInteractor;
import com.example.mrmayhem.pandoraplayer.data.music.IMusicRepository;
import com.example.mrmayhem.pandoraplayer.presentation.results.presenter.IResultsPresenter;
import com.example.mrmayhem.pandoraplayer.presentation.results.presenter.ResultsPresenter;

import dagger.Module;
import dagger.Provides;

/**
 * Created by mrMayhem on 31.08.2017.
 */
@Module
public class ResultsModule {
    @Provides
    IResultsInteractor provideInteractor(IMusicRepository iMusicRepository) {
        return new ResultsInteractor(iMusicRepository);
    }

    @Provides
    IResultsPresenter providePresenter(IResultsInteractor iResultsInteractor) {
        return new ResultsPresenter(iResultsInteractor);
    }
}
