package com.example.mrmayhem.pandoraplayer.di.results;

import com.example.mrmayhem.pandoraplayer.presentation.results.view.ResultsActivity;

import dagger.Subcomponent;

/**
 * Created by mrMayhem on 31.08.2017.
 */

@Subcomponent(modules = {ResultsModule.class})
public interface ResultsComponent {
    void inject(ResultsActivity resultActivity);
}
