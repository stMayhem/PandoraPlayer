package com.example.mrmayhem.pandoraplayer.business.models;

/**
 * Created by mrMayhem on 30.08.2017.
 */

public class PartnerAuthModel {
    private int partnerId;
    private String partnerAuthToken;
    private String syncTime;
    private int partnerLoginTime;
    private int decodeSyncTime;


    public int getPartnerId() {
        return partnerId;
    }

    public void setPartnerId(int partnerId) {
        this.partnerId = partnerId;
    }

    public String getPartnerAuthToken() {
        return partnerAuthToken;
    }

    public void setPartnerAuthToken(String partnerAuthToken) {
        this.partnerAuthToken = partnerAuthToken;
    }

    public String getSyncTime() {
        return syncTime;
    }

    public void setSyncTime(String syncTime) {
        this.syncTime = syncTime;
    }

    @Override
    public String toString() {
        return "PartnerAuthModel{" +
                "partnerId=" + partnerId +
                ", partnerAuthToken='" + partnerAuthToken + '\'' +
                ", syncTime='" + syncTime + '\'' +
                '}';
    }

    public int getPartnerLoginTime() {
        return partnerLoginTime;
    }

    public void setPartnerLoginTime(int partnerLoginTime) {
        this.partnerLoginTime = partnerLoginTime;
    }

    public int getDecodeSyncTime() {
        return decodeSyncTime;
    }

    public void setDecodeSyncTime(int decodeSyncTime) {
        this.decodeSyncTime = decodeSyncTime;
    }
}
