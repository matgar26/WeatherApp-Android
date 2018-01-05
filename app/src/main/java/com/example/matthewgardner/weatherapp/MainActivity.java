package com.example.matthewgardner.weatherapp;

import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.matthewgardner.weatherapp.Models.Atmosphere;
import com.example.matthewgardner.weatherapp.Models.Condition;
import com.example.matthewgardner.weatherapp.Models.Forecast;
import com.example.matthewgardner.weatherapp.Models.Weather;
import com.example.matthewgardner.weatherapp.Models.Wind;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlaceSelectionListener;
import com.google.android.gms.location.places.ui.SupportPlaceAutocompleteFragment;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements PlaceSelectionListener {

    TextView name, temp, windSpeed, humidity, conditions;
    Button forecastButton;
    ProgressBar loadingIndicator;

    List<Forecast> forecastList;

    String city;
    String state;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        name = findViewById(R.id.name);
        temp = findViewById(R.id.temp);
        windSpeed = findViewById(R.id.wind);
        humidity = findViewById(R.id.humidity);
        conditions = findViewById(R.id.conditions);

        loadingIndicator = findViewById(R.id.loadingIndicator);
        loadingIndicator.setVisibility(View.GONE);

        forecastButton = findViewById(R.id.forecastbutton);
        forecastButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                Constants.currentLocaiton = city;
                Constants.CurrentForecastList = (ArrayList<Forecast>) forecastList;
                startActivity(new Intent(MainActivity.this, ForecastActivity.class));
            }
        });


        final SupportPlaceAutocompleteFragment autocompleteFragment = (SupportPlaceAutocompleteFragment) getSupportFragmentManager().findFragmentById(R.id.autocomplete_fragment);
        autocompleteFragment.setHint("Search by Address, City, or Zipcode");

        // Register a listener to receive callbacks when a place has been selected or an error has occurred.
        autocompleteFragment.setOnPlaceSelectedListener(this);
    }

    @Override
    public void onPlaceSelected(Place place) {
        loadingIndicator.setVisibility(View.VISIBLE);
        loadingIndicator.animate();

        Geocoder geocoder = new Geocoder(getApplicationContext(), Locale.getDefault());
        List<Address> addresses;

        try {
            addresses = geocoder.getFromLocation(
                    place.getLatLng().latitude,
                    place.getLatLng().longitude,
                    1);

            if (addresses.size() > 0) {
                if (addresses.get(0).getLocality() != null) {
                    city = addresses.get(0).getLocality();
                }

                if (addresses.get(0).getAdminArea() != null) {
                    state = addresses.get(0).getAdminArea();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        getWeather();
    }

    @Override
    public void onError(Status status) {

    }

    public void getWeather() {
        NetworkInterface networkManager = NetworkInterface.retrofit.create(NetworkInterface.class);

        Call<Weather> getWeather = networkManager.getWeather("yql?q=select%20*%20from%20weather.forecast%20where%20woeid%20in%20(select%20woeid%20from%20geo.places(1)%20where%20text%3D%22" + city + "%2C%20" + state +"%22)&format=json&env=store%3A%2F%2Fdatatables.org%2Falltableswithkeys");
        getWeather.enqueue(new Callback<Weather>() {
            @Override
            public void onResponse(Call<Weather> call, Response<Weather> response) {
                if (response.isSuccessful()) {
                    Condition condition = response.body().getQuery().getResults().getChannel().getItem().getCondition();
                    Wind wind = response.body().getQuery().getResults().getChannel().getWind();
                    Atmosphere atmos = response.body().getQuery().getResults().getChannel().getAtmosphere();

                    loadingIndicator.setVisibility(View.GONE);
                    loadingIndicator.clearAnimation();

                    name.setText(city + ", " + state);
                    windSpeed.setText("Wind: " + wind.getSpeed() + " mph");
                    conditions.setText("Current Conditions " + condition.getText());
                    humidity.setText("Humidity: " + atmos.getHumidity());
                    temp.setText("Current Temperature: " + condition.getTemp() + " Degrees F.");

                    forecastList = response.body().getQuery().getResults().getChannel().getItem().getForecast();

                    forecastButton.setVisibility(View.VISIBLE);
                } else {
                    Toast.makeText(MainActivity.this, "Error Retrieving Weather", Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<Weather> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Error Retrieving Weather", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
