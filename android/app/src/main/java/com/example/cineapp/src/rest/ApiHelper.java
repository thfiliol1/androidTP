package com.example.cineapp.src.rest;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiHelper {
    private CinemaApi cinemaApi;

    public CinemaApi getCinemaApi() {
        return cinemaApi;
    }

    private ApiHelper() {
        Retrofit retrofit = new Retrofit.Builder().baseUrl("https://etudiants.openium.fr/").addConverterFactory(GsonConverterFactory.create()).build();
        cinemaApi = retrofit.create(CinemaApi.class);
    }

    private static volatile ApiHelper instance;


    public static synchronized ApiHelper getInstance() {
        if (instance == null) {
            instance = new ApiHelper();
        }
        return instance;
    }
}
