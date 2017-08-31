package com.example.mrmayhem.pandoraplayer.data.auth;

import com.example.mrmayhem.pandoraplayer.business.models.PartnerAuthModel;
import com.google.gson.JsonObject;

import io.reactivex.Single;

/**
 * Created by mrMayhem on 30.08.2017.
 */

public interface IAuthRepository {
    Single<JsonObject> authPartner(JsonObject jsonObject);

    Single<JsonObject> authUser(String jsonObject,int partnerId, String authToken);

    void savePartnerModel(PartnerAuthModel model);

    PartnerAuthModel getPartnerModel();

    void logout();
}
