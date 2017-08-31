package com.example.mrmayhem.pandoraplayer.presentation.playlist.presenter;

import com.example.mrmayhem.pandoraplayer.presentation.base.BasePresenter;
import com.example.mrmayhem.pandoraplayer.presentation.playlist.view.IPlaylistView;

/**
 * Created by mrMayhem on 30.08.2017.
 */

public interface IPlaylistPresenter extends BasePresenter<IPlaylistView> {
    void logout();

    void onListItemClick(int position);
}
