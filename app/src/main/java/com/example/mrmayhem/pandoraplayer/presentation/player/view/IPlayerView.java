package com.example.mrmayhem.pandoraplayer.presentation.player.view;

import com.example.mrmayhem.pandoraplayer.business.models.SongModel;
import com.example.mrmayhem.pandoraplayer.presentation.base.BaseView;

/**
 * Created by mrMayhem on 30.08.2017.
 */

public interface IPlayerView extends BaseView {
    void showSongInfo(SongModel model);

    void hidePauseButton();

    void hidePlayButton();
}
