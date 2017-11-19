package com.example.sathishthirumoorthy.weatherapp;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sathishthirumoorthy.weatherapp.Helper.CityPreference;
import com.example.sathishthirumoorthy.weatherapp.Model.Example;
import com.example.sathishthirumoorthy.weatherapp.Service.APIManager;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;


/**
 * Created by sathishthirumoorthy on 11/18/17.
 */

public class WeatherFragment extends Fragment {

    TextView cityField;
    TextView updatedField;
    TextView detailsField;
    TextView currentTemperatureField;
    ImageView weatherIcon;

    Handler handler;


    public WeatherFragment() {
        handler = new Handler();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_weather, container, false);
        cityField = (TextView) rootView.findViewById(R.id.city_field);
        updatedField = (TextView) rootView.findViewById(R.id.updated_field);
        detailsField = (TextView) rootView.findViewById(R.id.details_field);
        currentTemperatureField = (TextView) rootView.findViewById(R.id.current_temperature_field);
        weatherIcon = (ImageView) rootView.findViewById(R.id.weather_icon);

        return rootView;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Initiate API call
        updateWeatherData(new CityPreference(getActivity()).getCity());

    }

    /** Initiate weather call with parameter
     city and APP Key **/
    private void updateWeatherData(final String city) {

        APIManager.getApiService().getWeatherInfo(city, getString(R.string.open_weather_maps_app_id),
                callback);
    }

    //Weather service call back
    private Callback<Example> callback = new Callback<Example>() {
        @Override
        public void success(Example response, Response response2) {
            try {
                cityField.setText(response.getName().toUpperCase(Locale.US) +
                        ", " +
                        response.getSys().getCountry());

                detailsField.setText(
                        response.getWeather().get(0).getDescription().toUpperCase(Locale.US) +
                                "\n" + "Humidity: " + response.getMain().getHumidity() + "%" +
                                "\n" + "Pressure: " + response.getMain().getPressure() + " hPa" +
                                "\n" + "Wind: " + response.getWind().getSpeed());


                //Converting kelvin to fahrenheit
                int finalValue = (int) Math.round(1.8 * (response.getMain().getTemp() - 273.15F)) + 32;
                currentTemperatureField.setText(finalValue + "°F");

                //Convert date MMM DD, YYYY format
                DateFormat df = DateFormat.getDateTimeInstance();
                String updatedOn = df.format(new Date(response.getDt() * 1000));
                updatedField.setText("Last update: " + updatedOn);

                //Based on climate updating icon
                setWeatherImage(response.getWeather().get(0).getIcon());

            } catch (Exception e) {
                Log.e("SimpleWeather", "One or more fields not found in the JSON data");
            }
        }

        @Override
        public void failure(RetrofitError error) {
            Toast.makeText(getActivity(), R.string.place_not_found, Toast.LENGTH_LONG).show();
        }
    };

    public void changeCity(String city) {
        updateWeatherData(city);
    }

    private void setWeatherImage(String icon) {

        if (icon.equalsIgnoreCase("01d")) {
            weatherIcon.setImageResource(R.drawable.w01d);
        } else if (icon.equalsIgnoreCase("01n")) {
            weatherIcon.setImageResource(R.drawable.w01n);
        }

        else if (icon.equalsIgnoreCase("02d")) {
            weatherIcon.setImageResource(R.drawable.w02d);
        } else if (icon.equalsIgnoreCase("02n")) {
            weatherIcon.setImageResource(R.drawable.w02n);
        } else if (icon.equalsIgnoreCase("03d")) {
            weatherIcon.setImageResource(R.drawable.w03d);
        } else if (icon.equalsIgnoreCase("03n")) {
            weatherIcon.setImageResource(R.drawable.w03n);
        }

        else if (icon.equalsIgnoreCase("04d")) {
            weatherIcon.setImageResource(R.drawable.w04d);
        } else if (icon.equalsIgnoreCase("04n")) {
            weatherIcon.setImageResource(R.drawable.w04n);
        } else if (icon.equalsIgnoreCase("09d")) {
            weatherIcon.setImageResource(R.drawable.w09d);
        } else if (icon.equalsIgnoreCase("09n")) {
            weatherIcon.setImageResource(R.drawable.w09n);
        }

        else if (icon.equalsIgnoreCase("10d")) {
            weatherIcon.setImageResource(R.drawable.w10d);
        } else if (icon.equalsIgnoreCase("10n")) {
            weatherIcon.setImageResource(R.drawable.w10n);
        } else if (icon.equalsIgnoreCase("11d")) {
            weatherIcon.setImageResource(R.drawable.w11d);
        }

        else if (icon.equalsIgnoreCase("11n")) {
            weatherIcon.setImageResource(R.drawable.w11n);
        } else if (icon.equalsIgnoreCase("13d")) {
            weatherIcon.setImageResource(R.drawable.w13d);
        } else if (icon.equalsIgnoreCase("13n")) {
            weatherIcon.setImageResource(R.drawable.w13n);
        }

    }
}
