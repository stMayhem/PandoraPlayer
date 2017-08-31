package com.example.mrmayhem.pandoraplayer.presentation.results.presenter;

import com.example.mrmayhem.pandoraplayer.presentation.base.BasePresenter;
import com.example.mrmayhem.pandoraplayer.presentation.results.view.IResultsView;

/**
 * Created by mrMayhem on 31.08.2017.
 */

public interface IResultsPresenter extends BasePresenter<IResultsView> {
    void setQuery(String query);

    void onListItemClick();

    String getQuery();
}
