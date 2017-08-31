package com.example.mrmayhem.pandoraplayer.presentation.player.presenter;

import com.example.mrmayhem.pandoraplayer.business.player.IPlayerInteractor;
import com.example.mrmayhem.pandoraplayer.presentation.player.view.IPlayerView;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by mrMayhem on 30.08.2017.
 */

public class PlayerPresenter implements IPlayerPresenter {
    private CompositeDisposable disposables = new CompositeDisposable();
    private IPlayerInteractor interactor;
    private IPlayerView view;

    private int currentId = -1;
    private boolean isLoading = false;


    public PlayerPresenter(IPlayerInteractor interactor) {
        this.interactor = interactor;
    }

    @Override
    public void attachView(IPlayerView view) {
        this.view = view;
        onNextClick();
    }

    @Override
    public void detachView() {
        view = null;
        disposables.clear();
    }

    @Override
    public void onNextClick() {
        if (currentId < 9 && !isLoading) {
            Disposable disposable = interactor
                    .getSong(++currentId)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(model -> {
                                view.showSongInfo(model);
                            },
                            throwable -> {
                                view.showError(throwable.getMessage());
                            });
            disposables.add(disposable);
        }
    }

    @Override
    public void onPrevClick() {
        if (currentId > 0 && !isLoading) {
            Disposable disposable = interactor
                    .getSong(--currentId)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(model -> {
                                view.showSongInfo(model);
                            },
                            throwable -> {
                                view.showError(throwable.getMessage());
                            });
            disposables.add(disposable);
        }

    }


    @Override
    public void onPlayClick() {
            view.hidePlayButton();
    }

    @Override
    public void onPauseClick(){
        view.hidePauseButton();
    }

    @Override
    public void setSongId(int id) {
        currentId=id;
    }
}
