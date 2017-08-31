package com.example.mrmayhem.pandoraplayer.data.auth;

import android.util.Log;

import com.example.mrmayhem.pandoraplayer.PandoraApi;
import com.example.mrmayhem.pandoraplayer.business.models.PartnerAuthModel;
import com.example.mrmayhem.pandoraplayer.data.LocalStorage;
import com.google.gson.JsonObject;

import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by mrMayhem on 30.08.2017.
 */

public class AuthRepository implements IAuthRepository {
    private PandoraApi api;
    private LocalStorage storage;

    public AuthRepository(PandoraApi api, LocalStorage storage) {
        this.api = api;
        this.storage = storage;
    }

    @Override
    public Single<JsonObject> authPartner(JsonObject jsonObject) {
        return api.authPartner(jsonObject);
    }
    @Override
    public Single<JsonObject> authUser(String jsonObject, int partnerId,String authToken) {
        return api.authUser(jsonObject,partnerId,authToken);
    }

    @Override
    public void savePartnerModel(PartnerAuthModel model) {
        storage.savePartnerModelLocal(model);
    }

    @Override
    public PartnerAuthModel getPartnerModel() {
        return storage.getPartnerModel();
    }

    @Override
    public void logout() {
        storage.clearStorage();
    }


}
