package com.example.mrmayhem.pandoraplayer.presentation.playlist.view;

import com.example.mrmayhem.pandoraplayer.business.models.SongModel;
import com.example.mrmayhem.pandoraplayer.presentation.base.BaseView;

import java.util.List;

/**
 * Created by mrMayhem on 30.08.2017.
 */

public interface IPlaylistView extends BaseView {

    void showResultsActivity(String query);

    void showLoginActivity();

    void showPlayerActivity(int position);

    void showPlaylist(List<SongModel> list);

    void showLoading();

    void hideLoading();

    void showEmptyList();
}
