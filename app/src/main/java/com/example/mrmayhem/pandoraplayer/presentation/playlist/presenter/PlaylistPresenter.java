package com.example.mrmayhem.pandoraplayer.presentation.playlist.presenter;

import android.util.Log;

import com.example.mrmayhem.pandoraplayer.business.models.SongModel;
import com.example.mrmayhem.pandoraplayer.business.playlist.IPlaylistInteractor;
import com.example.mrmayhem.pandoraplayer.presentation.playlist.view.IPlaylistView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by mrMayhem on 30.08.2017.
 */

public class PlaylistPresenter implements IPlaylistPresenter {
    public static final String TAG = PlaylistPresenter.class.getSimpleName();

    private CompositeDisposable disposables = new CompositeDisposable();
    private IPlaylistInteractor interactor;
    private IPlaylistView view;

    private List<SongModel> values = new ArrayList<>();

    public PlaylistPresenter(IPlaylistInteractor interactor) {
        this.interactor = interactor;
    }

    @Override
    public void attachView(IPlaylistView view) {
        this.view = view;
       getUserPlaylist();
    }


    private void getUserPlaylist() {
        view.showLoading();
        Disposable disposable = interactor
                .getUserPlaylist()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(list -> {
                            view.hideLoading();
                           // map = new HashMap<>(list);
                            values.addAll(list);

                            if (list.size() > 0) view.showPlaylist(list);
                            else view.showEmptyList();
                        },
                        throwable -> {
                            view.hideLoading();
                            view.showError(throwable.getMessage());
                            Log.d(TAG, "throwable: " + throwable.getMessage());
                        });
        disposables.add(disposable);
    }

    @Override
    public void detachView() {
        disposables.clear();
        view=null;
    }

    @Override
    public void logout() {
        interactor.logout();
        view.showLoginActivity();
    }

    @Override
    public void onListItemClick(int position) {
        values.get(position);
        view.showPlayerActivity(position);
    }
}
