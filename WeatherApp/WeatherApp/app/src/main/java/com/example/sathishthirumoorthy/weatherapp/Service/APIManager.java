package com.example.sathishthirumoorthy.weatherapp.Service;


import com.example.sathishthirumoorthy.weatherapp.Model.Example;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.http.GET;
import retrofit.http.Query;


public class APIManager {

    private static final String URL = "http://api.openweathermap.org/data/2.5";
    private static Mc2Service mc2Service;

    public static Mc2Service getApiService() {
        RestAdapter restAdapter = new RestAdapter.Builder().
                setEndpoint(URL).build();

        mc2Service = restAdapter.create(Mc2Service.class);

        return mc2Service;

    }

    public interface Mc2Service {

        @GET("/weather")
        void getWeatherInfo(
                @Query("q") String city,
                @Query("appid") String appid,
                Callback<Example> cb);
    }

}