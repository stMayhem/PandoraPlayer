package com.example.mrmayhem.pandoraplayer.presentation.auth.presenter;

import com.example.mrmayhem.pandoraplayer.presentation.auth.view.IAuthView;
import com.example.mrmayhem.pandoraplayer.presentation.base.BasePresenter;

/**
 * Created by mrMayhem on 30.08.2017.
 */

public interface IAuthPresenter extends BasePresenter<IAuthView> {

    void authPartner();

    void authUser(String email, String password);
}
