package com.example.mrmayhem.pandoraplayer.business.auth;

import com.example.mrmayhem.pandoraplayer.business.models.PartnerAuthModel;

import io.reactivex.Single;

/**
 * Created by mrMayhem on 30.08.2017.
 */

public interface IAuthInteractor {
    Single<PartnerAuthModel> authPartner();

    void savePartnerModel(PartnerAuthModel model);

    PartnerAuthModel getPartnerModel();

    Single<Object> authUser();
}
