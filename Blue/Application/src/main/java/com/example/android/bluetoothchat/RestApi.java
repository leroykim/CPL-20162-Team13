package com.example.android.bluetoothchat;


import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

/**
 * Created by bluesky on 2016-11-16.
 */

public interface RestApi {
    @Headers("Content-Type: application/fhir+json")
    @POST("/repository/save/patients")
    Call<Void> savePatient(@Body String req);

    @Headers("Content-Type: application/fhir+json")
    @POST("/repository/save/bpm")
    Call<Void> saveBpm(@Body String req);

    @Headers("Content-Type: application/fhir+json")
    @POST("/repository/save/spo2")
    Call<Void> saveSpo2(@Body String req);
}
