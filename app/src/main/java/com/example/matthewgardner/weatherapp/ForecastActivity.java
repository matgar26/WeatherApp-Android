package com.example.matthewgardner.weatherapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

public class ForecastActivity extends AppCompatActivity {

    TextView name;
    ListView forecastList;
    ForecastListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forecast);

        name = findViewById(R.id.name);
        name.setText("Forecast for " + Constants.currentLocaiton);
        forecastList = findViewById(R.id.list);

        adapter = new ForecastListAdapter(ForecastActivity.this, Constants.CurrentForecastList, getResources());

        forecastList.setAdapter(adapter);

    }
}
