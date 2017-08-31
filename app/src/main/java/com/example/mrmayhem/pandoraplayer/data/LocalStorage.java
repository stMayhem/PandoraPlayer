package com.example.mrmayhem.pandoraplayer.data;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.mrmayhem.pandoraplayer.business.models.PartnerAuthModel;
import com.example.mrmayhem.pandoraplayer.utils.Constants;

/**
 * Created by mrMayhem on 31.08.2017.
 */

public class LocalStorage {
    private Context context;
    private SharedPreferences sharedPreferences;

    public LocalStorage(Context context, SharedPreferences sharedPreferences) {
        this.context = context;
        this.sharedPreferences = sharedPreferences;
    }

    public void savePartnerModelLocal(PartnerAuthModel model) {
        sharedPreferences.edit()
                .putInt(Constants.PARTNER_ID, model.getPartnerId())
                .putString(Constants.PARTNER_AUTH_TOKEN, model.getPartnerAuthToken())
                .putString(Constants.SYNC_TIME, model.getSyncTime())
                .putInt(Constants.PARTNER_LOGIN_TIME, model.getPartnerLoginTime())
                .putInt(Constants.DECODE_SYNC_TIME, model.getDecodeSyncTime())
                .apply();
    }
    public PartnerAuthModel getPartnerModel() {
        int partnerId = sharedPreferences.getInt(Constants.PARTNER_ID, 0);
        String partnerToken = sharedPreferences.getString(Constants.PARTNER_AUTH_TOKEN, "");
        String syncTime = sharedPreferences.getString(Constants.SYNC_TIME, "");
        int partnerLoginTime = sharedPreferences.getInt(Constants.PARTNER_LOGIN_TIME, 0);
        int decodeSyncTime = sharedPreferences.getInt(Constants.DECODE_SYNC_TIME, 0);

        PartnerAuthModel partnerAuthModel = new PartnerAuthModel();
        partnerAuthModel.setPartnerId(partnerId);
        partnerAuthModel.setPartnerAuthToken(partnerToken);
        partnerAuthModel.setSyncTime(syncTime);
        partnerAuthModel.setPartnerLoginTime(partnerLoginTime);
        partnerAuthModel.setDecodeSyncTime(decodeSyncTime);
        return partnerAuthModel;
    }

    public void clearStorage() {
        sharedPreferences.edit()
                .remove(Constants.PARTNER_ID)
                .remove(Constants.PARTNER_AUTH_TOKEN)
                .remove(Constants.SYNC_TIME)
                .apply();
    }
}
