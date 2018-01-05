package com.example.matthewgardner.weatherapp;

import com.example.matthewgardner.weatherapp.Models.Weather;
import com.google.android.apps.common.proguard.UsedByReflection;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.Url;

/**
 * Created by matthewgardner on 1/4/18.
 */

public interface NetworkInterface {

    @GET
    public Call<Weather> getWeather(@Url String urlQuery);

    public static final Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://query.yahooapis.com/v1/public/")
            .addConverterFactory(GsonConverterFactory.create())

            .build();
}