package com.example.mrmayhem.pandoraplayer.presentation.results.presenter;

import android.util.Log;

import com.example.mrmayhem.pandoraplayer.business.results.IResultsInteractor;
import com.example.mrmayhem.pandoraplayer.presentation.results.view.IResultsView;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by mrMayhem on 31.08.2017.
 */

public class ResultsPresenter implements IResultsPresenter {
    public static final String TAG = ResultsPresenter.class.getSimpleName();

    private CompositeDisposable disposables = new CompositeDisposable();
    private IResultsInteractor interactor;
    private IResultsView view;

    private String query;
    public ResultsPresenter(IResultsInteractor interactor) {
        this.interactor = interactor;

    }

    @Override
    public void attachView(IResultsView view) {
        this.view = view;
        getQueryResults(query);

    }

    private void getQueryResults(String query) {
       view.showLoading();
        Disposable disposable = interactor
                .getQueryResults(query)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(list -> {
                            view.hideLoading();
                            if (list.size() > 0) view.showQueryResults(list);
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
    public void setQuery(String query) {
        this.query=query;
    }

    @Override
    public void onListItemClick() {
        view.showPlayerActivity();
    }

    @Override
    public String getQuery() {
        return query;
    }
}
