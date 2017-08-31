package com.example.mrmayhem.pandoraplayer.presentation.auth.presenter;

import android.util.Log;

import com.example.mrmayhem.pandoraplayer.business.auth.IAuthInteractor;
import com.example.mrmayhem.pandoraplayer.presentation.auth.view.IAuthView;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by mrMayhem on 30.08.2017.
 */

public class AuthPresenter implements IAuthPresenter {
    public static final String TAG = AuthPresenter.class.getSimpleName();


    private CompositeDisposable disposables = new CompositeDisposable();
    private IAuthInteractor interactor;
    private IAuthView view;

    public AuthPresenter(IAuthInteractor interactor) {
        this.interactor = interactor;
    }


    @Override
    public void attachView(IAuthView view) {
        this.view = view;
        String partnerAuthToken = interactor.getPartnerModel().getPartnerAuthToken();
        if (partnerAuthToken.equals("")) {
            authPartner();
        } else {
          //  view.showPlaylistActivity();
          //  Log.d(TAG, "attachView: " + token);
        }
    }
    @Override
    public void authUser(String email, String password) {
        view.showLoading();
        Disposable disposable = interactor
                .authUser()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(model -> {
                            view.hideLoading();
                            view.showPlaylistActivity();
                            Log.d(TAG, "suces: " + model.toString());
                        },
                        throwable -> {
                            view.showError(throwable.getMessage());
                            Log.d(TAG, "throwable: " + throwable.getMessage());
                        });
        disposables.add(disposable);
    }

    @Override
    public void detachView() {
        disposables.clear();
        view = null;
    }

    @Override
    public void authPartner() {
        Disposable disposable = interactor
                .authPartner()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(model -> {
                            interactor.savePartnerModel(model);
                            Log.d(TAG, "suces: " + model.toString());
                        },
                        throwable -> {
                            Log.d(TAG, "throwable: " + throwable.getMessage());
                        });
        disposables.add(disposable);
    }
}
