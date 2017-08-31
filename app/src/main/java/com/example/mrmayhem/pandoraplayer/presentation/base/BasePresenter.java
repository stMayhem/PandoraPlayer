package com.example.mrmayhem.pandoraplayer.presentation.base;

/**
 * Created by mrMayhem on 30.08.2017.
 */

public interface BasePresenter<T extends BaseView> {
    void attachView(T view);

    void detachView();
}
