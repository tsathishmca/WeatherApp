package com.example.sathishthirumoorthy.weatherapp.Helper;

import android.app.Activity;
import android.content.SharedPreferences;

/**
 * Created by sathishthirumoorthy on 11/18/17.
 */

public class CityPreference {

    SharedPreferences prefs;

    public CityPreference(Activity activity){
        prefs = activity.getPreferences(Activity.MODE_PRIVATE);
    }

    // If the user has not chosen a city yet, return
    //  default city
    public String getCity(){
        return prefs.getString("city", "chicago");
    }

    public void setCity(String city){
        prefs.edit().putString("city", city).commit();
    }
}
