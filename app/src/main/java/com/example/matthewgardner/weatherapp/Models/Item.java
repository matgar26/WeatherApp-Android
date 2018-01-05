package com.example.matthewgardner.weatherapp.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by matthewgardner on 1/5/18.
 */

public class Item {

    @SerializedName("condition")
    @Expose
    private Condition condition;
    @SerializedName("forecast")
    @Expose
    private List<Forecast> forecast = null;

    public Item(Condition condition, List<Forecast> forecast) {
        super();
        this.condition = condition;
//        this.forecast = forecast;
    }

    public Condition getCondition() {
        return condition;
    }

    public void setCondition(Condition condition) {
        this.condition = condition;
    }

    public List<Forecast> getForecast() {
        return forecast;
    }

    public void setForecast(List<Forecast> forecast) {
        this.forecast = forecast;
    }
}
