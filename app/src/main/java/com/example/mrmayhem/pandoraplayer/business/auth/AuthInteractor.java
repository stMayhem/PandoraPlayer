package com.example.mrmayhem.pandoraplayer.business.auth;

import android.util.Log;

import com.example.mrmayhem.pandoraplayer.business.models.PartnerAuthModel;
import com.example.mrmayhem.pandoraplayer.data.auth.IAuthRepository;
import com.example.mrmayhem.pandoraplayer.utils.Constants;
import com.example.mrmayhem.pandoraplayer.utils.Helper;
import com.google.gson.JsonObject;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.concurrent.TimeUnit;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;

import io.reactivex.Single;

/**
 * Created by mrMayhem on 30.08.2017.
 */

public class AuthInteractor implements IAuthInteractor {
    private static final String TAG = AuthInteractor.class.getSimpleName();
    private IAuthRepository repository;


    public AuthInteractor(IAuthRepository repository) {
        this.repository = repository;
    }

    @Override
    public Single<PartnerAuthModel> authPartner() {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("username", "android");
        jsonObject.addProperty("password", "AC7IBG09A3DTSYM4R41UJWL07VLN8JI7");
        jsonObject.addProperty("deviceModel", "android-generic");
        jsonObject.addProperty("version", "5");

        return repository.authPartner(jsonObject)
                .delay(2, TimeUnit.SECONDS)
                .flatMap(
                res -> {
                    String stat = res.get("stat").getAsString();
                    if (stat.equals("ok")) {
                        JsonObject result = res.get("result").getAsJsonObject();

                        int time = Integer.valueOf(decryptHexString(result.get("syncTime").getAsString()));
                        int unixTime = (int) (System.currentTimeMillis() / 1000L);

                        PartnerAuthModel partnerAuthModel = new PartnerAuthModel();
                        partnerAuthModel.setPartnerId(result.get("partnerId").getAsInt());
                        partnerAuthModel.setPartnerAuthToken(result.get("partnerAuthToken").getAsString());
                        partnerAuthModel.setSyncTime(result.get("syncTime").getAsString());
                        partnerAuthModel.setPartnerLoginTime(unixTime);
                        partnerAuthModel.setDecodeSyncTime(time);

                        return Single.just(
                                partnerAuthModel);

                    } else {
                        String mes = res.get("message").getAsString();
                        int code = res.get("code").getAsInt();
                        return Single.error(new Exception("message: " + mes +
                                " code: " + code));
                    }
                }
        );
    }

    @Override
    public void savePartnerModel(PartnerAuthModel model) {
        repository.savePartnerModel(model);

    }


    @Override
    public PartnerAuthModel getPartnerModel() {
        return repository.getPartnerModel();

    }

    @Override
    public Single<Object> authUser() {
        PartnerAuthModel partnerModel = getPartnerModel();

        JsonObject jsonObject = new JsonObject();

        int unixTime = (int) (System.currentTimeMillis() / 1000L);
        int time = unixTime + (partnerModel.getPartnerLoginTime() - partnerModel.getDecodeSyncTime());

        jsonObject.addProperty("loginType", "user");
        jsonObject.addProperty("username", "dsdas");
        jsonObject.addProperty("password", "aaaa");
        jsonObject.addProperty("partnerAuthToken", partnerModel.getPartnerAuthToken());
        jsonObject.addProperty("syncTime", time);
        Log.d(TAG, "time: " + time);
        Log.d(TAG, "jsonObject: " + jsonObject);

        String s1 = jsonObject.toString();

        String de = encryptToHex(s1).toLowerCase();
        Log.d(TAG, "body: "+de);


        return repository.authUser(de, partnerModel.getPartnerId(), partnerModel.getPartnerAuthToken())
                .flatMap(res -> {
                            Log.d(TAG, "authUser: " + res.toString());
                            return Single.just(new Object());
                        }

                );
    }


    public String decryptHexString(String syncTime) {
        String decStr = "";
        try {
            Cipher m_decrypt = Cipher.getInstance("Blowfish/ECB/PKCS5Padding");
            m_decrypt.init(Cipher.DECRYPT_MODE, new SecretKeySpec(Constants.DEC_KEY.getBytes(), "Blowfish"));
            byte[] decrypted = m_decrypt.doFinal(Helper.hexToBytes(syncTime));
            byte[] newDecrypted = new byte[decrypted.length - 4];
            System.arraycopy(decrypted, 4, newDecrypted, 0, decrypted.length - 4);
            decStr = new String(newDecrypted, "UTF-8");

        } catch (NoSuchAlgorithmException | NoSuchPaddingException | BadPaddingException | InvalidKeyException | IllegalBlockSizeException  | UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        Log.d(TAG, "decryptHexString: " + decStr);
        return decStr;
    }

    public String encryptToHex(String s) {
        String encStr = "";
        try {
            Cipher m_encrypt = Cipher.getInstance("Blowfish");
            m_encrypt.init(Cipher.ENCRYPT_MODE, new SecretKeySpec(Constants.ENC_KEY.getBytes(), "Blowfish"));
            byte[] encrypted = m_encrypt.doFinal(s.getBytes());
            encStr = Helper.bytesToHex(encrypted);
        } catch (NoSuchAlgorithmException | NoSuchPaddingException | BadPaddingException | InvalidKeyException | IllegalBlockSizeException e) {
            e.printStackTrace();
        }
        return encStr;
    }


}
