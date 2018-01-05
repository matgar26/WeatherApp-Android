package com.example.matthewgardner.weatherapp.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by matthewgardner on 1/5/18.
 */

public class Query {
    @SerializedName("count")
    @Expose
    private Integer count;

    @SerializedName("results")
    @Expose
    private Result results;

    public Query(Integer count, String created, String lang, Result results) {
        super();
        this.count = count;
        this.results = results;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Result getResults() {
        return results;
    }

    public void setResults(Result results) {
        this.results = results;
    }
}
