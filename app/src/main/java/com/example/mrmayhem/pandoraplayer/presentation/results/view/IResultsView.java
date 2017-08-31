package com.example.mrmayhem.pandoraplayer.presentation.results.view;

import com.example.mrmayhem.pandoraplayer.business.models.SongModel;
import com.example.mrmayhem.pandoraplayer.presentation.base.BaseView;

import java.util.List;

/**
 * Created by mrMayhem on 31.08.2017.
 */

public interface IResultsView extends BaseView {
    void showQueryResults(List<SongModel>  list);

    void showPlayerActivity();

    void showLoading();

    void hideLoading();

    void showEmptyList();
}
