package com.example.matthewgardner.weatherapp.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by matthewgardner on 1/4/18.
 */

public class Weather {

    @SerializedName("query")
    @Expose
    private Query query;

    public Weather(Query query) {
        super();
        this.query = query;
    }

    public Query getQuery() {
        return query;
    }

    public void setQuery(Query query) {
        this.query = query;
    }

}
