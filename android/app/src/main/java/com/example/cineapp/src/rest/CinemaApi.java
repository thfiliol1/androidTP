package com.example.cineapp.src.rest;

import com.example.cineapp.src.model.MainJSONResult;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface CinemaApi {

    /**
     * Call a webservice with a static path
     */
    @GET("pam/cine.json")
    Call<MainJSONResult> getMainJSONResult();
}
