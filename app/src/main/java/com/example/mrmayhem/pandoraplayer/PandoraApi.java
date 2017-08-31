package com.example.mrmayhem.pandoraplayer;

import com.google.gson.JsonObject;

import io.reactivex.Single;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by mrMayhem on 29.08.2017.
 */

public interface PandoraApi {
    @Headers("Content-Type: application/json")
    @POST("?method=auth.partnerLogin")
    Single<JsonObject> authPartner(@Body JsonObject body);

    @Headers("Content-Type: text/plain")
    @POST("?method=auth.userLogin")
    Single<JsonObject> authUser(@Body String  body, @Query("partner_id") int partnerId, @Query("auth_token") String authToken);


    @Headers("Content-Type: application/json")
    @POST("?method=station.getPlaylist&partner_id=42&auth_token=VAO2oyLUwF4Y0yLMml6PKstg==")
    Single<JsonObject> getUserPlaylist(@Body JsonObject body);
}
