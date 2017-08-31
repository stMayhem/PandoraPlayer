package com.example.mrmayhem.pandoraplayer.presentation.auth.view;

import com.example.mrmayhem.pandoraplayer.presentation.base.BaseView;

/**
 * Created by mrMayhem on 30.08.2017.
 */

public interface IAuthView extends BaseView {
    void showPlaylistActivity();

    void showLoading();

    void hideLoading();
}
