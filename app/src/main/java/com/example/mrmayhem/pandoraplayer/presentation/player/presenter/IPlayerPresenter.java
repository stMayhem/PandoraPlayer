package com.example.mrmayhem.pandoraplayer.presentation.player.presenter;

import com.example.mrmayhem.pandoraplayer.presentation.base.BasePresenter;
import com.example.mrmayhem.pandoraplayer.presentation.player.view.IPlayerView;

/**
 * Created by mrMayhem on 30.08.2017.
 */

public interface IPlayerPresenter extends BasePresenter<IPlayerView> {

    void onNextClick();

    void onPrevClick();

    void onPlayClick();

    void onPauseClick();

    void setSongId(int id);
}
